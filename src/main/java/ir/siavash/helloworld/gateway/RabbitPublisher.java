package ir.siavash.helloworld.gateway;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import ir.siavash.helloworld.config.RabbitMqConfig;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitPublisher implements Publisher{
    private final Channel channel = RabbitMqConfig.getChannel();
    private static final String exchangeName = "pickapp-exchange";
    private static final String speedRoutingKey = "pickapp-speed-routingkey";
    private static final String directRoutingKey = "pickapp-direct-routingkey";
    private static final String messageExpiration = "250";


    public RabbitPublisher() throws IOException, TimeoutException {
        channel.exchangeDeclare(exchangeName, "direct", true);
    }

    @Override
    public void publishDirectCommand(String message) throws IOException {
        channel.basicPublish(exchangeName, directRoutingKey, new AMQP.BasicProperties.Builder()
                .expiration(messageExpiration)
                .build(), message.getBytes());
    }

    @Override
    public void publishSpeedCommand(String message) throws IOException {
        channel.basicPublish(exchangeName, speedRoutingKey, new AMQP.BasicProperties.Builder()
                .expiration(messageExpiration)
                .build(), message.getBytes());
    }
}
