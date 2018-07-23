import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    private final int port=0;
    private ServerSocket listener;



    public Server(int port_i) throws IOException {
        port = port_i;

        listener = new ServerSocket(port);
        System.out.println("SERVER ide na porte : " + port);

    }



    public void tah(int location, Player player) {
        /*
        if (player == currentPlayer && board[location] == null) {
            board[location] = currentPlayer;
            currentPlayer = currentPlayer.opponent;
            currentPlayer.otherPlayerMoved(location);
            return true;
        }
        return false;
*/
 }

    @Override
    public void run() {
        try {

            while (true) {
                Game game = new Game();
                Worker x = new Worker(listener.accept(),'X',game);
                Worker o = new Worker(listener.accept(),'O',game);
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
