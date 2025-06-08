package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Scanner;

/**
 * 服务器不需要知道数据从哪里来，所以不需要用字段来存放客户端的IP和端口，直接从请求中获取接口
 */
public class Server {
    /*  */

    private DatagramSocket socket = null; // 通信就靠DatagramSocket对象

    public Server(int port) throws SocketException {
        socket = new DatagramSocket(port); // 服务端需要指定port，让别人来连接
    }

    public void start() throws IOException {
        System.out.println("服务器启动！");
        while(true) {
            // 1. （阻塞）接收客户端发来的请求，并且进行解析
            DatagramPacket req = new DatagramPacket(new byte[4096], 4096);
            socket.receive(req);
            String data = new String(req.getData(), 0, req.getLength()); // 从 DatagramPacket 取到有效的数据

            // 2. 处理请求
            String outcome = func(data);

            // 3. 封装成数据报进行响应（注意要填写目的端口和ip）
            DatagramPacket resp = new DatagramPacket(outcome.getBytes(),
                    0,
                    outcome.getBytes().length,
                    //req.getAddress(), req.getPort()); // 可以这样子写，但是麻烦，推荐下面的写法
                    req.getSocketAddress());
            socket.send(resp);

            // 打印日志，看看效果
            System.out.printf("[%s:%d] req: %s, resp: %s\n", req.getAddress(), req.getPort(), data, outcome);
        }
    }

    // 业务代码（不是现在的重点）
    public String func(String data) {
        return data;
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(8080);
        server.start();
    }
}
