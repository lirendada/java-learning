package TCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private Socket conn = null;

    public Client(String addr, int port) throws IOException {
        conn = new Socket(addr, port);
    }

    public void start() {
        System.out.println("客户端启动！");
        Scanner tmp = new Scanner(System.in);
        try (InputStream in = conn.getInputStream();
             OutputStream out = conn.getOutputStream();
             Scanner sc = new Scanner(in);
             PrintWriter pw = new PrintWriter(out)) {
            while(true) {
                // 1. 输入数据，写入Socket中，然后刷新
                System.out.print("请输入要发送的数据：");
                String req = tmp.nextLine();
                pw.println(req);  // ✔自动添加换行符，满足服务端 Scanner.nextLine()，使用write则会死循环！
                pw.flush();       // ❗❗❗细节❗❗❗

                // 2. 接收服务器的响应
                if(sc.hasNextLine() == false) {
                    System.out.println("客户端断开连续！");
                    break;
                }
                String resp = sc.nextLine();

                // 打印日志看看效果
                System.out.printf("[%s:%d] req: %s, resp: %s\n", conn.getInetAddress().toString(),
                                                                 conn.getPort(),
                                                                 req, resp);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client("127.0.0.1", 9090);
        client.start();
    }
}
