package ck;

/**
 * Hello world!
 *
 */

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.redisson.Redisson;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RedissonClient;

import ck.po.Message;
import ck.po.Person;

import org.redisson.api.RList;

public class EchoServer
{

	private final int port;

	public static void redissionList()
	{
		// connects to 127.0.0.1:6379 by default
		RedissonClient redisson = Redisson.create();

		RList<Person> list_person = redisson.getList("personList");
		list_person.add(new Person("p1", 10));
		list_person.add(new Person("p2", 11));

		RList<String> list = redisson.getList("myList");
		list.add("1");
		list.add("2");
		list.add("3");

		Person pp = (Person) list_person.get(0);
		System.out.println(pp.getName());
		list_person.remove(0);
		list.contains("1");

		String valueAtIndex = list.get(3);

		for (String string : list)
		{
			// iteration through bulk loaded values
		}

		boolean removedValue = list.remove("1");
		list.removeAll(Arrays.asList("1", "2", "3"));
		list.containsAll(Arrays.asList("4", "1", "0"));

		List<String> secondList = new ArrayList<>();
		secondList.add("4");
		secondList.add("5");
		list.addAll(secondList);

		// fetch all objects
		List<String> allValues = list.readAll();

		list.addAfter("3", "7");
		list.addBefore("4", "6");

		// use fast* methods when previous value is not required
		list.fastSet(1, "6");
		list.fastRemove(3);

		redisson.shutdown();
	}

	public static void method3()
	{
		RedissonClient redisson = Redisson.create();

		RBlockingDeque<String> deque = redisson.getBlockingDeque("myQueue");
		deque.add("1");
		deque.add("2");
		deque.add("3");
		deque.add("4");

		deque.contains("1");
		// String one = deque.peek();
		String two = deque.poll();
		// deque.element();
		for (String string : deque)
		{
			// iteration through bulk loaded values
			System.out.println(string);
		}

		redisson.shutdown();
	}

	public static void method1()
	{
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // 将数据库驱动器类加载到jvm中
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=qyzhsfpt";// 数据库连接地址
		try
		{
			Connection conn = DriverManager.getConnection(dbURL, "sa", "root");// 需要输入登录用户名和登录密码
			System.out.println("Connection Successful!");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public static void method2()
	{
		Connection conn;
		Statement stmt;
		ResultSet rs;
		String url = "jdbc:sqlserver://localhost:1433;DatabaseName=test;";
		String sql = "select * from t_user";
		try
		{
			// 连接数据库
			conn = DriverManager.getConnection(url, "sa", "root");
			// 建立Statement对象
			stmt = conn.createStatement();
			/**
			 * Statement createStatement() 创建一个 Statement 对象来将 SQL 语句发送到数据库。
			 */
			// 执行数据库查询语句
			rs = stmt.executeQuery(sql);
			/**
			 * ResultSet executeQuery(String sql) throws SQLException 执行给定的 SQL 语句，该语句返回单个
			 * ResultSet 对象
			 */
			while (rs.next())
			{
				int id = rs.getInt("id");
				String name = rs.getString("name");
				// int age = rs.getInt("Sage");
				System.out.println("Sno:" + id + "\tSame:" + name);
			}
			if (rs != null)
			{
				rs.close();
				rs = null;
			}
			if (stmt != null)
			{
				stmt.close();
				stmt = null;
			}
			if (conn != null)
			{
				conn.close();
				conn = null;
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
			System.out.println("数据库连接失败");
		}
	}

	public EchoServer(int port)
	{
		this.port = port;
	}

	public void start() throws Exception
	{
		NioEventLoopGroup group = new NioEventLoopGroup();
		try
		{
			ServerBootstrap b = new ServerBootstrap();
			b.group(group).channel(NioServerSocketChannel.class).localAddress(new InetSocketAddress(port)).childHandler(new ChannelInitializer<SocketChannel>()
			{
				@Override
				public void initChannel(SocketChannel ch) throws Exception
				{
					ch.pipeline().addLast(new EchoServerHandler());
				}
			});

			ChannelFuture f = b.bind().sync();
			System.out.println(EchoServer.class.getName() + " started and listen on " + f.channel().localAddress());
			f.channel().closeFuture().sync();
		} finally
		{
			group.shutdownGracefully().sync();
		}
	}

	public static void main(String[] args) throws Exception
	{
		redissionList();
		method3();
		method1();
		method2();
		// redis
		Jedis redis = new Jedis("localhost");
		// jedis.set("foo", "bar");
		// String value = jedis.get("foo");
		// System.out.println(value);
		// redis
		// redis oo
		// ByteArrayOutputStream bos = new ByteArrayOutputStream();
		// ObjectOutputStream oos = new ObjectOutputStream(bos);
		//
		// Person person = new Person("liudehua" ,22);
		// oos.writeObject(person);
		// byte[] byteArray = bos.toByteArray();
		// oos.close();
		// bos.close();
		// String setObjectRet = redis.set("mingyuan".getBytes(), byteArray);
		// System.out.println(" set object return \t" + setObjectRet);
		// byte[] bs = redis.get("mingyuan".getBytes());
		// ByteArrayInputStream bis = new ByteArrayInputStream(bs);
		// ObjectInputStream inputStream = new ObjectInputStream(bis);
		// Person readObject = (Person) inputStream.readObject();
		// System.out.println(" read object \t" + readObject.toString());
		// inputStream.close();
		// bis.close();
		// redis oo
		/*
		 * if (args.length != 1) { System.err.println( "Usage: " +
		 * EchoServer.class.getSimpleName() + " <port>"); return; }
		 */

		Message message = new Message("1", "这是第" + "1" + "个内容");
		redis.lpush("MsgList", MessageUtil.convertToString(message, "utf-8"));

		byte[] bytes = redis.rpop("MsgList".getBytes());

		Message msg = (Message) MessageUtil.convertToMessage(bytes);

		// int port = Integer.parseInt(args[0]);
		int port = 5112;
		new EchoServer(port).start();
	}
}
