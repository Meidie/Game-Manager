package Network;

import Network.MessageTypes.InitMessage;
import Network.MessageTypes.Message;
import Objects.Game;

import java.io.*;
import java.net.Socket;


public class Client implements Runnable {
    Game game;
    Socket socket;
    int port;
    String IP;
    String znak;
    static public ObjectInputStream in = null;
    static public ObjectOutputStream out = null;

    public Client(String IP_i, int port_i) {
        IP = IP_i;
        port = port_i;
        try {
            socket = new Socket(IP, port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static synchronized void sendMessage(Message msg_i) {
        try {
            out.writeObject(msg_i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            while (true) {
                Message msg = (Message) in.readObject();

                switch (msg.getFlag()) {
                    case 'M':
                        System.out.println("CLIENT test");
                        break;
                    case 'T':
                        game.update(msg,false);
                        break;
                    case 'I':
                        InitMessage message = (InitMessage) msg;
                        //TODO i001  natahu dorobit
                        game = new Game(message.getWidth(),message.getHeight(),message.getPlayerSymbol(),message.getOnTurnSymbol());
                        break;
                    case 'Q':
                        return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
