package Network;

import Objects.Game;

import java.net.*;
import java.io.*;


public class Worker extends Thread {
    private String meno;
    private String znak;
    public Worker opponent;
    Socket socket;
    Game game;
    public ObjectInputStream inputStream = null;
    public ObjectOutputStream outputStream = null;

    public Worker(Socket socker_i, String znak_i, Game game_i) throws IOException {
        socket = socker_i;
        znak = znak_i;
        game = game_i;
        System.out.println("SERVER: HRAC SA NAPOJIL");
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
    }

    public synchronized void tah(Message msg_i) {
        try {
            opponent.outputStream.writeObject(msg_i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {

            Message init = new Message("I");
            init.setMsg1(znak);
            //TODO i001 odkaz kto je na tahu
            init.setMsg2("X");
            init.setX(game.getHeight());
            init.setY(game.getWidth());
            System.out.println("SERVER: INIT");
            outputStream.writeObject(init);

            //TODO zmena poradia hraca

            while (true) {
                Message msg = (Message) inputStream.readObject();
                switch (msg.getFlag()) {
                    case "M":
                        break;
                    case "T":
                        opponent.outputStream.writeObject(msg);
                        break;
                    case "Q":
                        opponent.outputStream.writeObject(msg);
                        System.out.println("SERVER " + meno + "  sa odpojil/a");
                        return;
                }
            }
        } catch (IOException e) {
            System.out.println("SERVER " + meno + " mu padlo spojenie " + e);
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }
}
