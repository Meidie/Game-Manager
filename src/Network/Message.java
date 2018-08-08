package Network;

import java.io.Serializable;

public class Message implements Serializable {
    private String flag;
    private String msg1;
    private String msg2;
    private int x;
    private int y;



    public Message(String flag_i) {
        flag = flag_i;
    }

    public void setMsg1(String msg1) {
        this.msg1 = msg1;
    }

    public void setMsg2(String msg2) {
        this.msg2 = msg2;
    }

    public String getMsg1() {
        return msg1;
    }

    public String getMsg2() {
        return msg2;
    }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getFlag() {
        return flag;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
