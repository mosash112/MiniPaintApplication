import java.awt.*;

public interface DrawingEngine {
    public void addShape(Shape shape);
    public void removeShape(Shape shape);

    public Shape[] getShapes();

    public void refresh(Graphics canvas);
}
