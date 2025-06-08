package TCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  服务器只需要指定端口即可
 */
public class Server {
    private ServerSocket socket = null;

    public Server(int port) throws IOException {
        socket = new ServerSocket(port);
    }

    public void start() throws IOException {
        System.out.println("TCP服务器启动！");
        while(true) {
            // 1. 监听新连接
            Socket conn = socket.accept();
            System.out.println("获取到新连接：" + conn.getInetAddress() + "/" + conn.getPort());

//            // 2. 创建新线程来处理新连接，防止主线程阻塞
//            new Thread(() -> {
//                work(conn);
//            }).start();

            // 2. 创建线程池来处理新连接，推荐用newCachedThreadPool
            ExecutorService pool = Executors.newCachedThreadPool();
            pool.submit(() -> {
                work(conn);
            });
        }
    }

    // 新连接实际上要处理的任务
    private void work(Socket conn) {
        try (InputStream in = conn.getInputStream();
             OutputStream out = conn.getOutputStream();
             Scanner sc = new Scanner(in);
             PrintWriter pw = new PrintWriter(out)) {
            while(true) {
                // 3. 读取请求并进行解析
                if(sc.hasNextLine() == false) {
                    // 要判断是否断开连接：如果客户端断开连接了，则会返回false
                    System.out.printf("[%s:%d] 客户端下线！\n", conn.getInetAddress().toString(), conn.getPort());
                    break;
                }
                String req = sc.nextLine();

                // 4. 将解析后的请求进行业务处理
                String resp = func(req);

                // 5. 响应结果给客户端
                pw.println(resp); // ✔自动添加换行符，满足服务端 Scanner.nextLine()，使用write则会死循环！
                pw.flush();       // ❗❗❗细节❗❗❗

                // 搞一下日志输出看看效果
                System.out.printf("[%s:%d] req: %s, resp: %s\n", conn.getInetAddress().toString(), conn.getPort()
                                                               , req, resp);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // 6. 释放Socket资源
            try {
                conn.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String func(String data) {
        return data;
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(9090);
        server.start();
    }
}
