package Interface;

import Engine.ExtendedVigenereCypher;
import Engine.VigenereCypher;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExtendedVigenere extends Template implements ActionListener {
    private JTextArea key;

    public void displayExtendedVigenere(JFrame frame){
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
        if (e.getSource() == decryptBtn) {
            plain.setText(ExtendedVigenereCypher.decrypt(cypher.getText(), key.getText()));
        }
        if (e.getSource() == encryptBtn) {
            cypher.setText(ExtendedVigenereCypher.encrypt(plain.getText(), key.getText()));
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("test");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
        ExtendedVigenere vigenere = new ExtendedVigenere();
        vigenere.displayExtendedVigenere(frame);
    }

}
