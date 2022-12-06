package shapes;

import java.awt.*;

public abstract class AbstractShapeClass implements Shape, Moveable{
    private Color borderColor;
    private Color fillColor;
    private Point position;
    private Point draggingPoint;

    public AbstractShapeClass(Point position) {
        this.position = position;
    }

    public Color getColor() {
        return borderColor;
    }

    public void setColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getDraggingPoint() {
        return draggingPoint;
    }

    public void setDraggingPoint(Point draggingPoint) {
        this.draggingPoint = draggingPoint;
    }
}
