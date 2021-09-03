package Interface;

import Engine.FullVigenereCypher;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FullVigenere extends Template implements ActionListener {
    private JTextArea key;
    private JTextField seed;

    public void displayFullVigenere(JFrame frame) {
        displayBase(frame);

        JLabel keyLabel = new JLabel("Vigenere Key : ");
        keyLabel.setBounds(30, 430, 720, 20);
        frame.add(keyLabel);

        JLabel seedLabel = new JLabel("Seed : ");
        seedLabel.setBounds(350, 98, 80, 30);
        frame.add(seedLabel);

        key = new JTextArea();
        key.setBorder(textArea);
        key.setLineWrap(true);
        key.setBounds(30, 450, 720, 70);
        frame.add(key);

        seed = new JTextField("0");
        seed.setBounds(350, 120, 80, 30);
        frame.add(seed);

        frame.setVisible(false);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (saveAndLoad(e)){
            super.actionPerformed(e);
        }
        if (e.getSource() == decryptBtn) {
            FullVigenereCypher.initVigenereTable(Integer.parseInt(seed.getText()));
            plain.setText(FullVigenereCypher.decrypt(cypher.getText(), key.getText()));
        }
        if (e.getSource() == encryptBtn) {
            FullVigenereCypher.initVigenereTable(Integer.parseInt(seed.getText()));
            cypher.setText(FullVigenereCypher.encrypt(plain.getText(), key.getText()));
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("test");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
        FullVigenere fullVigenere = new FullVigenere();
        fullVigenere.displayFullVigenere(frame);
    }
}
