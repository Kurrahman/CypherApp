package Interface;

import Engine.PlayFairCypher;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayFair extends Template implements ActionListener {
    private JButton bigraphBtn, squareBtn;
    private JDialog bigraphDialog, squareDialog;
    private JTextArea key, bigraph;

    public void displayPlayFair(JFrame frame) {
        displayBase(frame);

        JLabel keyLabel = new JLabel("PlayFair Key : ");
        keyLabel.setBounds(30, 430, 720, 20);
        frame.add(keyLabel);

        bigraphBtn = new JButton("Bigraph");
        bigraphBtn.addActionListener(this);
        bigraphBtn.setBounds(350, 110, 80, 30);
        frame.add(bigraphBtn);

        squareBtn = new JButton("Square");
        squareBtn.addActionListener(this);
        squareBtn.setBounds(350, 150, 80, 30);
        frame.add(squareBtn);

        key = new JTextArea();
        key.setBorder(textArea);
        key.setLineWrap(true);
        key.setBounds(30, 450, 720, 70);
        frame.add(key);

        squareDialog = new JDialog(frame, "Square Key", true);
        squareDialog.setLayout(null);
        squareDialog.setSize(300, 300);
        squareDialog.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        squareDialog.setVisible(false);

        bigraphDialog = new JDialog(frame, "Plain Text Bigraph", true);
        bigraphDialog.setLayout(null);
        bigraphDialog.setSize(400, 300);
        bigraphDialog.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        bigraphDialog.setVisible(false);

        frame.setVisible(false);
        frame.setVisible(true);
    }

    private void displaySquareDialog() {
        squareDialog.getContentPane().removeAll();
        PlayFairCypher.generateSquare(key.getText());
        Character[][] data = {
                {'A', 'B', 'C', 'D', 'E'},
                {'F', 'G', 'H', 'I', 'K'},
                {'L', 'M', 'N', 'O', 'P'},
                {'Q', 'R', 'S', 'T', 'U'},
                {'V', 'W', 'X', 'Y', 'Z'},
        };

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                data[i][j] = PlayFairCypher.getSquare().get(i).get(j);
            }
        }

        String[] column = {"", "", "", "", ""};
        JTable square = new JTable(data, column);
        for (int i = 0; i < 5; i++) {
            square.getColumnModel().getColumn(i).setPreferredWidth(50);
            square.setRowHeight(40);
        }
        square.setBounds(10, 10, 250, 220);
        squareDialog.add(square);

        squareDialog.setVisible(true);
    }

    private void displayBigraphDialog() {
        bigraphDialog.getContentPane().removeAll();

        bigraph = new JTextArea();
        bigraph.setBorder(textArea);
        bigraph.setBounds(10, 10, 360, 240);

        bigraph.setText(PlayFairCypher.generateBigraph(plain.getText()));
        bigraph.setEditable(false);
        bigraphDialog.add(bigraph);

        bigraphDialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == squareBtn) {
            displaySquareDialog();
        }
        if (e.getSource() == bigraphBtn) {
            displayBigraphDialog();
        }
        if (e.getSource() == decryptBtn) {
            plain.setText(PlayFairCypher.decrypt(cypher.getText(), key.getText()));
        }
        if (e.getSource() == encryptBtn) {
            cypher.setText(PlayFairCypher.encrypt(plain.getText(), key.getText()));
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("test");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
        PlayFair vigenere = new PlayFair();
        vigenere.displayPlayFair(frame);
    }
}
