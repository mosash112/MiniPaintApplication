import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import shapes.Rectangle;
import shapes.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Engine implements DrawingEngine{
    ArrayList<AbstractShapeClass> shps = new ArrayList<>();
    ArrayList<String> nms = new ArrayList<>();
    ArrayList<Rectangle> corners = new ArrayList<>();
    int counter = 0;
    Gui gui;

    public Engine() {}

    @Override
    public void addShape(AbstractShapeClass shape) {
        print(shape);
        shape.draw(gui.drwcanv.getGraphics());
    }

    public void print(AbstractShapeClass shape){
        System.out.println("-----------------");
        System.out.println(shape);
        System.out.println("pos: "+shape.getPosition().getX()+", "+shape.getPosition().getY());
        System.out.println("color: "+shape.getColor());
        System.out.println("fill color: "+shape.getFillColor());
        System.out.println("-----------------");
    }

    public int selectShape(Point point){
        int flag = 0, index = -1;
        for (AbstractShapeClass s:shps) {
            if (s.contains(point)) {
                index = shps.indexOf(s);
//                System.out.println(selected);
            }
        }
        if(index >= 0){
            AbstractShapeClass shape = shps.get(index);
            if (shape.getClass().isAssignableFrom(Oval.class)) {
                flag = 1;
            }else if (shape.getClass().isAssignableFrom(LineSegment.class)) {
                flag = 2;
            }else if (shape.getClass().isAssignableFrom(Triangle.class)) {
                flag = 3;
            }else if (shape.getClass().isAssignableFrom(Rectangle.class)) {
                flag = 4;
            }
            corners(flag, shape, shape.getPoints());
            drawCorners(gui.drwcanv.getGraphics());
        }
        return index;
    }

    @Override
    public void removeShape(AbstractShapeClass shape) {
        int index = shps.indexOf(shape);
//        System.out.println("index "+index+" removing selected "+shape.getClass());
        nms.remove(nms.get(index));
        shps.remove(shape);
        resetSelection();
    }

    @Override
    public AbstractShapeClass[] getShapes() {
        return shps.toArray(new AbstractShapeClass[0]);
    }

    @Override
    public void refresh(Graphics canvas) {
//        System.out.println("refreshing....");
        gui.drwcanv.repaint();
    }

    public void newShape(int flag, Point[] ps, Color clr, Color fclr, int hei, int wid) {
        AbstractShapeClass shape = null;
        counter++;
        System.out.println(counter);
        switch (flag) {
            case 1 -> {
                shape = new Oval(ps[0], hei, wid);
                nms.add("circle_" + counter);
            }
            case 2 -> {
                shape = new LineSegment(ps[0], ps[1]);
                nms.add("line_" + counter);
            }
            case 3 -> {
                shape = new Triangle(ps[0], ps[1], ps[2]);
                nms.add("triangle_" + counter);
            }
            case 4 -> {
                shape = new Rectangle(ps[0], hei, wid);
                nms.add("rect_" + counter);
            }
        }
        shape.setColor(clr);
        shape.setFillColor(fclr);
        shps.add(shape);
        addShape(shape);
    }

    public void corners(int flag, AbstractShapeClass shape, Point[] points){
        int len = 10;
        if (corners.isEmpty()) {
            for (int i = 0; i < 4; i++)
                corners.add(new Rectangle(new Point(-(len+5), -(len+5)), len, len));
            drawCorners(gui.drwcanv.getGraphics());
        }else {
            switch (flag) {
                case 1, 4 -> {
                    corners.get(0).setPosition(points[0]);
                    corners.get(1).setPosition(new Point((int) points[1].getX()-len, (int) points[1].getY()));
                    corners.get(2).setPosition(new Point((int) points[2].getX(), (int) points[2].getY()-len));
                    corners.get(3).setPosition(new Point((int) points[3].getX()-len, (int) points[3].getY()-len));
                }
                case 2 -> {
                    corners.get(0).setPosition(new Point((int) points[0].getX() - 5, (int) points[0].getY() - 5));
                    corners.get(1).setPosition(new Point((int) points[1].getX() - 5, (int) points[1].getY() - 5));
                    corners.get(2).setPosition(new Point(-10, -10));
                    corners.get(3).setPosition(new Point(-10, -10));
                }
                case 3 -> {
                    corners.get(0).setPosition(new Point((int) points[0].getX() - 5, (int) points[0].getY() - 5));
                    corners.get(1).setPosition(new Point((int) points[1].getX() - 5, (int) points[1].getY() - 5));
                    corners.get(2).setPosition(new Point((int) points[2].getX() - 5, (int) points[2].getY() - 5));
                    corners.get(3).setPosition(new Point(-10, -10));
                }
            }
        }
        refresh(gui.drwcanv.getGraphics());
    }

    public void drawShapes(Graphics g){
        for (shapes.Shape s:shps) {
            s.draw(g);
        }
    }

    public void drawCorners(Graphics g){
        g.setColor(Color.BLACK);
        for (Rectangle c:corners) {
            c.draw(g);
        }
    }

    public void resetSelection(){
        for (Rectangle r:corners)
            r.setPosition(new Point(-10,-10));
    }

    public boolean saveToJson(String f){
        JSONArray shapes = new JSONArray();
        boolean saved = true;
        File file=new File(f);
            try {
                FileWriter fileWriter = null;
                System.out.println("Writing JSON objects to file");
                System.out.println("-----------------------");
                file.createNewFile();
                fileWriter = new FileWriter(file);
                for (AbstractShapeClass s : shps) {
                    JSONObject obj = new JSONObject();
                    obj.put("shape", s.toJson());
                    shapes.add(obj);
                }
                System.out.println("shapes jason array: \n"+shapes);
                fileWriter.write(shapes.toJSONString());
                fileWriter.flush();
                fileWriter.close();
            } catch (Exception e) {
                System.out.println("Couldn't save file");
                saved = false;
            }
        return saved;
    }

    public boolean loadFromJson(String f){
        clean();
        JSONParser parser = new JSONParser();
        boolean loaded = true;
        try (Reader reader = new FileReader(f)) {
            JSONArray arr = (JSONArray) parser.parse(reader);
            System.out.println("reading JSON file:\n"+arr);
            for (Object obj:arr) {
                fromJson((JSONObject) obj);
            }
        } catch (Exception e) {
            System.out.println("Couldn't load file");
            e.printStackTrace();
            loaded = false;
        }
        gui.updateExistShapes();
        return loaded;
    }

    public void fromJson(JSONObject obj){
        int flag = 0, hei = 0, wid = 0;
        Point[] ps = new Point[3];

        JSONObject shape = (JSONObject) obj.get("shape");

        String type = (String) shape.get("type");
        System.out.println(type);
        switch (type) {
            case "shapes.Oval" -> flag = 1;
            case "shapes.LineSegment" -> flag = 2;
            case "shapes.Triangle" -> flag = 3;
            case "shapes.Rectangle" -> flag = 4;
        }

        JSONObject pos = (JSONObject) shape.get("position");
        System.out.println(pos.get("posx").getClass());
        ps[0] = new Point((int) Math.round((Double) pos.get("posx")), (int) Math.round((Double) pos.get("posy")));

        if (shape.containsKey("point2")) {
            JSONObject p2 = (JSONObject) shape.get("point2");
            ps[1] = new Point((int) Math.round((Double) p2.get("p2x")), (int) Math.round((Double) p2.get("p2y")));
        }
        if (shape.containsKey("point3")) {
            JSONObject p3 = (JSONObject) shape.get("point3");
            ps[2] = new Point((int) Math.round((Double) p3.get("p3x")), (int) Math.round((Double) p3.get("p3y")));
        }

        JSONObject border = (JSONObject) shape.get("border color");
        System.out.println(border);

        if (shape.containsKey("height")) {
            hei = Math.toIntExact((Long) shape.get("height"));
            System.out.println(hei);
        }
        if (shape.containsKey("width")) {
            wid = Math.toIntExact((Long) shape.get("width"));
            System.out.println(wid);
        }
        if (shape.containsKey("fill color")) {
            JSONObject fill = (JSONObject) shape.get("fill color");
            System.out.println(fill);
            newShape(flag,ps,new Color(Math.toIntExact((Long)  border.get("red")), Math.toIntExact((Long) border.get("green")), Math.toIntExact((Long) border.get("blue"))),new Color(Math.toIntExact((Long) fill.get("red")), Math.toIntExact((Long) fill.get("green")), Math.toIntExact((Long) fill.get("blue"))),hei,wid);
        }else
            newShape(flag,ps,new Color(Math.toIntExact((Long) border.get("red")), Math.toIntExact((Long) border.get("green")), Math.toIntExact((Long) border.get("blue"))),gui.trans,hei,wid);
    }

    public void copyShape(int index){
        AbstractShapeClass shape, copy;
        int flag = 0;
        copy = shps.get(index);
        print(copy);
        if (copy.getClass().isAssignableFrom(Oval.class)) {
            flag = 1;
        }else if (copy.getClass().isAssignableFrom(LineSegment.class)) {
            flag = 2;
        }else if (copy.getClass().isAssignableFrom(Triangle.class)) {
            flag = 3;
        }else if (copy.getClass().isAssignableFrom(Rectangle.class)) {
            flag = 4;
        }
        newShape(flag, copy.getPoints(), copy.getColor(), copy.getFillColor(), copy.containHeight(), copy.containWidth());
        refresh(gui.drwcanv.getGraphics());
    }

    public void clean(){
        shps.clear();
        nms.clear();
        refresh(gui.drwcanv.getGraphics());
        gui.updateExistShapes();
    }

    public void resizeShape(Point point, int corner){
        int flag = 0;
        if (selectShape(point)>=0) {
            AbstractShapeClass shape = shps.get(selectShape(point));
            shape.resize(point, corner);
            if (shape.getClass().isAssignableFrom(Oval.class)) {
                flag = 1;
            } else if (shape.getClass().isAssignableFrom(LineSegment.class)) {
                flag = 2;
            } else if (shape.getClass().isAssignableFrom(Triangle.class)) {
                flag = 3;
            } else if (shape.getClass().isAssignableFrom(Rectangle.class)) {
                flag = 4;
            }
            corners(flag, shape, shape.getPoints());
            refresh(gui.drwcanv.getGraphics());
        }
    }
}
