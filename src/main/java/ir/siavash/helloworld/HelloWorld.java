package ir.siavash.helloworld;

import ir.siavash.helloworld.builder.ShapeBuilder;
import ir.siavash.helloworld.builder.TransitionBuilder;
import ir.siavash.helloworld.builder.impl.PathBuilder;
import ir.siavash.helloworld.builder.impl.PathTransitionBuilder;
import ir.siavash.helloworld.builder.impl.RectAngleBuilder;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HelloWorld extends Application {

	private final ShapeBuilder<Rectangle> rectAngleBuilder = new RectAngleBuilder();
	private final ShapeBuilder<Path> pathBuilder = new PathBuilder();
	private final TransitionBuilder<PathTransition> pathTransitionBuilder = new PathTransitionBuilder();

	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Test");
		final Group root = new Group();
		final Rectangle rect = rectAngleBuilder.buildWithColor(80, 80, 100, 100, Color.RED);
		rect.setFill(new ImagePattern(new Image("file:/home/siavash/Downloads/dog.png")));
		Path path = pathBuilder.build();
		PathTransition pathTransition = pathTransitionBuilder.build(path, Duration.millis(4000),
				rect, PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT, Timeline.INDEFINITE);
		root.getChildren().addAll(rect);
		primaryStage.setScene(new Scene(root, 240, 70));
		primaryStage.show();
		pathTransition.play();
	}

}