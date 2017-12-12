package main;

import listeners.KeyHandler;
import listeners.MouseHandler;
import listeners.MouseMotionHandler;
import network.NetworkClient;
import network.client.Client;
import room.Room;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

public class Game extends Canvas implements Runnable {

    private boolean isRunning;
    private java.util.List<IRenderable> objectsToRender;
    private static Game instance;
    private Client client;
    private Room room;

    private Game() {
        this.isRunning = false;
        this.objectsToRender = new LinkedList<>();
    }

    /**
     * Used to add renderable objects such as the room.
     * @param renderable the object to render.
     */
    public void addRenderable(IRenderable renderable) {
        if (!this.objectsToRender.contains(renderable)) {
            this.objectsToRender.add(renderable);
        }
    }

    /**
     * Used to remove the renderable object so that it doesn't get rendered.
     * @param renderable the object to remove.
     */
    public void removeRenderable(IRenderable renderable) {
        if (this.objectsToRender.contains(renderable)) {
            this.objectsToRender.remove(renderable);
        }
    }

    /**
     * Starts the whole instance
     */
    void start() {
        this.isRunning = true; //Used to cycle the game

        new Thread(this).start();
        this.addMouseListener(new MouseHandler(this));
        this.addMouseMotionListener(new MouseMotionHandler(this));
        this.addKeyListener(new KeyHandler(this));
    }

    /**
     * Sets the networking client for global access.
     * @param client the networking client
     */
    private void setClient(Client client) {
        this.client = client;
    }

    /**
     * Get the networking client
     * @return client or null if it doesn't exist.
     */
    public Client getClient() {
        if (this.client != null) {
            return this.client;
        }
        return null;
    }

    /**
     * Get the room if there's one.
     * @return the room or null if there's none
     */
    public Room getRoom() {
        if (this.room != null) {
            return this.room;
        }
        return null;
    }

    /**
     * Set the room for global access.
     * @param room the room
     */
    public void setRoom(Room room) {
        this.objectsToRender.add(room);
        this.room = room;
    }

    /**
     * Get the instance of the game for global access.
     * @return the instance
     */
    public static Game getInstance() {
        return instance;
    }

    /**
     * The whole rendering process.
     */
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            this.requestFocus();
            return;
        }
        Graphics graphics = bs.getDrawGraphics();

        for (IRenderable renderable : this.objectsToRender) {
            renderable.render(graphics);
        }

        graphics.dispose();
        bs.show();
    }

    /**
     * The whole rendering cycling.
     */
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double unprocessed = 0;
        double nsPerTick = 1000000000.0 / 60;
        int frames = 0;
        int ticks = 0;
        long lastTimer1 = System.currentTimeMillis();
        while (isRunning) {
            long now = System.nanoTime();
            unprocessed += (now - lastTime) / nsPerTick;
            lastTime = now;
            while (unprocessed >= 1) {
                ticks++;
                unprocessed -= 1;
            }

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            frames++;
            render();
            if (System.currentTimeMillis() - lastTimer1 > 1000) {
                lastTimer1 += 1000;
                System.out.println(ticks + " ticks, " + frames + " fps");
                frames = 0;
                ticks = 0;
            }
        }
    }

    /**
     * The main method.
     * @param args
     */
    public static void main(String[] args) {
        new NetworkClient("127.0.0.1", 30000).run();
    }

    /**
     * Initializes the game frame & sets the network client.
     * @param client the networking client.
     */
    public static void initialize(Client client) {
        instance = new Game();
        instance.setClient(client);
        FrameInitializer.initialize(instance);
    }

}