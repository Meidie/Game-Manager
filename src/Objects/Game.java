package Objects;

import java.util.Arrays;

public class Game {

    private int width;
    private int height;
    private String znak;
    private String naTahu;
    private char[][] playBoard;


    public Game() {
        //TODO i001 input kto zacne 1. ci "x" / "o"  (string)

        this.height = InputFromKeyboard.readInt("Zadajte X-ovu velkost plochy");
        this.width = InputFromKeyboard.readInt("Zadajte Y-ovu velkost plochy");
        this.playBoard = new char[width][height];
        //   printArray();  // toto sa vola iba pre server
    }

    public Game(int x, int y, String znak_i, String naTahu_i) {
        height = x;
        width = y;
        znak = znak_i;
        naTahu = naTahu_i;
        this.playBoard = new char[width][height];
        printArray();
    }

    public void printArray() {
        System.out.println("\n" + Arrays.deepToString(playBoard).
                replace("], ", "]\n").
                replace("[[", "[").
                replace("]]", "]").
                replace("\0", "0").
                replace(",", ""));
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
