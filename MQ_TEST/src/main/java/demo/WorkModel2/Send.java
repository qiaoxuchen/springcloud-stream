package demo.WorkModel2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.nio.charset.StandardCharsets;

/**
 * @Des 工作队列（又名：任务队列）
 *  主要思想：是避免立即执行，资源密集型任务，而不得不等待它完成 （削峰填谷）
 *  工作队列背后的假设：是每个任务都被准确地交付给一个工作人员
 */

public class Send {

    private static final String TASK_QUEUE_NAME = "task_queue";

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
            // 注意事项：RabbitMQ不允许您使用不同的参数重新定义现有队列，并将向任何尝试这样做的程序返回错误
            // 1、设置消息持久化
            boolean durable = true;
            channel.queueDeclare(TASK_QUEUE_NAME, durable, false, false, null);

            for (int i = 10; i > 0; i--) {
                String dots = "";
                for (int j = 0; j <= i; j++) {
                    dots += ".";
                }
                String message = "helloworld" + dots + dots.length();

                // 2、设置消息持久化
                channel.basicPublish("", TASK_QUEUE_NAME,
                        MessageProperties.PERSISTENT_TEXT_PLAIN,
                        message.getBytes(StandardCharsets.UTF_8));
                System.out.println(" [x] Sent '" + message + "'");
            }
        }
    }

}