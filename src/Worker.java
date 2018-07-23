import java.net.*;
import java.io.*;

public class Worker implements Runnable {
    private String meno;
    private char znak;
    public Worker opponent;
    Socket socket;
    Game game;
    public ObjectInputStream inputStream = null;
    public ObjectOutputStream outputStream = null;

    public Worker(Socket socker_i, char znak_i, Game game_i) throws IOException {
        socket = socker_i;
        znak = znak_i;
        game = game_i;
        System.out.println("SERVER HRAC SA NAPOJIL");
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());

    }


    public synchronized void tah(Message msg_i)
    {
        try {
            opponent.outputStream.writeObject(msg_i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  * The run method of this thread.
     *  
     */
    public void run() {
        try {
            // The thread is only started after everyone connects.
            //TODO  init hry
            new Message('I');


            //TODO zmena poradia hraca

            while (true) {
                Message msg = (Message) inputStream.readObject();
                switch (msg.getFlag()) {
                    case 'M':
                        break;
                    case 'T':


                    case 'Q':
                        return;
                }
            }
        } catch (IOException e) {
            System.out.println("SERVER " + meno + "  sa odpojil/a " + e);
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
