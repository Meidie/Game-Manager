package Network.MessageTypes;

import Objects.Game;
import java.io.Serializable;

public class TurnMessage extends Message implements Serializable {

    private char playerSymbol;
    private int gameTurnCount;
    private int widthSur;
    private int heightSur;

    private TurnMessage(char flag) {
        this.setFlag(flag);
    }

    public static Message create(int widthSur, int heightSur, char playerSymbol_i,int gameTurn) {
        Message message = new TurnMessage('T');
        ((TurnMessage) message).playerSymbol = playerSymbol_i;
        ((TurnMessage) message).widthSur = widthSur;
        ((TurnMessage) message).heightSur = heightSur;
        ((TurnMessage) message).gameTurnCount = gameTurn;
        return message;
    }

    public char getPlayerSymbol() {
        return playerSymbol;
    }

    public int getGameTurnCount() {
        return gameTurnCount;
    }

    public int getWidthSur() {
        return widthSur;
    }

    public int getHeightSur() {
        return heightSur;
    }

}
