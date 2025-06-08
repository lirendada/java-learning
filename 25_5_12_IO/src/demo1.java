import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

public class demo1 {
    public static void main(String[] args) {
        try(Reader rd = new FileReader("./main.txt")) {
            while(true) {
                char[] arr = new char[1024];
                int n = rd.read(arr);
                if(n == -1) {
                    break;
                } else {
                    for (int i = 0; i < n; i++) {
                        System.out.print(arr[i]);
                    }
                    System.out.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
