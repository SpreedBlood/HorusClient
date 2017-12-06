import config.StaticSettings;
import listeners.MouseMotionHandler;
import room.Room;
import room.Tile;
import sprite.SpriteStorage;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * Created by SpreedBlood on 2017-12-05.
 */
public class Game extends Canvas implements Runnable {

    private boolean isRunning;
    private Room room;

    private Game() {
        isRunning = false;
    }

    private void start() {
        this.isRunning = true;
        new Thread(this).start();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.getSize(new Dimension(StaticSettings.WIDTH, StaticSettings.HEIGHT));

        Frame frame = new Frame("Habbo Client");
        frame.setLayout(new BorderLayout());
        frame.add(game, BorderLayout.CENTER);
        frame.setSize(StaticSettings.WIDTH, StaticSettings.HEIGHT);
        frame.setResizable(false);
        frame.setBackground(Color.black);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            this.requestFocus();
            return;
        }
        Graphics graphics = bs.getDrawGraphics();

        this.room.render(graphics);

        graphics.dispose();
        bs.show();
    }

    private void tick() {
    }

    private void init() {
        this.room = new Room();
        this.addMouseMotionListener(new MouseMotionHandler(this.room.getModel().getCamera()));
    }

    public void run() {
        long lastTime = System.nanoTime();
        double unprocessed = 0;
        double nsPerTick = 1000000000.0 / 60;
        int frames = 0;
        int ticks = 0;
        long lastTimer1 = System.currentTimeMillis();
        init();
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
}