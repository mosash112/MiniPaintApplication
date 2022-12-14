package shapes;

import org.json.simple.JSONObject;
import java.awt.*;

public abstract class AbstractShapeClass implements Shape, Moveable{
    private Color borderColor;
    private Color fillColor;
    private Point position;
    private Point draggingPoint;
    private Point[] points = new Point[4];

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

    public JSONObject toJson(){
        JSONObject shape = new JSONObject(),
                pos = new JSONObject(),
                p2 = new JSONObject(),
                p3 = new JSONObject(),
                border = new JSONObject(),
                fill = new JSONObject();

        try {
            shape.put("type",this.getClass().getName());

            pos.put("posx",this.getPosition().getX());
            pos.put("posy",this.getPosition().getY());
            shape.put("position", pos);

            if (this.getPoints()[1] != null) {
                p2.put("p2x",this.getPoints()[1].getX());
                p2.put("p2y",this.getPoints()[1].getY());
                shape.put("point2",p2);
            }

            if (this.getPoints()[2] != null) {
                p3.put("p3x",this.getPoints()[2].getX());
                p3.put("p3y",this.getPoints()[2].getX());
                shape.put("point3",p3);
            }

            border.put("red",this.getColor().getRed());
            border.put("green",this.getColor().getGreen());
            border.put("blue",this.getColor().getBlue());
            shape.put("border color", border);
            if (this.getFillColor() != null) {
                fill.put("red",this.getFillColor().getRed());
                fill.put("green",this.getFillColor().getGreen());
                fill.put("blue",this.getFillColor().getBlue());
                shape.put("fill color", fill);
            }
            if (this.containHeight() != 0)
                shape.put("height",this.containHeight());
            if (this.containWidth() != 0)
                shape.put("width",this.containWidth());
        } catch (Exception e) {
            System.out.println("Couldn't convert shape to Json");
            System.out.println(e);
        }
        return shape;
    }

    public Point[] getPoints(){
        return points;
    }
}
