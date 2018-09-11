package Network;

import Network.MessageTypes.InitMessage;
import Network.MessageTypes.Message;
import Network.MessageTypes.TurnMessage;

import java.net.*;
import java.io.*;


public class Worker extends Thread {
    private String meno;
    private char znak;
    public Worker opponent;
    Socket socket;
    public ObjectInputStream inputStream = null;
    public ObjectOutputStream outputStream = null;

    public Worker(Socket socker_i, char znak_i) throws IOException {
        socket = socker_i;
        znak = znak_i;
        System.out.println("SERVER: HRAC SA NAPOJIL");
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
    }

    private void sendMessage(Message msg)
    {
        try {
            outputStream.writeObject(msg);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void run() {
        try {

            sendMessage(InitMessage.create(Server.getServer().getServerGame().getWidth(),Server.getServer().getServerGame().getHeight(),znak));

            //TODO zmena poradia hraca

            while (true) {
                Message msg = (Message) inputStream.readObject();
                switch (msg.getFlag()) {
                    case 'M':
                        break;
                    case 'T':
                        TurnMessage message = (TurnMessage) msg;
                        if (znak == message.getPlayerSymbol())  // kuk toto povodne bolo  == (!=) a fungovalo to aj ked nemalo :D
                       opponent.sendMessage(msg);
                        break;
                    case 'Q':
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
