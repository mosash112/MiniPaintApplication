package shapes;

import java.awt.*;
import java.lang.Math;

public class Circle extends AbstractShapeClass{
    private int radius;

    public Circle(Point position, int radius) {
        super(position);
        this.radius = radius;
    }

//    drawing a circle with its center on coordinates x, y
    public void draw(Graphics canvas) {
            canvas.setColor(this.getFillColor());
            canvas.fillOval((int) this.getPosition().getX()-(this.radius/2), (int) this.getPosition().getY()-(this.radius/2), this.radius, this.radius);
            canvas.setColor(this.getColor());
            canvas.drawOval((int)this.getPosition().getX()-(this.radius/2), (int)this.getPosition().getY()-(this.radius/2), this.radius, this.radius);
    }

    public boolean contains(Point point){
        int dist = (int) Math.ceil(Math.sqrt(Math.pow((this.getPosition().getX()-point.getX()),2)+Math.pow((this.getPosition().getY()-point.getY()),2)));
        boolean contain = false;
        this.setDraggingPoint(point);
        if (dist<=(this.radius/2))
            contain = true;
        return contain;
    }

    public void moveTo(Point point){
        Point old = this.getDraggingPoint(), oldpos = this.getPosition();
        int xdiff = (int) (point.getX()-old.getX()), ydiff = (int) (point.getY()-old.getY());
        this.setDraggingPoint(point);
        this.setPosition(new Point((int) (oldpos.getX()+xdiff), (int) (oldpos.getY()+ydiff)));
//        System.out.println("old: "+oldpos.getX()+", "+oldpos.getY()+" diff: "+xdiff+", "+ydiff+" new: "+(oldpos.getX()+xdiff)+", "+(oldpos.getY()+ydiff));
    }
}
