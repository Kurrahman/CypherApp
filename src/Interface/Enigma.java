package Interface;

import Engine.EnigmaCypher;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Enigma extends Template implements ActionListener {
    private static final JTextField[] keys = new JTextField[3];


    public void displayEnigma(JFrame frame) {
        displayBase(frame);

        JLabel keyLabel = new JLabel("Enigma Key : ");
        keyLabel.setBounds(30, 430, 100, 20);
        frame.add(keyLabel);

        for (int i = 0; i < 3; i++) {
            keys[i] = new JTextField();
            keys[i].setBounds(30 + 30 * i, 450, 30, 30);
            keys[i].setText("0");
            keys[i].setVisible(true);
            frame.add(keys[i]);
        }

        frame.setVisible(false);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (saveAndLoad(e)){
            super.actionPerformed(e);
        }
        if (e.getSource() == decryptBtn) {
            Integer[] key = new Integer[]{
                    Integer.parseInt(keys[0].getText()),
                    Integer.parseInt(keys[1].getText()),
                    Integer.parseInt(keys[2].getText())
            };
            plain.setText(EnigmaCypher.decrypt(cypher.getText(), key));
        }
        if (e.getSource() == encryptBtn) {
            Integer[] key = new Integer[]{
                    Integer.parseInt(keys[0].getText()),
                    Integer.parseInt(keys[1].getText()),
                    Integer.parseInt(keys[2].getText())
            };
            cypher.setText(EnigmaCypher.encrypt(plain.getText(), key));
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("test");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
        Enigma enigma = new Enigma();
        enigma.displayEnigma(frame);
    }
}
