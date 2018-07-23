import java.util.Arrays;

public class Game {

    private int width;
    private int height;
    private char[][] playBoard;
    private int count = 0;
    private int step = 0;


    public Game (){

        this.height = InputFromKeyboard.readInt("Zadajte X-ovu velkost plochy");
        this.width = InputFromKeyboard.readInt("Zadajte Y-ovu velkost plochy");
        this.playBoard = new char[width][height];
        printArray();
        playBoard[0][0]= 'o';
        playBoard[1][0]= 'o';
        playBoard[2][0]= 'o';
        printArray();
        if (checkBoard(2,0,1,0))
            System.out.print("Vyhral si");
    }

    public void printArray(){
        System.out.println("\n" + Arrays.deepToString(playBoard).
            replace("], ", "]\n").
            replace("[[", "[").
            replace("]]", "]").
            replace("\0","░").
            replace(",",""));
    }


    public boolean checkBoard (int index_X, int index_Y, int inc_1, int inc_2) {

        step++;
        if(step > 2 && (count-1) == 3){
            return true;
        }
        else if (step > 2 && (count-1) != 3){
            return false;
        }

        // 0,1,-1
        int xIncrement = inc_1 ;
        int yIncrement = inc_2 ;

        int x = index_X;
        int y = index_Y;

        while( x >= 0 && y >=0 && x < playBoard.length && y < playBoard[x].length){

            if(playBoard[x][y] == 'o')
                count++;

            x += xIncrement;
            y += yIncrement;

        }

        checkBoard(index_X,index_Y,inc_1*(-1),inc_2*(-1));

        if((count-1) == 3) return true;

        return  false;
    }

}
