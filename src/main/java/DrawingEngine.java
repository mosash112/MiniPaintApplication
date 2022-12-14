import shapes.*;

import java.awt.*;

public interface DrawingEngine {
    public void addShape(AbstractShapeClass shape);
    public void removeShape(AbstractShapeClass shape);

    public AbstractShapeClass[] getShapes();

    public void refresh(Graphics canvas);
}
