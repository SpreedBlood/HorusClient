package main;

import config.StaticSettings;

import java.awt.*;

/**
 * Created by SpreedBlood on 2017-12-10.
 */
public final class FrameInitializer {

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
