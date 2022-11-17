import java.awt.*;

public interface Shape {
    public void draw(Graphics canvas);

    public void setPosition(Point position);
    public Point getPosition();

    public void setColor(Color color);
    public Color getColor();
    public void setFillColor(Color color);
    public Color getFillColor();
}
