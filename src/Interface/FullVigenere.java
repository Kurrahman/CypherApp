package Interface;

import Engine.FullVigenereCypher;
import Engine.VigenereCypher;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FullVigenere implements ActionListener {
    private JTextArea plain, cypher, key;
    private JTextField seed;
    private JButton encryptBtn, decryptBtn;
    Border textArea = BorderFactory.createLoweredBevelBorder();

    public void displayFullVigenere(JFrame frame){
        JLabel plainLabel = new JLabel("Plain Text : ");
        plainLabel.setBounds(30,10,300,20);
        frame.add(plainLabel);

        JLabel cypherLabel = new JLabel("Cypher Text : ");
        cypherLabel.setBounds(450,10,300,20);
        frame.add(cypherLabel);

        JLabel keyLabel = new JLabel("Vigenere Key : ");
        keyLabel.setBounds(30,430,720,20);
        frame.add(keyLabel);

        JLabel seedLabel = new JLabel("Seed : ");
        seedLabel.setBounds(350, 98, 80, 30);
        frame.add(seedLabel);

        plain = new JTextArea();
        plain.setBorder(textArea);
        plain.setLineWrap(true);
        plain.setBounds(30,30, 300, 400);
        frame.add(plain);

        cypher = new JTextArea();
        cypher.setBorder(textArea);
        cypher.setLineWrap(true);
        cypher.setBounds(450, 30, 300, 400);
        frame.add(cypher);

        key = new JTextArea();
        key.setBorder(textArea);
        key.setLineWrap(true);
        key.setBounds(30, 450, 720, 70);
        frame.add(key);

        encryptBtn = new JButton("Encrypt");
        encryptBtn.addActionListener(this);
        encryptBtn.setBounds(350, 30, 80, 30);
        frame.add(encryptBtn);

        decryptBtn = new JButton("Decrypt");
        decryptBtn.addActionListener(this);
        decryptBtn.setBounds(350, 70, 80, 30);
        frame.add(decryptBtn);

        seed = new JTextField("0");
        seed.setBounds(350, 120, 80, 30);
        frame.add(seed);

        frame.setVisible(false);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == decryptBtn){
            FullVigenereCypher.initVigenereTable(Integer.parseInt(seed.getText()));
            plain.setText(FullVigenereCypher.decrypt(cypher.getText(), key.getText()));
        }
        if (e.getSource() == encryptBtn){
            FullVigenereCypher.initVigenereTable(Integer.parseInt(seed.getText()));
            cypher.setText(FullVigenereCypher.encrypt(plain.getText(),key.getText()));
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("test");
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
        FullVigenere fullVigenere = new FullVigenere();
        fullVigenere.displayFullVigenere(frame);
    }
}
