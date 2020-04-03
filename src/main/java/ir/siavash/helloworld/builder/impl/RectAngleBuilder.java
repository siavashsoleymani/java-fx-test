package ir.siavash.helloworld.builder.impl;

import ir.siavash.helloworld.builder.ShapeBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectAngleBuilder implements ShapeBuilder<Rectangle> {
    @Override
    public Rectangle buildWithColor(int x, int y, int width, int height, Color color) {
        Rectangle rect = new Rectangle(x, y, width, height);
        rect.setArcHeight(20);
        rect.setArcWidth(20);
        rect.setFill(color);
        return rect;
    }

    @Override
    public Rectangle build() {
        Rectangle rect = new Rectangle(20, 20, 150, 150);
        rect.setArcHeight(0);
        rect.setArcWidth(0);
        rect.setFill(Color.GRAY);
        return rect;
    }
}
