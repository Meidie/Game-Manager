import java.util.Arrays;

public class Game {

    private int width;
    private int height;
    private char[][] playBoard;

    public Game (){

        this.height = InputFromKeyboard.readInt("Zadajte X-ovu velkost plochy");
        this.width = InputFromKeyboard.readInt("Zadajte Y-ovu velkost plochy");
        this.playBoard = new char[width][height];
        printArray();

       //test
        playBoard[2][0]= 'o';
        playBoard[1][1]= 'o';
        playBoard[0][2]= 'o';
        printArray();

        //vertikalne
        if (checkBoard(1,1,1,0,1,0,0,'o',3))
            System.out.print("Vyhral si");
        //horizintalne
        else if (checkBoard(1,1,0,1,1,0,0,'o',3))
            System.out.print("Vyhral si");
        //diagonala 1
        else if (checkBoard(1,1,1,1,1,0,0,'o',3))
            System.out.print("Vyhral si");
        //diagonala 2
        else if (checkBoard(1,1,1,-1,1,0,0,'o',3))
            System.out.print("Vyhral si");
        else
            System.out.print("Prehral si");

    }

    public void printArray(){
        System.out.println("\n" + Arrays.deepToString(playBoard).
            replace("], ", "]\n").
            replace("[[", "[").
            replace("]]", "]").
            replace("\0","░").
            replace(",",""));
    }

    //je tam trochu vela parametrov ale nevedel som ako inak obist to aby sa zakazdym nuloval heck, count a step

    //index_X,index_Y -> suradnice mieesta pridaneho symbolu
    //inc_1,inc_2 -> smer posunu
    //step -> kolky prechod
    //check -> pomocna premenna pre zabezpecenie aby suradnice nevysli mimo pola
    //count -> pocet najdenych symbolov
    //symbol -> hladany symbol
    //numberToFind -> kolko symbolov v rade hladame
    public boolean checkBoard (int index_X, int index_Y, int inc_1, int inc_2, int step, int  check, int count, char symbol, int numberToFind) {

        // 0,1,-1
        int xIncrement = inc_1 ;
        int yIncrement = inc_2 ;

        int x = index_X;
        int y = index_Y;

        while( x >= 0 && y >=0 && x < playBoard.length && y < playBoard[x].length){

            //ak najde hladany znak zvysi counter o 1
            if(playBoard[x][y] == symbol && check == 0 )
                count++;
            //ak pri prvom prechode najde hladany pocet symbolov ukonci sa a vrati true
            else if (count == numberToFind && step == 1)
                return true;
            //ak pri druhom prechode najde hladany pocet symbolov ukonci sa a vrati true
            else if ((count - 1) == numberToFind && step == 2 )
                return true;
            //ak ani po druhom prechode nenajde hladany pocet symbolov ukonci sa a vrati false
            else if ((count - 1) != numberToFind && (step == 2 || step > 2))
                return false;
            //ak po prvom prechode nenajde hladany pocet symbolov rekurzivne vykona opat chek ale opacnym smerom
            else{
                step++;
                if(checkBoard(index_X,index_Y,inc_1*(-1),inc_2*(-1),2,0,count,symbol,numberToFind))
                    return true;
            }

            //posun po polickach
            x += xIncrement;
            y += yIncrement;

            //podmienka aby sa suradnice nedostali mimo pola
            if (x < 0 || y < 0){
                x = 0;
                y = 0;
                check = 1;
            }
        }

        return  false;
    }
}
