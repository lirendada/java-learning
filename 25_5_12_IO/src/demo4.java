import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class demo4 {
    public static void main(String[] args) {
        try(OutputStream out = new FileOutputStream("./main1.txt")) {
            byte[] buf = {
                97, 98, 99, 100
            };
            out.write(buf); // 将buf数据写入到文件中去
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
