import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class demo3 {
    public static void main(String[] args) {
        try(InputStream in = new FileInputStream("./main.txt")) {
            byte[] buf = new byte[1024];
            while(true) {
                int n = in.read(buf);
                if(n == -1) {
                    System.out.println("读取完毕");
                    break;
                }

                for(int i = 0; i < n; ++i) {
                    System.out.printf("0x%x\n", buf[i]); // 十六进制输出
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
