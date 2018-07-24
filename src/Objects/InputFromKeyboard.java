package Objects;

        import java.io.BufferedReader;
        import java.io.InputStreamReader;

public class InputFromKeyboard {

    public static int readInt(String user_input)
    {
        int n = 0;
        String s;

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        try
        {	System.out.println(user_input);
            s = input.readLine();
            System.out.println("Nacital som " + s);
            n = Integer.parseInt(s);
        }
        catch (Exception e)
        {
            System.out.println("nepodarilo sa");
            n = readInt(user_input);
        }

        return n;
    }


}
