import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class demo5 {
    public static void main(String[] args) {
        try(InputStream in = new FileInputStream("./main.txt");
            Scanner sc = new Scanner(in, "UTF-8")) {

            while(sc.hasNext()) {
                String line = sc.nextLine();
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
