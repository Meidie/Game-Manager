package Objects;

import Network.Client;
import Network.MessageTypes.Message;
import Network.MessageTypes.TurnMessage;
import Network.Server;

import java.util.Arrays;

public class Game {

    private int width;
    private int height;
    private char znak;
    private char naTahu;
    private char[][] playBoard;
    private int gameTurnCount = 0;


    public static Game initTicTacToe() {
        //TODO i001 input kto zacne 1. ci "x" / "o"  (string)

        int height = InputFromKeyboard.readInt("Zadajte X-ovu velkost plochy");
        int width = InputFromKeyboard.readInt("Zadajte Y-ovu velkost plochy");
        return (new Game(width,height,'S','X'));
    }

    public Game(int x, int y, char znak_i, char naTahu_i) {
        height = x;
        width = y;
        znak = znak_i;
        naTahu = naTahu_i;
        this.playBoard = new char[width][height];
        for (int x_tmp = 0; x_tmp < width; x_tmp++) {
            for (int y_tmp = 0; y_tmp < height; y_tmp++) {
                playBoard[x_tmp][y_tmp] = '0';
            }
        }
        //  printArray();
        if (naTahu == znak) {
            createTurn();
        }
    }

    public int setX() {
        int x = InputFromKeyboard.readInt("si na tahu/nzadaj riadok");
        return x;
    }

    public int setY() {
        int y = InputFromKeyboard.readInt("zadaj stlpec");
        return y;
    }

    public void update(Message msg) {
        if (msg.getFlag() == 'T') {
            TurnMessage message = (TurnMessage) msg;
            playBoard[message.getWidthSur()][message.getHeightSur()] = message.getPlayerSymbol();
            createTurn();
        }

    }


    public void createTurn() {
        printArray();
        int x = 0;
        int y = 0;
        boolean check = false;
        while (!check) {
            x = setX() - 1;
            y = setY() - 1;
            check = testPoint(x, y, '0');
        }
        //TODO zmenit znak na char
        playBoard[x][y] = znak;

        printArray();
        if (checkBoard_T(x, y, 3)) {
            Server.log("hrac " + znak + " vyhral");
        }
        Client.send_message(TurnMessage.create(x,y,znak,0));


    }

    public void printArray() {
        System.out.println("\n" + Arrays.deepToString(playBoard).
                replace("], ", "]\n").
                replace("[[", "[").
                replace("]]", "]").
                replace("\0", "0").
                replace(",", ""));
    }

    ///////////////
    private boolean checkBoard_T(int x, int y, int count) {
        if (checkOneWay(x, y, 1, 0, count) || checkOneWay(x, y, 0, 1, count) || checkOneWay(x, y, 1, 1, count) || checkOneWay(x, y, -1, 1, count))
            return true;
        else
            return false;
    }

    private boolean checkOneWay(int x, int y, int up, int left, int count) {
        char tmp = playBoard[x][y];

        while (testPoint(x, y, tmp)) {
            x += up;
            y += left;
        }
        up *= -1;
        left *= -1;
        x += up;
        y += left;
        int checkCount = 0;

        while (testPoint(x, y, tmp) && (checkCount < count)) {
            checkCount++;
            x += up;
            y += left;
        }
        if (checkCount == count) {
            return true;
        }
        return false;
    }

    private boolean testPoint(int x, int y, char mark) {
        if ((x >= 0) && (y >= 0) && (x < height) && (y < width) && (playBoard[x][y] == mark))
            return true;
        else
            return false;
    }

    public int getGameTurnCount() {
        return gameTurnCount;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
