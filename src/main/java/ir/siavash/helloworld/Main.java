package ir.siavash.helloworld;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import ir.siavash.helloworld.config.RabbitMqConfig;
import ir.siavash.helloworld.view.ViewBuilder;
import javafx.application.Application;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

public class Main extends Application {

    private final Channel channel = RabbitMqConfig.getChannel();
    private static final String exchangeName = "pickapp-exchange";
    private static final String speedRoutingKey = "pickapp-speed-routingkey";
    private static final String directRoutingKey = "pickapp-direct-routingkey";
    private static final String messageExpiration = "250";
    private Executor executor = Executors.newFixedThreadPool(2);

    public Main() throws IOException, TimeoutException {
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        channel.exchangeDeclare(exchangeName, "direct", true);
        Scene scene = ViewBuilder.build(primaryStage);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> ViewBuilder.buttons.stream()
                .filter(btn -> btn.getText().equalsIgnoreCase(key.getText()))
                .map(btn -> {
                    byte[] messageBodyBytes = key.getText().getBytes();
                    if (key.getText().equalsIgnoreCase("w")
                            || key.getText().equalsIgnoreCase("s")
                            || key.getText().equalsIgnoreCase(" ")) {
                        executor.execute(() -> {
                            System.out.println(key.getText());
                            try {
                                channel.basicPublish(exchangeName, speedRoutingKey, new AMQP.BasicProperties.Builder()
                                        .expiration(messageExpiration)
                                        .build(), messageBodyBytes);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    } else if (key.getText().equalsIgnoreCase("a")
                            || key.getText().equalsIgnoreCase("d")
                            || key.getText().equalsIgnoreCase("f"))
                        executor.execute(() -> {
                            System.out.println(key.getText());
                            try {
                                channel.basicPublish(exchangeName, directRoutingKey, new AMQP.BasicProperties.Builder()
                                        .expiration(messageExpiration)
                                        .build(), messageBodyBytes);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    return btn;
                })
                .forEach(btn -> ViewBuilder.changeColor(btn, Color.WHITE)));


        scene.addEventHandler(KeyEvent.KEY_RELEASED, (key -> ViewBuilder.buttons.stream()
                .filter(btn -> btn.getText().equalsIgnoreCase(key.getText()))
                .forEach(btn -> {
                    try {
                        if (btn.getText().equalsIgnoreCase("a")
                                || btn.getText().equalsIgnoreCase("d")
                                || btn.getText().equalsIgnoreCase("f")) {
                            channel.basicPublish(exchangeName, directRoutingKey, new AMQP.BasicProperties.Builder()
                                    .expiration(messageExpiration)
                                    .build(), "f".getBytes());
                        }
                        ViewBuilder.changeColor(btn, Color.GRAY);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })));
    }

}