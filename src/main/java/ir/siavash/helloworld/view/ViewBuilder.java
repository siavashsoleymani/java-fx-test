package ir.siavash.helloworld.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ViewBuilder {

    public static List<Button> buttons = new ArrayList<>();

    public static Scene build(Stage primaryStage) {
        primaryStage.setTitle("PickApp");
        final Pane root = new Pane();
        Button increaseSpeed = getButton("W");
        Button decreaseSpeed = getButton("S");
        Button rightSteer = getButton("D");
        Button leftSteer = getButton("A");
        Button middleSteer = getButton("F");
        Button space = createSpaceButton();
        changeColor(space, Color.GRAY);
        VBox.setMargin(increaseSpeed, new Insets(50, 0, 0, 0));
        HBox hBox = new HBox();
        hBox.getChildren().addAll(leftSteer, decreaseSpeed, rightSteer);
        VBox container = new VBox();
        container.getChildren().addAll(increaseSpeed, hBox, space);
        HBox.setMargin(leftSteer, new Insets(5, 0, 0, 50));
        HBox.setMargin(decreaseSpeed, new Insets(5, 0, 0, 2));
        HBox.setMargin(rightSteer, new Insets(5, 0, 0, 2));
        VBox.setMargin(increaseSpeed, new Insets(5, 0, 0, 70));
        VBox.setMargin(space, new Insets(5, 0, 0, 35));
        root.getChildren().addAll(container);
        Scene scene = new Scene(root, 240, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
        return scene;
    }

    public static Button createSpaceButton() {
        Button space = new Button(" ");
        space.setMinWidth(150);
        space.setMaxWidth(150);
        space.setMinHeight(30);
        space.setMaxHeight(30);
        space.setDisable(true);
        buttons.add(space);
        return space;
    }

    public static void changeColor(Button rightSteer, Color color) {
        rightSteer.setBackground(new Background(new BackgroundFill(color,
                CornerRadii.EMPTY,
                Insets.EMPTY)));
    }

    public static Button getButton(String text) {
        Button button = new Button(text);
        button.setMinWidth(40);
        button.setMaxWidth(40);
        button.setMaxHeight(40);
        button.setMaxHeight(40);
        button.setDisable(true);
        changeColor(button, Color.GRAY);
        buttons.add(button);
        return button;
    }
}
