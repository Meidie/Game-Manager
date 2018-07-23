package Network;

import Objects.Game;

import java.io.IOException;
import java.net.ServerSocket;

public class Server implements Runnable {

    private final int port;
    private ServerSocket listener;



    public Server(int port_i) throws IOException {
        port = port_i;

        listener = new ServerSocket(port);
        System.out.println("SERVER ide na porte : " + port);
        run();

    }





    @Override
    public void run() {
        try {

            while (true) {
                Game game = new Game();
                Worker x = new Worker(listener.accept(),'X',game);
                Worker o = new Worker(listener.accept(),'O',game);
                System.out.println("test");
                x.opponent=o;
                o.opponent=x;
                x.run();
                o.run();

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                listener.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }
}
