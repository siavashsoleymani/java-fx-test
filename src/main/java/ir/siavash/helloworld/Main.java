package ir.siavash.helloworld;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import ir.siavash.helloworld.config.RabbitMqConfig;
import ir.siavash.helloworld.gateway.Publisher;
import ir.siavash.helloworld.gateway.TCPPublisher;
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

    private Executor executor = Executors.newFixedThreadPool(2);
    private Publisher publisher = new TCPPublisher("snapptix.ir", 8081);

    public Main() throws IOException, TimeoutException {
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Scene scene = ViewBuilder.build(primaryStage);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> ViewBuilder.buttons.stream()
                .filter(btn -> btn.getText().equalsIgnoreCase(key.getText()))
                .map(btn -> {
                    if (key.getText().equalsIgnoreCase("w")
                            || key.getText().equalsIgnoreCase("s")
                            || key.getText().equalsIgnoreCase(" ")) {
                        executor.execute(() -> {
                            System.out.println(key.getText());
                            try {
                                publisher.publishSpeedCommand(key.getText());
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
                                publisher.publishDirectCommand(key.getText());
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
//                            publisher.publishDirectCommand("f");
                        }
                        ViewBuilder.changeColor(btn, Color.GRAY);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })));
    }

}