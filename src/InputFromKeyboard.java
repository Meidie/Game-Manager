import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InputFromKeyboard {

    public static int readInt(String napis_pre_uzivatela)
    {
        int n = 0;
        String s;

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        try
        {	System.out.println(napis_pre_uzivatela);
            s = input.readLine();
            System.out.println("Nacital som " + s);
            n = Integer.parseInt(s);
        }
        catch (Exception e)
        {
            System.out.println("nepodarilo sa");
            n = readInt(napis_pre_uzivatela);
        }

        return n;
    }

}
