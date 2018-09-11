package Network.MessageTypes;

import Objects.Game;

import java.io.Serializable;

public abstract class Message implements Serializable {
    private char flag;
    private String [] messages;

    public char getFlag() {
        return flag;
    }

    public void setFlag(char flag) {
        this.flag = flag;
    }
}
