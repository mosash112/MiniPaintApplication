import shapes.*;
import shapes.Rectangle;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileFilter;

public class Gui implements MouseListener, MouseMotionListener {
    Engine engine;
    String[] names = {"--Select a shape--"};
    Color trans = new Color(255,255,225,0), newBorder = Color.BLACK, newFill = trans;
    int flag = 0, selected;

//    main window components
    private final JFrame mainFrame = new JFrame();
    private final JPanel mainPanel = new JPanel();
    private final GridBagConstraints c = new GridBagConstraints();
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu menu = new JMenu("File");
    private final JMenuItem save = new JMenuItem("Save");
    private final JMenuItem load = new JMenuItem("Load");
    private final JFileChooser fileChooser = new JFileChooser();
    private final JLabel shapelbl = new JLabel("Select Shape");
    final JComboBox<String> shpcombox = new JComboBox<>(names);
    private final JButton colorbtn = new JButton("Colorize");
    private final JButton delbtn = new JButton("Delete");
    private final JButton copybtn = new JButton("Copy");
    private final JButton ovalbtn = new JButton("Oval");
    private final JButton linebtn = new JButton("Line Segment");
    private final JButton tribtn = new JButton("Triangle");
    private final JButton rectbtn = new JButton("Rectangle");
    MyPanel drwcanv = new MyPanel(this);
    private final JOptionPane opt = new JOptionPane();

    //    coloring window components
    private final JPanel colorPanel = new JPanel();

    //    circle parameters window components
    private final JFrame ovalFrame = new JFrame();
    private final JPanel ovalPanel = new JPanel();
    private final JLabel ovalposlbl = new JLabel("Position");
    private final JLabel ovalxlbl = new JLabel("X :");
    private final JLabel ovalylbl = new JLabel("Y :");
    private final JLabel ovalColorlbl = new JLabel("Color :");
    private final JLabel ovalFillColorlbl = new JLabel("Fill Color :");
    private final JLabel ovalHeilbl = new JLabel("Vertical Radius :");
    private final JLabel ovalWidlbl = new JLabel("Horizontal Radius :");
    private final JTextField ovalxtxt = new JTextField(10);
    private final JTextField ovalytxt = new JTextField(10);
    private final JButton ovalColor = new JButton("select Border Color");
    private final JButton ovalFillColor = new JButton("select Fill Color");
    private final JTextField ovalHeitxt = new JTextField(10);
    private final JTextField ovalWidtxt = new JTextField(10);
    private final JButton createOvalBtn = new JButton("Create Circle");

    //    rectangle parameters window components
    private final JFrame rectFrame = new JFrame();
    private final JPanel rectPanel = new JPanel();
    private final JLabel rectposlbl = new JLabel("Position");
    private final JLabel rectxlbl = new JLabel("X :");
    private final JLabel rectylbl = new JLabel("Y :");
    private final JLabel rectColorlbl = new JLabel("Color :");
    private final JLabel rectFillColorlbl = new JLabel("Fill Color :");
    private final JLabel heilbl = new JLabel("Height :");
    private final JLabel widlbl = new JLabel("Width :");
    private final JTextField rectxtxt = new JTextField(10);
    private final JTextField rectytxt = new JTextField(10);
    private final JButton rectColor = new JButton("select Border Color");
    private final JButton rectFillColor = new JButton("select Fill Color");
    private final JTextField rectHeitxt = new JTextField(10);
    private final JTextField rectWidtxt = new JTextField(10);
    private final JButton createRectBtn = new JButton("Create Rectangle");

    //    line parameters window components
    private final JFrame lineFrame = new JFrame();
    private final JPanel linePanel = new JPanel();
    private final JLabel lineposlbl = new JLabel("Position");
    private final JLabel linexlbl = new JLabel("X1 :");
    private final JLabel lineylbl = new JLabel("Y1 :");
    private final JLabel linex2lbl = new JLabel("X2 :");
    private final JLabel liney2lbl = new JLabel("Y2 :");
    private final JLabel lineColorlbl = new JLabel("Color :");
    private final JTextField linextxt = new JTextField(10);
    private final JTextField lineytxt = new JTextField(10);
    private final JTextField linex2txt = new JTextField(10);
    private final JTextField liney2txt = new JTextField(10);
    private final JButton lineColor = new JButton("select Border Color");
    private final JButton createLineBtn = new JButton("Create Line");

    //    triangle parameters window components
    private final JFrame triFrame = new JFrame();
    private final JPanel triPanel = new JPanel();
    private final JLabel triposlbl = new JLabel("Position");
    private final JLabel trixlbl = new JLabel("X1 :");
    private final JLabel triylbl = new JLabel("Y1 :");
    private final JLabel trix2lbl = new JLabel("X2 :");
    private final JLabel triy2lbl = new JLabel("Y2 :");
    private final JLabel trix3lbl = new JLabel("X3 :");
    private final JLabel triy3lbl = new JLabel("Y3 :");
    private final JLabel triColorlbl = new JLabel("Color :");
    private final JLabel triFillColorlbl = new JLabel("Fill Color :");
    private final JTextField trixtxt = new JTextField(10);
    private final JTextField triytxt = new JTextField(10);
    private final JTextField trix2txt = new JTextField(10);
    private final JTextField triy2txt = new JTextField(10);
    private final JTextField trix3txt = new JTextField(10);
    private final JTextField triy3txt = new JTextField(10);
    private final JButton triColor = new JButton("select Border Color");
    private final JButton triFillColor = new JButton("select Fill Color");
    private final JButton createTriBtn = new JButton("Create Triangle");


    public Gui() {
        window();
        newOval();
        newRect();
        newLine();
        newTri();
    }

    public void window(){
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));
        mainPanel.setLayout(new GridBagLayout());

        constraintReset();

        c.insets = new Insets(3,3,3,3);
        c.gridx = 0;
        c.gridy = 2;
        mainPanel.add(shapelbl,c);

        shpcombox.setMaximumSize(shpcombox.getPreferredSize());
        shpcombox.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.gridx = 0;
        c.gridy = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        mainPanel.add(shpcombox,c);

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.LINE_START;
        mainPanel.add(colorbtn,c);

        c.gridx = 1;
        c.gridy = 4;
        mainPanel.add(delbtn,c);

        c.gridx = 0;
        c.gridy = 5;
        mainPanel.add(copybtn,c);

        c.gridx = 2;
        c.gridy = 1;
        mainPanel.add(ovalbtn,c);

        c.gridx = 3;
        c.gridy = 1;
        mainPanel.add(linebtn,c);

        c.gridx = 4;
        c.gridy = 1;
        mainPanel.add(rectbtn,c);

        c.gridx = 5;
        c.gridy = 1;
        mainPanel.add(tribtn,c);

        drwcanv.setBackground(Color.WHITE);
        drwcanv.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        drwcanv.addMouseMotionListener(this);
        drwcanv.addMouseListener(this);
        c.gridx = 2;
        c.gridy = 2;
        c.gridwidth = 4;
        c.gridheight = 5;
        c.ipady = 200;
        c.ipadx = 400;
        c.fill = GridBagConstraints.BOTH;
        mainPanel.add(drwcanv,c);

        shpcombox.addActionListener(e -> selectedShape());
        colorbtn.addActionListener(e -> colorizeButtonPressed(2));
        delbtn.addActionListener(e -> deleteButtonPressed());
        copybtn.addActionListener(e -> copyShape());
        ovalbtn.addActionListener(e -> {flag = 1;shapeParameters();});
        linebtn.addActionListener(e -> {flag = 2;shapeParameters();});
        tribtn.addActionListener(e -> {flag = 3;shapeParameters();});
        rectbtn.addActionListener(e -> {flag = 4;shapeParameters();});
        save.addActionListener(e -> savePainting());
        load.addActionListener(e -> loadPainting());

        menu.setMnemonic(KeyEvent.VK_F);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,ActionEvent.CTRL_MASK));
        copybtn.setMnemonic(KeyEvent.VK_C+ActionEvent.CTRL_MASK);
        delbtn.setMnemonic(KeyEvent.VK_D);
        colorbtn.setMnemonic(KeyEvent.VK_C);

        menu.add(save);
        menu.add(load);
        menuBar.add(menu);
        mainFrame.add(menuBar, BorderLayout.NORTH);
        mainFrame.add(mainPanel, BorderLayout.WEST);
        mainFrame.setSize(600,400);
        mainFrame.setLocation(300, 400);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setTitle("Mini-Paint");
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public void constraintReset(){
//        reset constraint values
        c.gridheight = 1;
        c.ipady = 0;
        c.ipadx = 0;
        c.gridwidth = 1;
    }

    public void newOval(){
        ovalPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));
        ovalPanel.setLayout(new GridBagLayout());

        constraintReset();

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        ovalPanel.add(ovalposlbl,c);

        c.gridx = 0;
        c.gridy = 1;
        ovalPanel.add(ovalxlbl,c);

        ovalxtxt.setMaximumSize(ovalxtxt.getPreferredSize());
        ovalxtxt.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.gridx = 1;
        c.gridy = 1;
        ovalPanel.add(ovalxtxt,c);

        c.gridx = 2;
        c.gridy = 1;
        ovalPanel.add(ovalylbl,c);

        ovalytxt.setMaximumSize(ovalytxt.getPreferredSize());
        ovalytxt.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.gridx = 3;
        c.gridy = 1;
        ovalPanel.add(ovalytxt,c);

        c.gridx = 0;
        c.gridy = 2;
        ovalPanel.add(ovalColorlbl,c);

        ovalColor.setMaximumSize(ovalColor.getPreferredSize());
        ovalColor.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.gridx = 1;
        c.gridy = 2;
        ovalPanel.add(ovalColor,c);

        c.gridx = 2;
        c.gridy = 2;
        ovalPanel.add(ovalFillColorlbl,c);

        ovalFillColor.setMaximumSize(ovalFillColor.getPreferredSize());
        ovalFillColor.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.gridx = 3;
        c.gridy = 2;
        ovalPanel.add(ovalFillColor,c);

        c.gridx = 0;
        c.gridy = 3;
        ovalPanel.add(ovalHeilbl,c);

        ovalHeitxt.setMaximumSize(ovalHeitxt.getPreferredSize());
        ovalHeitxt.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.gridx = 1;
        c.gridy = 3;
        ovalPanel.add(ovalHeitxt,c);

        c.gridx = 2;
        c.gridy = 3;
        ovalPanel.add(ovalWidlbl,c);

        ovalWidtxt.setMaximumSize(ovalWidtxt.getPreferredSize());
        ovalWidtxt.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.gridx = 3;
        c.gridy = 3;
        ovalPanel.add(ovalWidtxt,c);

        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 2;
        ovalPanel.add(createOvalBtn,c);

        ovalColor.addActionListener(e -> colorizeButtonPressed(0));
        ovalFillColor.addActionListener(e -> colorizeButtonPressed(1));
        createOvalBtn.addActionListener(e -> checkOvalParam());

        ovalFrame.add(ovalPanel,BorderLayout.CENTER);
        ovalFrame.setSize(550, 220);
        ovalFrame.setLocation(300,400);
        ovalFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        ovalFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mainFrame.setVisible(true);
            }
        });
        ovalFrame.setTitle("Circle attributes");
        ovalFrame.setVisible(false);
    }

    private void checkOvalParam() {
        Color fillColor = newFill, color = newBorder;
        Point[] points = new Point[]{new Point(0, 0), new Point(0, 0), new Point(0, 0)};
        int hei = 0, wid = 0;

        if (!ovalxtxt.getText().isBlank() && !ovalytxt.getText().isBlank()) {
            try {
                points[0] = new Point(Integer.parseInt(ovalxtxt.getText()), Integer.parseInt(ovalytxt.getText()));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(opt, "enter numbers only", "Input error", JOptionPane.WARNING_MESSAGE);
            }
            ovalxtxt.setText("");
            ovalytxt.setText("");
        }
        if(!ovalHeitxt.getText().isBlank()) {
            try{
                hei = Integer.parseInt(ovalHeitxt.getText());
            }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(opt, "enter numbers only","Input error", JOptionPane.WARNING_MESSAGE);
            }
        }
        if(!ovalWidtxt.getText().isBlank()) {
            try{
                wid = Integer.parseInt(ovalWidtxt.getText());
            }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(opt, "enter numbers only","Input error", JOptionPane.WARNING_MESSAGE);
            }
        }
        ovalHeitxt.setText("");
        ovalWidtxt.setText("");
        newShape(flag, points, color, fillColor, hei, wid);
        newFill = trans;
        newBorder = Color.BLACK;
    }

    public void newRect(){
        rectPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));
        rectPanel.setLayout(new GridBagLayout());

        constraintReset();

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.weightx = 0;
        rectPanel.add(rectposlbl,c);

        c.gridx = 0;
        c.gridy = 1;
        rectPanel.add(rectxlbl,c);

        rectxtxt.setMaximumSize(rectxtxt.getPreferredSize());
        rectxtxt.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.gridx = 1;
        c.gridy = 1;
        rectPanel.add(rectxtxt,c);

        c.gridx = 2;
        c.gridy = 1;
        rectPanel.add(rectylbl,c);

        rectytxt.setMaximumSize(rectytxt.getPreferredSize());
        rectytxt.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.gridx = 3;
        c.gridy = 1;
        rectPanel.add(rectytxt,c);

        c.gridx = 0;
        c.gridy = 2;
        rectPanel.add(rectColorlbl,c);

        rectColor.setMaximumSize(rectColor.getPreferredSize());
        rectColor.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.gridx = 1;
        c.gridy = 2;
        rectPanel.add(rectColor,c);

        c.gridx = 2;
        c.gridy = 2;
        rectPanel.add(rectFillColorlbl,c);

        rectFillColor.setMaximumSize(rectFillColor.getPreferredSize());
        rectFillColor.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.gridx = 3;
        c.gridy = 2;
        rectPanel.add(rectFillColor,c);

        c.gridx = 0;
        c.gridy = 3;
        rectPanel.add(heilbl,c);

        rectHeitxt.setMaximumSize(rectHeitxt.getPreferredSize());
        rectHeitxt.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.gridx = 1;
        c.gridy = 3;
        rectPanel.add(rectHeitxt,c);

        c.gridx = 2;
        c.gridy = 3;
        rectPanel.add(widlbl,c);

        c.gridx = 3;
        c.gridy = 3;
        rectPanel.add(rectWidtxt,c);

        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 2;
        rectPanel.add(createRectBtn,c);

        rectColor.addActionListener(e -> colorizeButtonPressed(0));
        rectFillColor.addActionListener(e -> colorizeButtonPressed(1));
        createRectBtn.addActionListener(e -> checkRectParam());

        rectFrame.add(rectPanel,BorderLayout.CENTER);
        rectFrame.setSize(450, 220);
        rectFrame.setLocation(300,400);
        rectFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        rectFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mainFrame.setVisible(true);
            }
        });
        rectFrame.setTitle("Rectangle attributes");
        rectFrame.setVisible(false);
    }

    private void checkRectParam() {
        Color fillColor = newFill, color = newBorder;
        Point[] points = new Point[]{new Point(0, 0), new Point(0, 0), new Point(0, 0)};
        int wid = 0, hei = 0;

        if (!rectxtxt.getText().isBlank() && !rectytxt.getText().isBlank()) {
            try {
                points[0] = new Point(Integer.parseInt(rectxtxt.getText()), Integer.parseInt(rectytxt.getText()));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(opt, "enter numbers only", "Input error", JOptionPane.WARNING_MESSAGE);
            }
        }
        rectxtxt.setText("");
        rectytxt.setText("");
        if(!rectHeitxt.getText().isBlank()) {
            try{
                hei = Integer.parseInt(rectHeitxt.getText());
            }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(opt, "enter numbers only","Input error", JOptionPane.WARNING_MESSAGE);
            }
        }
        rectHeitxt.setText("");
        if(!rectWidtxt.getText().isBlank()) {
            try{
                wid = Integer.parseInt(rectWidtxt.getText());
            }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(opt, "enter numbers only","Input error", JOptionPane.WARNING_MESSAGE);
            }
        }
        rectWidtxt.setText("");

        newShape(flag, points, color, fillColor, hei, wid);
        newFill = trans;
        newBorder = Color.BLACK;
    }

    public void newLine(){
        linePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));
        linePanel.setLayout(new GridBagLayout());

        constraintReset();

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.weightx = 0;
        linePanel.add(lineposlbl,c);

        c.gridx = 0;
        c.gridy = 1;
        linePanel.add(linexlbl,c);

        linextxt.setMaximumSize(linextxt.getPreferredSize());
        linextxt.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.gridx = 1;
        c.gridy = 1;
        linePanel.add(linextxt,c);

        c.gridx = 2;
        c.gridy = 1;
        linePanel.add(lineylbl,c);

        lineytxt.setMaximumSize(lineytxt.getPreferredSize());
        lineytxt.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.gridx = 3;
        c.gridy = 1;
        linePanel.add(lineytxt,c);

        c.gridx = 0;
        c.gridy = 2;
        linePanel.add(linex2lbl,c);

        c.gridx = 1;
        c.gridy = 2;
        linePanel.add(linex2txt,c);

        c.gridx = 2;
        c.gridy = 2;
        linePanel.add(liney2lbl,c);

        c.gridx = 3;
        c.gridy = 2;
        linePanel.add(liney2txt,c);

        c.gridx = 0;
        c.gridy = 3;
        linePanel.add(lineColorlbl,c);

        lineColor.setMaximumSize(lineColor.getPreferredSize());
        lineColor.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.gridx = 1;
        c.gridy = 3;
        linePanel.add(lineColor,c);

        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 2;
        linePanel.add(createLineBtn,c);

        lineColor.addActionListener(e -> colorizeButtonPressed(0));
        createLineBtn.addActionListener(e -> checkLineParam());

        lineFrame.add(linePanel,BorderLayout.CENTER);
        lineFrame.setSize(450, 220);
        lineFrame.setLocation(300,400);
        lineFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        lineFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mainFrame.setVisible(true);
            }
        });
        lineFrame.setTitle("Line attributes");
        lineFrame.setVisible(false);
    }

    private void checkLineParam() {
        Color color = newBorder;
        Point[] points = new Point[]{new Point(0, 0), new Point(0, 0), new Point(0, 0)};

        if (!linextxt.getText().isBlank() && !lineytxt.getText().isBlank()) {
            try {
                points[0] = new Point(Integer.parseInt(linextxt.getText()), Integer.parseInt(lineytxt.getText()));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(opt, "enter numbers only", "Input error", JOptionPane.WARNING_MESSAGE);
            }
        }
        linextxt.setText("");
        lineytxt.setText("");
        if (!linex2txt.getText().isBlank() && !liney2txt.getText().isBlank()) {
            try{
                points[1] = new Point(Integer.parseInt(linex2txt.getText()), Integer.parseInt(liney2txt.getText()));
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(opt, "enter numbers only", "Input error", JOptionPane.WARNING_MESSAGE);
            }
        }
        linex2txt.setText("");
        liney2txt.setText("");

        newShape(flag, points, color, null, 0, 0);
        newFill = trans;
        newBorder = Color.BLACK;
    }

    public void newTri(){
        triPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));
        triPanel.setLayout(new GridBagLayout());

        constraintReset();

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.weightx = 0;
        triPanel.add(triposlbl,c);

        c.gridx = 0;
        c.gridy = 1;
        triPanel.add(trixlbl,c);

        trixtxt.setMaximumSize(trixtxt.getPreferredSize());
        trixtxt.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.gridx = 1;
        c.gridy = 1;
        triPanel.add(trixtxt,c);

        c.gridx = 2;
        c.gridy = 1;
        triPanel.add(triylbl,c);

        triytxt.setMaximumSize(triytxt.getPreferredSize());
        triytxt.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.gridx = 3;
        c.gridy = 1;
        triPanel.add(triytxt,c);

        c.gridx = 0;
        c.gridy = 2;
        triPanel.add(trix2lbl,c);

        c.gridx = 1;
        c.gridy = 2;
        triPanel.add(trix2txt,c);

        c.gridx = 2;
        c.gridy = 2;
        triPanel.add(triy2lbl,c);

        c.gridx = 3;
        c.gridy = 2;
        triPanel.add(triy2txt,c);

        c.gridx = 0;
        c.gridy = 3;
        triPanel.add(trix3lbl,c);

        c.gridx = 1;
        c.gridy = 3;
        triPanel.add(trix3txt,c);

        c.gridx = 2;
        c.gridy = 3;
        triPanel.add(triy3lbl,c);

        c.gridx = 3;
        c.gridy = 3;
        triPanel.add(triy3txt,c);

        c.gridx = 0;
        c.gridy = 4;
        triPanel.add(triColorlbl,c);

        triColor.setMaximumSize(triColor.getPreferredSize());
        triColor.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.gridx = 1;
        c.gridy = 4;
        triPanel.add(triColor,c);

        c.gridx = 2;
        c.gridy = 4;
        triPanel.add(triFillColorlbl,c);

        triFillColor.setMaximumSize(triFillColor.getPreferredSize());
        triFillColor.setAlignmentX(Component.CENTER_ALIGNMENT);
        c.gridx = 3;
        c.gridy = 4;
        triPanel.add(triFillColor,c);

        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 2;
        triPanel.add(createTriBtn,c);

        triColor.addActionListener(e -> colorizeButtonPressed(0));
        triFillColor.addActionListener(e -> colorizeButtonPressed(1));
        createTriBtn.addActionListener(e -> checkTriParam());

        triFrame.add(triPanel,BorderLayout.CENTER);
        triFrame.setSize(450, 240);
        triFrame.setLocation(300,400);
        triFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        triFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mainFrame.setVisible(true);
            }
        });
        triFrame.setTitle("Triangle attributes");
        triFrame.setVisible(false);
    }

    private void checkTriParam() {
        Color fillColor = newFill, color = newBorder;
        Point[] points = new Point[]{new Point(0, 0), new Point(0, 0), new Point(0, 0)};

        if (!trixtxt.getText().isBlank() && !triytxt.getText().isBlank()) {
            try {
                points[0] = new Point(Integer.parseInt(trixtxt.getText()), Integer.parseInt(triytxt.getText()));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(opt, "enter numbers only", "Input error", JOptionPane.WARNING_MESSAGE);
            }
        }
        trixtxt.setText("");
        triytxt.setText("");
        if (!trix2txt.getText().isBlank() && !triy2txt.getText().isBlank()) {
            try{
                points[1] = new Point(Integer.parseInt(trix2txt.getText()), Integer.parseInt(triy2txt.getText()));
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(opt, "enter numbers only", "Input error", JOptionPane.WARNING_MESSAGE);
            }
        }
        trix2txt.setText("");
        triy2txt.setText("");
        if (!trix3txt.getText().isBlank() && !triy3txt.getText().isBlank()) {
            try{
                points[2] = new Point(Integer.parseInt(trix3txt.getText()), Integer.parseInt(triy3txt.getText()));
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(opt, "enter numbers only", "Input error", JOptionPane.WARNING_MESSAGE);
            }
        }
        trix3txt.setText("");
        triy3txt.setText("");

        newShape(flag, points, color, fillColor, 0, 0);
        newFill = trans;
        newBorder = Color.BLACK;
    }

    public int selectedShape(){
        return shpcombox.getSelectedIndex()-1;
    }

    public void deleteButtonPressed(){
        int index = selectedShape();
        engine.removeShape(engine.getShapes()[index]);
        updateExistShapes();
        engine.refresh(drwcanv.getGraphics());
        shpcombox.setSelectedIndex(index);
//        engine.selectShape(engine.getShapes()[index-1].getDraggingPoint());
//        System.out.println("shape: "+engine.getShapes()[index-1]+" index: "+(index-1));
        engine.resetSelection();
    }

    public void colorizeButtonPressed(int j) {
//        int index = selectedShape()-1;
        switch (j){
            case 0:
                newBorder = JColorChooser.showDialog(colorPanel, "Border Color", Color.BLACK);
                break;
            case 1:
                newFill = JColorChooser.showDialog(colorPanel, "Fill Color", trans);
                break;
            case 2:
                newBorder = JColorChooser.showDialog(colorPanel, "Border Color", Color.BLACK);
                newFill = JColorChooser.showDialog(colorPanel, "Fill Color", trans);
                break;
        }
        if (newBorder==null)
            newBorder = Color.BLACK;
        if (newFill==null)
            newFill = trans;
        if (j==2) {
            setColor(newFill, true);
            setColor(newBorder, false);
        }
    }

    public void copyShape(){
        engine.copyShape(selected);
        updateExistShapes();
        shpcombox.setSelectedIndex(selected+1);
    }

    public void savePainting(){
        fileChooser.setSelectedFile(new File("painting.json"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new FileNameExtensionFilter("JSON Files | *.json", "json"));
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int ret = fileChooser.showDialog(mainFrame, "save as");
        if(ret == JFileChooser.APPROVE_OPTION) {
            String file = fileChooser.getSelectedFile().getName();
            boolean saved = engine.saveToJson(file);
        }else
            System.out.println("Something went wrong with file chooser when saving");
    }

    public void loadPainting(){
        fileChooser.setSelectedFile(new File("painting.json"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new FileNameExtensionFilter("JSON Files | *.json", "json"));
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int ret = fileChooser.showDialog(mainFrame, "load");
        if(ret == JFileChooser.APPROVE_OPTION) {
            String file = fileChooser.getSelectedFile().getName();
            boolean loaded = engine.loadFromJson(file);
        }else
            System.out.println("Something went wrong with file chooser when loading");
    }

    public void shapeParameters(){
        try{
            drwcanv.setShapes(engine.getShapes());
        }catch (NullPointerException e){
            System.out.println("Couldn't retrieve shapes.");
        }
        mainFrame.setVisible(false);
        switch (flag) {
            case 1 ->     //circle
                    ovalFrame.setVisible(true);
            case 2 ->     //line
                    lineFrame.setVisible(true);
            case 3 ->     //triangle
                    triFrame.setVisible(true);
            case 4 ->     //rectangle
                    rectFrame.setVisible(true);
        }
    }

    public void newShape(int f, Point[] points, Color color, Color fillColor, int hei, int wid) {
        engine.newShape(f, points, color, fillColor, hei, wid);
        updateExistShapes();
        mainFrame.setVisible(true);
        switch (f) {
            case 1 -> circleFrame.setVisible(false);
            case 2 -> lineFrame.setVisible(false);
            case 3 -> triFrame.setVisible(false);
            case 4 -> rectFrame.setVisible(false);
        }
    }

    public void updateExistShapes(){
        DefaultComboBoxModel model = (DefaultComboBoxModel) shpcombox.getModel();
        model.removeAllElements();
        model.addElement("--Select a shape--");
        for (String item : engine.nms) {
            model.addElement(item);
        }
        shpcombox.setModel(model);
    }

    public void setColor(Color color, boolean fill){
        int index = selectedShape();
        if (!fill) {
            engine.getShapes()[index].setColor(color);
        }else if (fill){
            engine.getShapes()[index].setFillColor(color);
        }
        engine.refresh(drwcanv.getGraphics());
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        int x, y;
        x = e.getX();
        y = e.getY();
        Point p = new Point(x, y);
        selected = engine.selectShape(p);
        shpcombox.setSelectedIndex(selected+1);
        if(selected == -1){
            engine.resetSelection();
            engine.refresh(drwcanv.getGraphics());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        int x, y, corner = 0;
        x = e.getX();
        y = e.getY();
        Point p = new Point(x, y);
        if (selected >= 0) {
            if (engine.corners.get(0).contains(p) || engine.corners.get(1).contains(p) ||
                engine.corners.get(2).contains(p) || engine.corners.get(3).contains(p)) {
                if (engine.corners.get(0).contains(p))
                    corner = 1;
                else if (engine.corners.get(1).contains(p))
                    corner = 2;
                else if (engine.corners.get(2).contains(p))
                    corner = 3;
                else if (engine.corners.get(3).contains(p))
                    corner = 4;
                System.out.println("------------reizing------------");
                engine.resizeShape(p,corner);
            }else{
                System.out.println("/////moving\\\\\\");
                engine.getShapes()[selected].moveTo(p);
                engine.resetSelection();
                engine.refresh(drwcanv.getGraphics());
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
}
