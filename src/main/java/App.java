import java.util.*;

public class App {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Input Data: ");
        String inputData = in.nextLine();
        in.close();

        Parser parser = new Parser();
        try {
            parser.parseString(inputData);
        } catch (MyException e) {
            e.printStackTrace();
        }
        //String inputData = "10.03.2000 89245676543 m Ivanov Ivan Ivanovich";
    }
}
