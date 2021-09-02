package Interface;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Template implements ActionListener {
    protected JTextArea plain, cypher;
    protected JButton encryptBtn, decryptBtn;
    Border textArea = BorderFactory.createLoweredBevelBorder();

    public void displayBase(JFrame frame) {
        frame.getContentPane().removeAll();

        JLabel plainLabel = new JLabel("Plain Text : ");
        plainLabel.setBounds(30, 10, 300, 20);
        frame.add(plainLabel);

        JLabel cypherLabel = new JLabel("Cypher Text : ");
        cypherLabel.setBounds(450, 10, 300, 20);
        frame.add(cypherLabel);

        plain = new JTextArea();
        plain.setBorder(textArea);
        plain.setLineWrap(true);
        plain.setBounds(30, 30, 300, 400);
        frame.add(plain);

        cypher = new JTextArea();
        cypher.setBorder(textArea);
        cypher.setLineWrap(true);
        cypher.setBounds(450, 30, 300, 400);
        frame.add(cypher);

        encryptBtn = new JButton("Encrypt");
        encryptBtn.addActionListener(this);
        encryptBtn.setBounds(350, 30, 80, 30);
        frame.add(encryptBtn);

        decryptBtn = new JButton("Decrypt");
        decryptBtn.addActionListener(this);
        decryptBtn.setBounds(350, 70, 80, 30);
        frame.add(decryptBtn);

        frame.setVisible(false);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
