package ck;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @Author: zhuquanwen
 * @Description:
 * @Date: 2018/6/4 15:49
 * @Modified:
 **/
public class ClientObj
{
	private static int port = 7777;
//	private static String ip = "192.168.0.144";
	private static String ip = "192.168.0.7";

	public static void connect()
	{
		Bootstrap bootstrap = new Bootstrap();
		EventLoopGroup worker = new NioEventLoopGroup();
		try
		{
			bootstrap.group(worker);
			bootstrap.channel(NioSocketChannel.class);
			bootstrap.handler(new ChannelInitializer<Channel>()
			{
				@Override
				protected void initChannel(Channel ch) throws Exception
				{
					ch.pipeline().addLast(new ObjectEncoder());
					ch.pipeline().addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
					ch.pipeline().addLast(new ObjectClientHandler());
				}
			});
			ChannelFuture channelFuture = bootstrap.connect(ip, port).sync();
			channelFuture.channel().closeFuture().sync();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			worker.shutdownGracefully();
		}
	}

	public static void main(String[] args)
	{
		System.err.println("client start");
		connect();
		System.err.println("connect done");
	}
}
