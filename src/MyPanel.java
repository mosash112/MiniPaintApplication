import shapes.AbstractShapeClass;
import shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyPanel extends JPanel {
    AbstractShapeClass[] shapes;
    Gui gui;

    public MyPanel(Gui gui) {
        this.gui = gui;
    }

    public void setShapes(AbstractShapeClass[] shapes) {
        this.shapes = shapes;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        try {
            shapes = gui.engine.getShapes();
            System.out.println("Drawing.....");
            for (shapes.Shape s:shapes) {
//            System.out.println(s);
                s.draw(g);
            }
        }catch (NullPointerException e){
            System.out.println("Couldn't retrieve shapes.\nTrying again ...");
        }
    }
}
