package ro.tuc.BusinessLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InstructionListener implements ActionListener {
    private final Controller theController;

    public InstructionListener(Controller controller) {
        this.theController = controller;
    }

    public void actionPerformed(ActionEvent e){
        UIManager.put("OptionPane.background", new Color(33, 36, 49));
        UIManager.put("Panel.background", new Color(33, 36, 49));
        UIManager.put("Button.background", new Color(228, 208, 208));
        String instructions = """
                Pentru inceput, trebuie sa introduceti 2 polinoame cu o variabila "x". Polinoamele trebuie sa fie de forma:
                \ta(n)x^n + a(n-1)x^(n-1) + ... + a(1)x + a(0)
                Coeficientii ambelor polinoame sunt numere reale, iar exponentii sunt numere naturale.
                Dupa ce ati introdus polinoamele, alegeti operatia pe care doriti sa o efectuati si apasati "=".
                Pentru operatiile de integrare si derivare se va utiliza polinomul introdus in primul camp.""";
        JTextArea area = new JTextArea(instructions);
        area.setFont(new Font("Tahoma", Font.PLAIN, 14));
        area.setBackground(new Color(33, 36, 49));
        area.setForeground(new Color(228, 208, 208));
        JOptionPane.showMessageDialog(theController.theView, area, "Instructiuni", JOptionPane.INFORMATION_MESSAGE);
    }
}
