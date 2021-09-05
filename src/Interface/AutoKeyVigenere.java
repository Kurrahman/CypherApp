package Interface;

import Engine.AutoKeyVigenereCypher;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AutoKeyVigenere extends Template implements ActionListener {
    private JTextArea key;

    public void displayAutoKeyVigenere(JFrame frame) {
        displayBase(frame);

        JLabel keyLabel = new JLabel("Vigenere Key : ");
        keyLabel.setBounds(30, 430, 720, 20);
        frame.add(keyLabel);

        key = new JTextArea();
        key.setBorder(textArea);
        key.setLineWrap(true);
        key.setBounds(30, 450, 720, 70);
        frame.add(key);
        frame.setVisible(false);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (templateAction(e)) {
            super.actionPerformed(e);
        }
        if (e.getSource() == decryptBtn) {
            plain.setText(AutoKeyVigenereCypher.decrypt(cypher.getText(), key.getText()));
        }
        if (e.getSource() == encryptBtn) {
            cypher.setText(AutoKeyVigenereCypher.encrypt(plain.getText(), key.getText()));
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("test");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
        AutoKeyVigenere vigenere = new AutoKeyVigenere();
        vigenere.displayAutoKeyVigenere(frame);
    }
}
