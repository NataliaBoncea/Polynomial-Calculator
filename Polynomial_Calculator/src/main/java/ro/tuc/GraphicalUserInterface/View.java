package ro.tuc.GraphicalUserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import ro.tuc.BusinessLogic.*;

public class View extends JFrame{
    private JLabel title = new JLabel("Calculator Polinomial");
    private JLabel pLabel1 = new JLabel("Primul polinom: ");
    private JLabel pLabel2 = new JLabel("Al doilea polinom: ");
    private JLabel resultLabel = new JLabel("Rezultat:        ");
    private JTextField pText1 = new JTextField(20);
    private JTextField pText2 = new JTextField(20);
    private JButton egal = new JButton ("=");
    private JButton instructiuni = new JButton ("Instructiuni");
    private String op[] = {"Adunare", "Scadere", "Inmultire", "Impartire", "Modulo", "Derivare", "Integrare"};
    private JComboBox<String> operation = new JComboBox<>(op);
    Color light_pink = new Color(228, 208, 208);
    Color purple1 = new Color(33, 36, 49);
Color purple2 = new Color(45, 49, 66);
Color purple3 = new Color(41, 43, 61);


    public View(){
        JFrame frame = new JFrame ("Calculator Polinomial");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);

        Font  f1  = new Font(Font.SERIF, Font.BOLD,  40);
        Font  f2  = new Font(Font.DIALOG, Font.PLAIN,  15);
        Font  f3  = new Font(Font.DIALOG, Font.BOLD,  20);
        Font  f4  = new Font(Font.SERIF, Font.PLAIN,  12);

        JPanel p4 = new JPanel();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p23 = new JPanel();
        JPanel p5 = new JPanel();
        JPanel p6 = new JPanel();
        JPanel p = new JPanel();
        JPanel instrPanel = new JPanel();

        p1.add(title);
        title.setFont(f1);
        title.setForeground(light_pink);
        p1.setBackground(purple1);

        //p2.setLayout(new GridLayout(2, 1));
        p2.add(pLabel1);
        pLabel1.setFont(f2);
        pLabel1.setForeground(light_pink);
        p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
        p2.setBackground(purple2);
        p2.add(pLabel2);
        pLabel2.setFont(f2);
        pLabel2.setForeground(light_pink);
        p2.setBackground(purple2);
        p2.setAlignmentY(Component.CENTER_ALIGNMENT);


        p3.add(pText1);
        p3.add(pText2);
        pText1.setBackground(light_pink);
        pText2.setBackground(light_pink);
        p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));
        p3.setAlignmentY(Component.CENTER_ALIGNMENT);

        p23.add(p2);
        p23.add(p3);
        p23.setAlignmentX(Component.CENTER_ALIGNMENT);
        p23.setBackground(purple2);

        p4.add(operation);
        operation.setFont(f2);
        p4.add(egal);
        egal.setPreferredSize(new Dimension(150, 30));
        egal.setFont(f3);
        p4.setBackground(purple2);

        p5.add(resultLabel);
        p5.setBackground(purple3);
        resultLabel.setFont(f3);
        resultLabel.setForeground(light_pink);

        p6.add(instructiuni);
        p6.setBackground(purple3);
        instructiuni.setFont(f4);
        p6.setLayout(new FlowLayout(FlowLayout.RIGHT));

        p.add(p1);
        p.add(p23);
        p.add(p4);
        p.add(p5);
        p.add(p6);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(purple2);

        frame.setContentPane(p);
        frame.setVisible(true);
    }

    public String getFirstPolynomial(){
        return pText1.getText();
    }

    public String getSecondPolynomial(){
        return pText2.getText();
    }
    public int getOperation() {
        return operation.getSelectedIndex();
    }
    public void setSolution(String solution){
        resultLabel.setText("Rezultat: " + solution);
    }
    public void changeFirstColorError(){
        pText1.setBackground(Color.RED);
        UIManager.put("OptionPane.background", purple1);
        UIManager.put("Panel.background", purple1);
        UIManager.put("Button.background", light_pink);
        String instructions = """
                Oups! Se pare ca nu ati introdus primul polinom corect.
                Asigurati-va ca ati introdus polinomul in concordanta cu structura precizata in instructiuni.""";
        JTextArea area = new JTextArea(instructions);
        area.setFont(new Font("Tahoma", Font.PLAIN, 14));
        area.setBackground(purple1);
        area.setForeground(light_pink);
        JOptionPane.showMessageDialog(this, area, "Eroare!", JOptionPane.ERROR_MESSAGE);
    }
    public void changeFirstColorGood(){
        pText1.setBackground(light_pink);
    }
    public void changeSecondColorError(){
        pText2.setBackground(Color.RED);
        UIManager.put("OptionPane.background", purple1);
        UIManager.put("Panel.background", purple1);
        UIManager.put("Button.background", light_pink);
        String instructions = """
                Oups! Se pare ca nu ati introdus al doilea polinom corect.
                Asigurati-va ca ati introdus polinomul in concordanta cu structura precizata in instructiuni.""";
        JTextArea area = new JTextArea(instructions);
        area.setFont(new Font("Tahoma", Font.PLAIN, 14));
        area.setBackground(purple1);
        area.setForeground(light_pink);
        JOptionPane.showMessageDialog(this, area, "Eroare!", JOptionPane.ERROR_MESSAGE);
    }

    public void impartireLaZero(){
        UIManager.put("OptionPane.background", purple1);
        UIManager.put("Panel.background", purple1);
        UIManager.put("Button.background", light_pink);
        String instructions = """
                Oups! Nu se poate efectua impartire la 0.""";
        JTextArea area = new JTextArea(instructions);
        area.setFont(new Font("Tahoma", Font.PLAIN, 14));
        area.setBackground(purple1);
        area.setForeground(light_pink);
        JOptionPane.showMessageDialog(this, area, "Eroare!", JOptionPane.ERROR_MESSAGE);
    }
    public void changeSecondColorGood(){
        pText2.setBackground(light_pink);
    }
    public void equalListener(ActionListener listenEqButton){
        egal.addActionListener(listenEqButton);
    }
    public void instrListener(ActionListener listenIButton){
        instructiuni.addActionListener(listenIButton);
    }
}
