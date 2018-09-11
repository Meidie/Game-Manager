package Network;

import Objects.Game;

import java.io.IOException;
import java.net.ServerSocket;

public class Server extends Thread {

    private int port;
    private ServerSocket listener;
    private Game serverGame;
    static private Server server = null;
    public Server(int port_i) throws IOException {
        server = this;
        port = port_i;
        serverGame = Game.initTicTacToe();
        listener = new ServerSocket(port);
        System.out.println("SERVER ide na porte : " + port);
    }

    @Override
    public void run() {
        try {

            while (true) {
                Worker x;
                Worker o;
                x = new Worker(listener.accept(), 'X');
                o = new Worker(listener.accept(), 'O');
                x.start();
                o.start();
                x.opponent = o;
                o.opponent = x;
                //TODO exit server cez finally // Warning:(25, 13) 'while' statement cannot complete without throwing an exception
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

    public Game getServerGame() {
        return serverGame;
    }

    static Server getServer()
    {
        return server;
    }

    public static void log(String message) {
        System.out.println("SERVER: " + message);
    }
}
