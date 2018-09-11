package Network.MessageTypes;

import Network.Server;
import Objects.Game;

import java.io.Serializable;

public class InitMessage extends Message implements Serializable {

    private char playerSymbol;
    private char onTurnSymbol;
    private int height;
    private int width;

    private InitMessage(char symbol)
    {
        setFlag(symbol);
    }

    public static Message create(int width_i,int height_i, char playerSymbol) {
        Message message = new InitMessage('I');
        ((InitMessage) message).playerSymbol = playerSymbol;
        //TODO i001 odkaz kto je na tahu
        ((InitMessage) message).onTurnSymbol = ('X');
        ((InitMessage) message).height = height_i;
        ((InitMessage) message).width = width_i;
        Server.log("INIT");
        return message;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public char getPlayerSymbol() {
        return playerSymbol;
    }

    public char getOnTurnSymbol() {
        return onTurnSymbol;
    }
}
