package main;

import listeners.KeyHandler;
import listeners.MouseHandler;
import listeners.MouseMotionHandler;
import network.NetworkClient;
import network.clients.Client;
import room.Room;
import room.map.RoomCamera;
import room.map.RoomModel;
import sprite.SpriteStorage;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Created by SpreedBlood on 2017-12-05.
 */
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

    public void addRenderable(IRenderable renderable) {
        if (!this.objectsToRender.contains(renderable)) {
            this.objectsToRender.add(renderable);
        }
    }

    public void removeRenderable(IRenderable renderable) {
        if (this.objectsToRender.contains(renderable)) {
            this.objectsToRender.remove(renderable);
        }
    }

    void start() {
        this.isRunning = true;
        new Thread(this).start();
        this.addMouseListener(new MouseHandler(this));
        ;
        this.addMouseMotionListener(new MouseMotionHandler(this));
        ;
        this.addKeyListener(new KeyHandler(this));
        ;
    }

    public static void main(String[] args) {
        new NetworkClient("127.0.0.1", 30000).run();
    }

    public static void initialize(Client client) {
        instance = new Game();
        instance.setClient(client);
        FrameInitializer.initialize(instance);
    }

    private void tick() {
    }

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
                tick();
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

    private void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        if (this.client != null) {
            return this.client;
        }
        return null;
    }

    public Room getRoom() {
        if (this.room != null) {
            return this.room;
        }
        return null;
    }

    public void setRoom(Room room) {
        this.objectsToRender.add(room);
        this.room = room;
    }

    public static Game getInstance() {
        return instance;
    }
}