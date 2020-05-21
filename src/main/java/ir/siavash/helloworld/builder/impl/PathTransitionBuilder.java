package ir.siavash.helloworld.builder.impl;

import ir.siavash.helloworld.builder.TransitionBuilder;
import javafx.animation.PathTransition;
import javafx.scene.Node;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class PathTransitionBuilder implements TransitionBuilder<PathTransition> {
    @Override
    public PathTransition build(Path path, Duration duration,
                                Node node, PathTransition.OrientationType orientationType,
                                int cycleCount) {
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(duration);
        pathTransition.setPath(path);
        pathTransition.setNode(node);
        pathTransition.setOrientation(orientationType);
        pathTransition.setCycleCount(cycleCount);
        pathTransition.setAutoReverse(true);
        return pathTransition;
    }
}
