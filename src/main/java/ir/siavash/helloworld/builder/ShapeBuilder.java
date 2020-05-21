package ir.siavash.helloworld.builder;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public interface ShapeBuilder<T extends Shape> {
    T buildWithColor(int x, int y, int width, int height, Color color);
    T build();
}
