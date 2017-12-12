package main;

import config.StaticSettings;

import java.awt.*;

final class FrameInitializer {

    /**
     * This is used to initialize the game and prevent the main game class to become to big.
     * @param instance of the game
     */
    static void initialize(Game instance) {
        instance.getSize(new Dimension(StaticSettings.WIDTH, StaticSettings.HEIGHT));
        Frame frame = new Frame("Habbo Client");
        frame.setLayout(new BorderLayout());
        frame.add(instance, BorderLayout.CENTER);
        frame.setSize(StaticSettings.WIDTH, StaticSettings.HEIGHT);
        frame.setResizable(false);
        frame.setBackground(Color.black);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        instance.start();
    }

}
