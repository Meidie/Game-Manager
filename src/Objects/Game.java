package Objects;

import java.util.Arrays;

public class Game {

    private int width;
    private int height;
    private char[][] playBoard;


    public Game() {

        this.height = InputFromKeyboard.readInt("Zadajte X-ovu velkost plochy");
        this.width = InputFromKeyboard.readInt("Zadajte Y-ovu velkost plochy");
        this.playBoard = new char[width][height];
        printArray();
    }

    public Game(int x, int y) {
        height = x;
        width = y;
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

}
