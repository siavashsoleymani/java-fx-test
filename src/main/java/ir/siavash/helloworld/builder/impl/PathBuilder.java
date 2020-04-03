package ir.siavash.helloworld.builder.impl;

import ir.siavash.helloworld.builder.ShapeBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;


public class PathBuilder implements ShapeBuilder<Path> {
    @Override
    public Path buildWithColor(int x, int y, int width, int height, Color color) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Path build() {
        Path path = new Path();
        path.getElements().add(new MoveTo(400,320));
//        path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 200, 120));
        path.getElements().add(new CubicCurveTo(100, 120, 0, 1000, 360, 360));
        path.getElements().add(new CubicCurveTo(100, 120, 0, 1000, 360, 360));
        return path;
    }
}
