package server;

/**
 * @author ashang  970090853@qq.com
 * @Date 19-10-17 上午11:17
 * <p>
 * 类说明：
 */
public class MyServer {

    public static void main(String[] args) {

        Server server = new Server(8081);

        server.start();
    }
}
