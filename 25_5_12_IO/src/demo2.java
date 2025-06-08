import java.io.*;

public class demo2 {
    public static void main(String[] args) {
        try (BufferedReader rd = new BufferedReader(new FileReader("./main.txt"))) {
            String line;
            while((line = rd.readLine()) != null) {
                System.out.println(line);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
