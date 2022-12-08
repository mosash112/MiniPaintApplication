package shapes;

import java.awt.*;

public interface Shape {
    public void draw(Graphics canvas);

    public void setPosition(Point position);
    public Point getPosition();

    public void setColor(Color borderColor);
    public Color getColor();
    public void setFillColor(Color fillColor);
    public Color getFillColor();

    public Point[] getPoints();
    public int containWidth();
    public int containHeight();
}
