package Objects;

import Network.Server;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {


        System.out.print("Hello!");

        new Server(30000);

        //new Objects.Game();
    }
}
