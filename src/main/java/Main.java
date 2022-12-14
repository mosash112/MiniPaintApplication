import shapes.AbstractShapeClass;
import shapes.LineSegment;

import java.awt.*;

public class Main {
    static Engine engine = new Engine();
    static Gui gui = new Gui();
    public static void main(String[] args) {
        engine.gui = gui;
        gui.engine = engine;
    }
}
