package UDP;

import java.io.IOException;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

public class DictServer extends Server {
    private Map<String, String> dict = new HashMap<>();

    public DictServer(int port) throws SocketException {
        super(port);
        dict.put("cat", "小猫");
        dict.put("dog", "小狗");
        dict.put("pig", "小猪");
        dict.put("bird", "小鸟");
        dict.put("sheep", "小羊");
        dict.put("cow", "小牛");
        dict.put("chicken", "小鸡");
        dict.put("rabbit", "小兔子");
        dict.put("fish", "小鱼");
        dict.put("wolf", "狼");
        dict.put("monkey", "猴子");
        dict.put("chicken", "小鸡");
        dict.put("fish", "小鱼");
        dict.put("tiger", "老虎");
        dict.put("lion", "狮子");
        dict.put("wolf", "狼");
        dict.put("monkey", "猴子");
    }

    @Override
    public String func(String data) {
        return dict.getOrDefault(data, "查无该单词");
    }

    public static void main(String[] args) throws IOException {
        DictServer server = new DictServer(8080);
        server.start();
    }
}
