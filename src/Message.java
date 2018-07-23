import java.io.Serializable;

public class Message implements Serializable {
    private char flag;
    private String msg;
    private int x;
    private int y;


    public Message(char flag) {

    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public char getFlag() {
        return flag;
    }

    public String getMsg() {
        return msg;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
