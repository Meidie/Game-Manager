package Network;

import Objects.Game;

import java.io.*;
import java.net.Socket;


public class Client implements Runnable {
    Game game;
    Socket socket;
    int port;
    String IP;
    String znak;
    public ObjectInputStream in = null;
    public ObjectOutputStream out = null;

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

    public void run() {
        try {
            while (true) {
                Message msg = (Message) in.readObject();

                switch (msg.getFlag()) {
                    case "M":
                        System.out.println("CLIENT test");
                        break;
                    case "T":


                        break;
                    case "I":
                        //TODO i001  natahu dorobit
                        game = new Game(msg.getX(), msg.getY(), msg.getMsg1(), msg.getMsg2());
                        break;
                    case "Q":
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
