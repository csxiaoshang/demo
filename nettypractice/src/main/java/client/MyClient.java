package client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author ashang  970090853@qq.com
 * @Date 19-10-17 下午3:28
 * <p>
 * 类说明：
 */
public class MyClient {

    public static void main(String[] args) {

        Client client = new Client("127.0.0.1", 8081);

        client.start();

    }
}
