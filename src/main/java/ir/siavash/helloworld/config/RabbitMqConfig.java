package ir.siavash.helloworld.config;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMqConfig {
    private static Connection CONNECTION_INSTANCE = null;
    private static Channel CHANNEL_INSTANCE = null;

    private RabbitMqConfig() {
        throw new IllegalCallerException();
    }

    public static Connection getConnection() throws IOException, TimeoutException {
        if (CONNECTION_INSTANCE != null) return CONNECTION_INSTANCE;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        factory.setHost("snapptix.ir");
        factory.setPort(5672);
        factory.setAutomaticRecoveryEnabled(true);
        CONNECTION_INSTANCE = factory.newConnection();
        return CONNECTION_INSTANCE;
    }

    public static Channel getChannel() throws IOException, TimeoutException {
        if (CHANNEL_INSTANCE != null) return CHANNEL_INSTANCE;
        CHANNEL_INSTANCE = getConnection().createChannel();
        return CHANNEL_INSTANCE;
    }
}
