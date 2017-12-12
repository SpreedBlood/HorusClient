package com.horus;

import com.horus.network.Listener;

/**
 * Created by SpreedBlood on 2017-12-09.
 */
public class Horus {

    public static void main(String[] args) {

        new Thread(new Listener(30000)).start();

    }

}
