package Interface;

import Engine.AffineCypher;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hill extends Template implements ActionListener {
    private JTextField[][] key3, key2;
    private JComboBox<Integer> keySize;
    private Integer[] keyList = {2,3};

    private void showKey(JTextField[][] keyss){
        for (JTextField[] keys: keyss) {
            for (JTextField key: keys) {
                key.setVisible(true);
            }
        }
    }

    private void hideKey(JTextField[][] keyss){
        for (JTextField[] keys: keyss) {
            for (JTextField key: keys) {
                key.setVisible(false);
            }
        }
    }

    public void displayHill(JFrame frame){
        displayBase(frame);

        Integer[][] data3 = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        Integer[][] data2 = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };

        String[] column3 = {"", "", ""};
        String[] column2 = {"", ""};

        JLabel keyLabel = new JLabel("Hill Key : ");
        keyLabel.setBounds(30, 430, 720, 20);
        frame.add(keyLabel);

        key3 = new JTextField[][]
                {
                        new JTextField[]{new JTextField(),new JTextField(),new JTextField()},
                        new JTextField[]{new JTextField(),new JTextField(),new JTextField()},
                        new JTextField[]{new JTextField(),new JTextField(),new JTextField()}
                };
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                key3[i][j].setBounds(30+25*i,450+25*j,25,25);
                key3[i][j].setVisible(true);
                frame.add(key3[i][j]);
            }
        }

        key2 = new JTextField[][]
                {
                        new JTextField[]{new JTextField(),new JTextField()},
                        new JTextField[]{new JTextField(),new JTextField()}
                };
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                key2[i][j].setBounds(30+30*i,450+30*j,30,30);
                key2[i][j].setVisible(false);
                frame.add(key2[i][j]);
            }
        }

        JLabel keySizeLabel = new JLabel("Key Size : ");
        keySizeLabel.setBounds(350, 110, 80, 20);
        frame.add(keySizeLabel);

        keySize = new JComboBox<>(keyList);
        keySize.setBounds(350, 130, 80, 30);
        keySize.setSelectedIndex(1);
        keySize.addActionListener(this);
        frame.add(keySize);

        frame.setVisible(false);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == keySize){
            if (keySize.getSelectedIndex() == 1){
                hideKey(key2);
                showKey(key3);
            } else {
                hideKey(key3);
                showKey(key2);
            }
        }
        if (e.getSource() == decryptBtn) {
//            plain.setText(AffineCypher.decrypt(cypher.getText(), (int) coprime.getSelectedItem(), Integer.parseInt(shift.getText())));
        }
        if (e.getSource() == encryptBtn) {
//            cypher.setText(AffineCypher.encrypt(plain.getText(), (int) coprime.getSelectedItem(), Integer.parseInt(shift.getText())));
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("test");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
        Hill hill = new Hill();
        hill.displayHill(frame);
    }
}
