package Network;

import Objects.Game;

import java.io.IOException;
import java.net.ServerSocket;

public class Server extends Thread {

    private int port;
    private ServerSocket listener;
    private Game game;

    public Server(int port_i) throws IOException {
        port = port_i;
        game = new Game();
        listener = new ServerSocket(port);
        System.out.println("SERVER ide na porte : " + port);
    }

    @Override
    public void run() {
        try {

            while (true) {
                Worker x;
                Worker o;
                x = new Worker(listener.accept(), "X", game);
                o = new Worker(listener.accept(), "O", game);
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


    public static Message init_message(Game game, String znak)
    {
        Message message = new Message("I");
        message.setMsg1(znak);
        //TODO i001 odkaz kto je na tahu
        message.setMsg2("X");
        message.setX(game.getHeight());
        message.setY(game.getWidth());
        Server.log("INIT");
        return message;
    }

    public static void log(String message) {
        System.out.println("SERVER: " + message);
    }
}
