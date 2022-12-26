package demo.PublishSubscribe3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


/**
 * 发布者将消息发布到交换机，交换再与队列绑定；
 * 当消费者要消费消息时，也需要绑定交换机，并只能接收到来自同种类型的交换机的消息
 * channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
 * 交换机的四种类型：direct、topic、headers 和fanout
 * direct：处理路由键 要求消费者的路由键与生产者完全匹配
 * topic：处理路由键  将生产者的路由键与消费者绑定交换机的路由键绑定到同一种模式上，模式：#  *
 *  eg：channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"item.#");   //使用item.# 匹配所有的以item开头的
 * headers： 不处理路由键  通过Headers进行匹配，Headers是一个键值对，需要与生产者的键值对匹配才可以发送
 *  eg：Map<String, Object> headers = new Hashtable<String, Object>();
 * 		headers.put("x-match", "any");//all：必须所有键值对都匹配 any：有一个匹配就可以
 * 		headers.put("aaa", "01234");
 * 		headers.put("bbb", "56789");
 * 		// 为转发器指定队列，设置binding 绑定header键值对
 * 		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME,"", headers);
 * fanout：不处理路由键 相当于广播模式，消费者只要绑定了生产者相同类型的交换机，就会接收到消息    Fanout交换机转发消息是最快的
 */

public class EmitLog {

    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setUsername("123456");
        factory.setPassword("123456");
        factory.setPort(5672);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            // 创建交换机
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

            String message = argv.length < 1 ? "info: Hello World!" :
                    String.join(" ", argv);

            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }

}