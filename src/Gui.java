import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Gui{

    Engine engine;
    String[] names = {"--Choose Shape--"},colors = {"--Choose Color--","GREEN","RED","WHITE","BLACK","BLUE","CYAN","DARK_GRAY","GRAY","MAGENTA","ORANGE","PINK","YELLOW"};
    ArrayList<Shape> shapes = new ArrayList<>();
    Color ncolor, trans = new Color(0,0,0,0);
    int flag = 0;

    private final JFrame jf = new JFrame();
    private final JPanel jp = new JPanel();
    private final GridBagConstraints c = new GridBagConstraints();

    private final JLabel shapelbl = new JLabel("Select Shape");
    final JComboBox<String> shpcombox = new JComboBox<>(names);
    private final JButton clrbtn = new JButton("Colorize");
    private final JButton delbtn = new JButton("Delete");
    private final JButton crclbtn = new JButton("Circle");
    private final JButton lnbtn = new JButton("Line Segment");
    private final JButton sqrbtn = new JButton("Square");
    private final JButton recbtn = new JButton("Rectangle");
    final MyPanel drwcanv = new MyPanel(this,shapes);

    private final JFrame cf = new JFrame();
    private final JPanel cp = new JPanel();

    private final JButton btn1 = new JButton("Green");
    private final JButton btn2 = new JButton("Red");
    private final JButton btn3 = new JButton("White");
    private final JButton btn4 = new JButton("Black");
    private final JButton btn5 = new JButton("Blue");
    private final JButton btn6 = new JButton("Cyan");
    private final JButton btn7 = new JButton("Dark Grey");
    private final JButton btn8 = new JButton("Gray");
    private final JButton btn9 = new JButton("Magenta");
    private final JButton btn10 = new JButton("Orange");
    private final JButton btn11 = new JButton("Pink");
    private final JButton btn12 = new JButton("Yellow");
    private final JLabel sname = new JLabel();
    private final JRadioButton edgebtn = new JRadioButton("Edge color");
    private final JRadioButton fillbtn = new JRadioButton("Fill color");
    final ButtonGroup group = new ButtonGroup();

    private final JFrame af = new JFrame();
    private final JPanel ap = new JPanel();

    private final JLabel poslbl = new JLabel("Position");
    private final JLabel posxlbl = new JLabel("X :");
    private final JLabel posylbl = new JLabel("Y :");
    private final JLabel x2lbl = new JLabel("point 2 X :");
    private final JLabel y2lbl = new JLabel("point 2 Y :");
    private final JLabel clrlbl = new JLabel("Color :");
    private final JLabel fclrlbl = new JLabel("Fill Color :");
    private final JLabel radlbl = new JLabel("Radius :");
    private final JLabel widlbl = new JLabel("Width :");
    private final JTextField posxtxt = new JTextField(10);
    private final JTextField posytxt = new JTextField(10);
    private final JTextField x2txt = new JTextField(10);
    private final JTextField y2txt = new JTextField(10);
    private final JComboBox<String> clrcombox = new JComboBox<>(colors);
    private final JComboBox<String> fclrcombox = new JComboBox<>(colors);
    private final JTextField radtxt = new JTextField(10);
    private final JTextField widtxt = new JTextField(10);

    private final JButton createbtn = new JButton("Create");

    public Gui() {
        window();
    }

    public void window(){
        jp.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));
        jp.setLayout(new GridBagLayout());
        cp.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));
        cp.setLayout(new GridBagLayout());
        ap.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));
        ap.setLayout(new GridBagLayout());

        c.insets = new Insets(3,3,3,3);
        c.gridx = 0;
        c.gridy = 2;
        jp.add(shapelbl,c);

        shpcombox.setMaximumSize(shpcombox.getPreferredSize());
        shpcombox.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.gridx = 0;
        c.gridy = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        jp.add(shpcombox,c);

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.LINE_START;
        jp.add(clrbtn,c);

        c.gridx = 1;
        c.gridy = 4;
        jp.add(delbtn,c);

        c.gridx = 2;
        c.gridy = 0;
        jp.add(crclbtn,c);

        c.gridx = 3;
        c.gridy = 0;
        jp.add(lnbtn,c);

        c.gridx = 4;
        c.gridy = 0;
        jp.add(sqrbtn,c);

        c.gridx = 5;
        c.gridy = 0;
        jp.add(recbtn,c);

        drwcanv.setBackground(Color.WHITE);
        drwcanv.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 4;
        c.gridheight = 5;
        c.ipady = 200;
        c.ipadx = 400;
        c.fill = GridBagConstraints.BOTH;
        jp.add(drwcanv,c);

//        reset constraint values
        c.gridheight = 1;
        c.ipady = 0;
        c.ipadx = 0;

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        cp.add(sname,c);

        group.add(edgebtn);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        cp.add(edgebtn,c);

        group.add(fillbtn);
        c.gridx = 2;
        c.gridy = 1;
        cp.add(fillbtn,c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        btn1.setBackground(Color.GREEN);
        cp.add(btn1,c);

        c.gridx = 1;
        c.gridy = 2;
        btn2.setBackground(Color.RED);
        cp.add(btn2,c);

        c.gridx = 2;
        c.gridy = 2;
        btn3.setBackground(Color.WHITE);
        cp.add(btn3,c);

        c.gridx = 3;
        c.gridy = 2;
        btn4.setBackground(Color.BLACK);
        btn4.setForeground(Color.WHITE);
        cp.add(btn4,c);

        c.gridx = 0;
        c.gridy = 3;
        btn5.setBackground(Color.BLUE);
        btn5.setForeground(Color.WHITE);
        cp.add(btn5,c);

        c.gridx = 1;
        c.gridy = 3;
        btn6.setBackground(Color.CYAN);
        cp.add(btn6,c);

        c.gridx = 2;
        c.gridy = 3;
        btn7.setBackground(Color.DARK_GRAY);
        btn7.setForeground(Color.WHITE);
        cp.add(btn7,c);

        c.gridx = 3;
        c.gridy = 3;
        btn8.setBackground(Color.GRAY);
        btn8.setForeground(Color.WHITE);
        cp.add(btn8,c);

        c.gridx = 0;
        c.gridy = 4;
        btn9.setBackground(Color.MAGENTA);
        cp.add(btn9,c);

        c.gridx = 1;
        c.gridy = 4;
        btn10.setBackground(Color.ORANGE);
        cp.add(btn10,c);

        c.gridx = 2;
        c.gridy = 4;
        btn11.setBackground(Color.PINK);
        cp.add(btn11,c);

        c.gridx = 3;
        c.gridy = 4;
        btn12.setBackground(Color.YELLOW);
        cp.add(btn12,c);

        c.gridx = 0;
        c.gridy = 0;
        ap.add(poslbl,c);

        c.gridx = 0;
        c.gridy = 1;
        ap.add(posxlbl,c);

        posxtxt.setMaximumSize(posxtxt.getPreferredSize());
        posxtxt.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.gridx = 1;
        c.gridy = 1;
        ap.add(posxtxt,c);

        c.gridx = 2;
        c.gridy = 1;
        ap.add(posylbl,c);

        posytxt.setMaximumSize(posytxt.getPreferredSize());
        posytxt.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.gridx = 3;
        c.gridy = 1;
        ap.add(posytxt,c);

        c.gridx = 0;
        c.gridy = 2;
        ap.add(x2lbl,c);

        c.gridx = 1;
        c.gridy = 2;
        ap.add(x2txt,c);

        c.gridx = 2;
        c.gridy = 2;
        ap.add(y2lbl,c);

        c.gridx = 3;
        c.gridy = 2;
        ap.add(y2txt,c);

        c.gridx = 0;
        c.gridy = 3;
        ap.add(clrlbl,c);

        clrcombox.setMaximumSize(clrcombox.getPreferredSize());
        clrcombox.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.gridx = 1;
        c.gridy = 3;
        ap.add(clrcombox,c);

        c.gridx = 2;
        c.gridy = 3;
        ap.add(fclrlbl,c);

        fclrcombox.setMaximumSize(fclrcombox.getPreferredSize());
        fclrcombox.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.gridx = 3;
        c.gridy = 3;
        ap.add(fclrcombox,c);

        c.gridx = 0;
        c.gridy = 4;
        ap.add(radlbl,c);

        radtxt.setMaximumSize(radtxt.getPreferredSize());
        radtxt.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.gridx = 1;
        c.gridy = 4;
        ap.add(radtxt,c);

        c.gridx = 2;
        c.gridy = 4;
        ap.add(widlbl,c);

        c.gridx = 3;
        c.gridy = 4;
        ap.add(widtxt,c);

        c.gridx = 0;
        c.gridy = 5;
        c.weightx = 1;
        c.gridwidth = 4;
        ap.add(createbtn,c);

        shpcombox.addActionListener(e -> selectedShape());
        delbtn.addActionListener(e -> deleteButtonPressed());
        clrbtn.addActionListener(e -> colorizeButtonPressed());
        crclbtn.addActionListener(e -> circleButtonPressed());
        lnbtn.addActionListener(e -> lineButtonPressed());
        sqrbtn.addActionListener(e -> squareButtonPressed());
        recbtn.addActionListener(e -> rectangleButtonPressed());
        btn1.addActionListener(e -> {ncolor = btn1.getBackground();setColor();});
        btn2.addActionListener(e -> {ncolor = btn2.getBackground();setColor();});
        btn3.addActionListener(e -> {ncolor = btn3.getBackground();setColor();});
        btn4.addActionListener(e -> {ncolor = btn4.getBackground();setColor();});
        btn5.addActionListener(e -> {ncolor = btn5.getBackground();setColor();});
        btn6.addActionListener(e -> {ncolor = btn6.getBackground();setColor();});
        btn7.addActionListener(e -> {ncolor = btn7.getBackground();setColor();});
        btn8.addActionListener(e -> {ncolor = btn8.getBackground();setColor();});
        btn9.addActionListener(e -> {ncolor = btn9.getBackground();setColor();});
        btn10.addActionListener(e -> {ncolor = btn10.getBackground();setColor();});
        btn11.addActionListener(e -> {ncolor = btn11.getBackground();setColor();});
        btn12.addActionListener(e -> {ncolor = btn12.getBackground();setColor();});

        cf.add(cp,BorderLayout.CENTER);
        cf.setSize(400, 400);
        cf.setLocation(300,400);
        cf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        cf.setTitle("Choose a color");
        cf.pack();
        cf.setVisible(false);

        af.add(ap,BorderLayout.CENTER);
        af.setSize(450, 220);
        af.setLocation(300,400);
        af.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        af.setTitle("Shape attributes");
//        af.pack();
        af.setVisible(false);

        jf.add(jp, BorderLayout.WEST);
        jf.setSize(600,400);
        jf.setLocation(300, 400);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setTitle("Mini-Paint");
        jf.pack();
        jf.setVisible(true);
    }

    public int selectedShape(){
//        if (shpcombox.getSelectedIndex()>0) {
//            System.out.println("item number " + shpcombox.getSelectedIndex() + "\t" + shpcombox.getSelectedItem() + " shape was selected");
//        }
        return shpcombox.getSelectedIndex();
    }

    public void deleteButtonPressed(){
        int index = selectedShape()-1;
        Shape rs;
        if (index>0) {
//            System.out.println(delbtn.getText() + " button was pushed\nitem number " + index + " will be deleted");
            rs = engine.getShapes()[index];
//            System.out.println(rs);
            engine.removeShape(rs);
            shapes.remove(rs);
        }
        updateExistShapes();
        drwcanv.repaint();
    }

    public void colorizeButtonPressed(){
        int index = selectedShape()-1;
//        System.out.println(clrbtn.getText()+" button was pushed");
        cf.setVisible(true);
        sname.setText(engine.nms.get(index)+" shape");
    }

    public void circleButtonPressed(){
        flag = 1;
        createShape();
//        Point p = new Point(50,50);
//        Color clr = new Color(0,0,0,0);
//        Circle c = new Circle(p,Color.BLACK, clr,30);
//        newShape(c);
    }

    public void lineButtonPressed(){
        flag = 2;
        createShape();

//        Point p1 = new Point(50,50);
//        Point p2 = new Point(100,100);
//        LineSegment l = new LineSegment(p1,p2,Color.BLACK);
//        newShape(l);
    }

    public void squareButtonPressed(){
        flag = 3;
        createShape();

//        Point p = new Point(50,50);
//        Color clr = new Color(0,0,0,0);
//        Square sq = new Square(p,Color.BLACK,clr,30);
//        newShape(sq);
    }

    public void rectangleButtonPressed(){
        flag = 4;
        createShape();

//        Point p = new Point(50,50);
//        Color clr = new Color(0,0,0,0);
//        Rectangle r = new Rectangle(p,Color.BLACK,clr,30, 40);
//        newShape(r);
    }

    public void createShape(){
        af.setVisible(true);
        Shape shape = null;
        Color fclr = trans,clr = Color.BLACK;
        Point p1 = null, p2 = null;
        double rad = 0, wid = 0;

        if(!posxtxt.getText().equals(null) && !posytxt.getText().equals(null))
            p1 = new Point(Integer.parseInt(posxtxt.getText()),Integer.parseInt(posytxt.getText()));
        if(x2txt.getText()!=null && y2txt.getText()!=null)
            p2 = new Point(Integer.parseInt(x2txt.getText()),Integer.parseInt(y2txt.getText()));
        if(radtxt.getText()!=null)
            rad = Double.valueOf(radtxt.getText());
        if(widtxt.getText()!=null)
            wid = Double.valueOf(widtxt.getText());
        if(clrcombox.getSelectedItem()!=null)
            clr = engine.clrs.get(clrcombox.getSelectedIndex()-1);
        if(fclrcombox.getSelectedItem()!=null)
            fclr = engine.clrs.get(fclrcombox.getSelectedIndex()-1);

        shape = new Circle(p1,clr, fclr,rad);
        switch (flag){
            case 1:     //circle
                posxlbl.setText("X :");
                posylbl.setText("Y :");
                radlbl.setText("Radius :");
                x2lbl.setEnabled(false);
                y2lbl.setEnabled(false);
                x2txt.setEnabled(false);
                y2txt.setEnabled(false);
                widlbl.setEnabled(false);
                widtxt.setEnabled(false);
                fclrlbl.setEnabled(true);
                fclrcombox.setEnabled(true);
                shape = new Circle(p1,clr, fclr,rad);
                break;
            case 2:     //line
                posxlbl.setText("point 1 X :");
                posylbl.setText("point 1 Y :");
                radlbl.setText("Length :");
                x2lbl.setEnabled(true);
                y2lbl.setEnabled(true);
                x2txt.setEnabled(true);
                y2txt.setEnabled(true);
                fclrlbl.setEnabled(false);
                fclrcombox.setEnabled(false);
                widlbl.setEnabled(false);
                widtxt.setEnabled(false);
                break;
            case 3:     //square
                posxlbl.setText("X :");
                posylbl.setText("Y :");
                radlbl.setText("Length :");
                x2lbl.setEnabled(false);
                y2lbl.setEnabled(false);
                x2txt.setEnabled(false);
                y2txt.setEnabled(false);
                widlbl.setEnabled(false);
                widtxt.setEnabled(false);
                fclrlbl.setEnabled(true);
                fclrcombox.setEnabled(true);
                break;
            case 4:     //rectangle
                posxlbl.setText("X :");
                posylbl.setText("Y :");
                radlbl.setText("Length :");
                x2lbl.setEnabled(false);
                y2lbl.setEnabled(false);
                x2txt.setEnabled(false);
                y2txt.setEnabled(false);
                widlbl.setEnabled(true);
                widtxt.setEnabled(true);
                fclrlbl.setEnabled(true);
                fclrcombox.setEnabled(true);
                break;
            default:
                posxlbl.setText("X :");
                posylbl.setText("Y :");
                radlbl.setText("Radius :");
                x2lbl.setEnabled(true);
                y2lbl.setEnabled(true);
                x2txt.setEnabled(true);
                y2txt.setEnabled(true);
                widlbl.setEnabled(true);
                widtxt.setEnabled(true);
                fclrlbl.setEnabled(true);
                fclrcombox.setEnabled(true);
        }
        Shape finalShape = shape;
        createbtn.addActionListener(e -> {
            engine.addShape(finalShape);
            finalShape.draw(drwcanv.getGraphics());
            updateExistShapes();
            shapes.add(finalShape);
        });
    }

    public void updateExistShapes(){
        DefaultComboBoxModel model = (DefaultComboBoxModel) shpcombox.getModel();
        model.removeAllElements();
        model.addElement("--Choose Shape--");
        for (String item : engine.nms) {
            model.addElement(item);
        }
        shpcombox.setModel(model);
    }

    public void setColor(){
//        System.out.println(ncolor);
        int index = selectedShape()-1;
//        System.out.println(index);
        if (edgebtn.isSelected()) {
            engine.getShapes()[index].setColor(ncolor);
            //        System.out.println("\ni'm engine shape "+engine.getShapes()[index]+" and my color is "+engine.getShapes()[index].getColor());
            shapes.get(index).setColor(ncolor);
            //        System.out.println("\ni'm gui shape "+shapes.get(index)+" and my color is "+shapes.get(index).getColor());
        }else if (fillbtn.isSelected()){
            engine.getShapes()[index].setFillColor(ncolor);
//                    System.out.println("\ni'm engine shape "+engine.getShapes()[index]+" and my fill color is "+engine.getShapes()[index].getFillColor());
            shapes.get(index).setFillColor(ncolor);
//                    System.out.println("\ni'm gui shape "+shapes.get(index)+" and my fill color is "+shapes.get(index).getFillColor());
        }
        drwcanv.repaint();
    }
}
