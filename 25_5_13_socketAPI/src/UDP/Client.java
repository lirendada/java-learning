package UDP;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * 1. 因为客户端在发送数据报的时候需要知道服务器的IP和端口，并且由于UDP
 *    每次发送数据都得传入服务器的IP和端口，所以需要用单独的字段来保存
 *
 * 2. 创建DatagramSocket时候传入的端口是指定客户端自己在本机的端口，
 *    而不是服务器的端口，注意和上面区分开！
 */
public class Client {
    private int port;    // 服务器的端口
    private String addr; // 服务器的地址
    private DatagramSocket socket = null;

    public Client(int port, String addr) throws SocketException {
        this.port = port;
        this.addr = addr;
        socket = new DatagramSocket(); // 让系统自动分配端口号
    }

    public void start() throws IOException {
        System.out.println("客户端启动！");
        Scanner sc = new Scanner(System.in);

        while(true) {
            // 1. 构造请求数据报，注意要传入目的端口和IP到数据报中
            System.out.print("请输入要发送给服务器的信息：");
            String message = sc.nextLine();
            DatagramPacket req = new DatagramPacket(message.getBytes(),
                    0,
                    message.getBytes().length,
                    InetAddress.getByName(addr),
                    port);

            // 2. 发送请求到服务器
            socket.send(req);

            // 3. 接收服务器发来的响应，然后进行解析
            DatagramPacket resp = new DatagramPacket(new byte[4096], 4096);
            socket.receive(resp);
            String data = new String(resp.getData(), 0, resp.getLength());

            // 进行日志输出，查看效果
            System.out.println("响应：" + data);
        }
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client(8080, "127.0.0.1");
        client.start();
    }
}
