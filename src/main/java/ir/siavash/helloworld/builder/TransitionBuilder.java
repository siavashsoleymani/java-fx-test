package ir.siavash.helloworld.builder;

import javafx.animation.PathTransition;
import javafx.scene.Node;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public interface TransitionBuilder<T> {
    T build(Path path, Duration duration,
            Node node, PathTransition.OrientationType orientationType,
            int cycleCount);
}
