import network.Listener;

public class Horus {

    public static void main(String[] args) {
        new Thread(new Listener(30000)).start();
    }
}
