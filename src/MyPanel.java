import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyPanel extends JPanel {
    ArrayList<Shape> shapes = new ArrayList<>();
    Gui gui;

    public MyPanel(Gui gui,ArrayList<Shape> shapes) {
        this.shapes = shapes;
        this.gui = gui;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
//        shapes.clear();
        shapes = gui.shapes;
        System.out.println("Drawing.....");
        for (Shape s:shapes) {
//            System.out.println(s);
            s.draw(g);
        }
    }
}
