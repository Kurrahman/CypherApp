package Interface;

import Engine.AffineCypher;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Affine extends Template implements ActionListener {
    private JComboBox<Integer> coprime;
    private JTextField shift;
    private final Integer[] coprimeList = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25};

    public void displayAffine(JFrame frame) {
        displayBase(frame);

        JLabel coprimeLabel = new JLabel("Coprime : ");
        coprimeLabel.setBounds(350, 110, 80, 20);
        frame.add(coprimeLabel);

        coprime = new JComboBox<>(coprimeList);
        coprime.setBounds(350, 130, 80, 30);
        coprime.setSelectedIndex(0);
        coprime.addActionListener(this);
        frame.add(coprime);

        JLabel shiftLabel = new JLabel("Shift : ");
        shiftLabel.setBounds(350, 170, 80, 20);
        frame.add(shiftLabel);

        shift = new JTextField();
        shift.setBounds(350, 190, 80, 30);
        shift.setBorder(textArea);
        frame.add(shift);

        frame.setVisible(false);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (saveAndLoad(e)){
            super.actionPerformed(e);
        }
        if (e.getSource() == decryptBtn) {
            plain.setText(AffineCypher.decrypt(cypher.getText(), (int) coprime.getSelectedItem(), Integer.parseInt(shift.getText())));
        }
        if (e.getSource() == encryptBtn) {
            cypher.setText(AffineCypher.encrypt(plain.getText(), (int) coprime.getSelectedItem(), Integer.parseInt(shift.getText())));
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("test");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
        Affine affine = new Affine();
        affine.displayAffine(frame);
    }
}
