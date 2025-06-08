import java.io.*;

public class demo6 {
    public static void main(String[] args) {
        try(OutputStream out = new FileOutputStream("./main2.txt");
            PrintWriter pw = new PrintWriter(out)) {

            // 将文本输出到文件中，可以直接传String类型
            pw.write("啊啊~利刃啊liren\n利刃阿斯顿");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
