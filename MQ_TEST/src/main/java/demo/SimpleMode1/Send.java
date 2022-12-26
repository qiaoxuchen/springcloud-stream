package demo.SimpleMode1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;


public class Send {


    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        // 创建工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setUsername("123456");
        factory.setPassword("123456");
        factory.setPort(5672);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            //定义队列(使用Java代码在MQ中新建一个队列)
            //参数1：定义的队列名称
            //参数2：队列中的数据是否持久化（如果选择了持久化）
            //参数3: 是否排外（当前队列是否为当前连接私有）
            //参数4：自动删除（当此队列的连接数为0时，此队列会销毁（无论队列中是否还有数据））
            //参数5：设置当前队列的参数
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World!111";
            //参数1：交换机名称，如果直接发送信息到队列，则交换机名称为""
            //参数2：目标队列名称
            //参数3：设置当前这条消息的属性（设置过期时间 10）
            //参数4：消息的内容
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        }

    }
}