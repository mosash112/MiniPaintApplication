import java.awt.*;

public class Circle implements Shape{
    private Point position;
    private Color color;
    private Color fillColor;
    private double radius;

    public Circle(Point position, Color color, Color fillColor, double radius) {
        this.position = position;
        this.color = color;
        this.fillColor = fillColor;
        this.radius = radius;
    }

    @Override
    public void draw(Graphics canvas) {
            canvas.setColor(this.getFillColor());
            canvas.fillOval((int) this.getPosition().getX(), (int) this.getPosition().getY(), (int) this.getRadius(), (int) this.getRadius());
            canvas.setColor(this.getColor());
            canvas.drawOval((int)this.getPosition().getX(),(int)this.getPosition().getY(),(int)this.getRadius(),(int)this.getRadius());
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public Point getPosition() {
        return this.position;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public void setFillColor(Color color) {
        this.fillColor = color;
    }

    @Override
    public Color getFillColor() {
        return this.fillColor;
    }
}
