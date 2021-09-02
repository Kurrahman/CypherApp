package Interface;

import Engine.HillCypher;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hill extends Template implements ActionListener {
    private JTextField[][] key3, key2;
    private JComboBox<Integer> keySize;
    private final Integer[] keyList = {2, 3};
    private JTextField[][] invKey3, invKey2;
    private JButton inverseBtn;

    private void showKey(JTextField[][] keyss) {
        for (JTextField[] keys : keyss) {
            for (JTextField key : keys) {
                key.setVisible(true);
            }
        }
    }

    private void hideKey(JTextField[][] keyss) {
        for (JTextField[] keys : keyss) {
            for (JTextField key : keys) {
                key.setVisible(false);
            }
        }
    }

    public void displayHill(JFrame frame) {
        displayBase(frame);

        JLabel keyLabel = new JLabel("Hill Key : ");
        keyLabel.setBounds(30, 430, 720, 20);
        frame.add(keyLabel);

        key3 = new JTextField[][]
                {
                        new JTextField[]{new JTextField(), new JTextField(), new JTextField()},
                        new JTextField[]{new JTextField(), new JTextField(), new JTextField()},
                        new JTextField[]{new JTextField(), new JTextField(), new JTextField()}
                };
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                key3[i][j].setBounds(30 + 25 * j, 450 + 25 * i, 25, 25);
                key3[i][j].setText("0");
                key3[i][j].setVisible(true);
                frame.add(key3[i][j]);
            }
        }

        key2 = new JTextField[][]
                {
                        new JTextField[]{new JTextField(), new JTextField()},
                        new JTextField[]{new JTextField(), new JTextField()}
                };
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                key2[i][j].setBounds(30 + 30 * j, 450 + 30 * i, 30, 30);
                key2[i][j].setText("0");
                key2[i][j].setVisible(false);
                frame.add(key2[i][j]);
            }
        }

        JLabel invLabel = new JLabel("Inverse Hill Key : ");
        invLabel.setBounds(130, 430, 720, 20);
        frame.add(invLabel);

        invKey3 = new JTextField[][]
                {
                        new JTextField[]{new JTextField(), new JTextField(), new JTextField()},
                        new JTextField[]{new JTextField(), new JTextField(), new JTextField()},
                        new JTextField[]{new JTextField(), new JTextField(), new JTextField()}
                };
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                invKey3[i][j].setBounds(130 + 25 * j, 450 + 25 * i, 25, 25);
                invKey3[i][j].setText("0");
                invKey3[i][j].setVisible(true);
                frame.add(invKey3[i][j]);
            }
        }

        invKey2 = new JTextField[][]
                {
                        new JTextField[]{new JTextField(), new JTextField()},
                        new JTextField[]{new JTextField(), new JTextField()}
                };
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                invKey2[i][j].setBounds(130 + 30 * j, 450 + 30 * i, 30, 30);
                invKey2[i][j].setText("0");
                invKey2[i][j].setVisible(false);
                frame.add(invKey2[i][j]);
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

        inverseBtn = new JButton("Inv Key");
        inverseBtn.addActionListener(this);
        inverseBtn.setBounds(350, 170, 80, 30);
        frame.add(inverseBtn);

        frame.setVisible(false);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == inverseBtn) {
            if (keySize.getSelectedIndex() == 1) {
                int[][] key = new int[3][3];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        key[i][j] = Integer.parseInt(key3[i][j].getText());
                    }
                }
                HillCypher.generateInverse3(key);
                int[][] inv = HillCypher.inverse3;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        invKey3[i][j].setText(String.valueOf(inv[i][j]));
                    }
                }
            } else {
                int[][] key = new int[2][2];
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        key[i][j] = Integer.parseInt(key2[i][j].getText());
                    }
                }
                HillCypher.generateInverse2(key);
                int[][] inv = HillCypher.inverse2;
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        invKey2[i][j].setText(String.valueOf(inv[i][j]));
                    }
                }
            }
        }
        if (e.getSource() == keySize) {
            if (keySize.getSelectedIndex() == 1) {
                hideKey(key2);
                showKey(key3);
            } else {
                hideKey(key3);
                showKey(key2);
            }
        }
        if (e.getSource() == decryptBtn) {
            if (keySize.getSelectedIndex() == 1) {
                int[][] key = new int[3][3];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        key[i][j] = Integer.parseInt(key3[i][j].getText());
                    }
                }
                plain.setText(HillCypher.decrypt3(cypher.getText(), key));
            } else {
                int[][] key = new int[2][2];
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        key[i][j] = Integer.parseInt(key2[i][j].getText());
                    }
                }
                plain.setText(HillCypher.decrypt2(cypher.getText(), key));
            }
        }
        if (e.getSource() == encryptBtn) {
            if (keySize.getSelectedIndex() == 1) {
                int[][] key = new int[3][3];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        key[i][j] = Integer.parseInt(key3[i][j].getText());
                    }
                }
                cypher.setText(HillCypher.encrypt3(plain.getText(), key));
            } else {
                int[][] key = new int[2][2];
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        key[i][j] = Integer.parseInt(key2[i][j].getText());
                    }
                }
                cypher.setText(HillCypher.encrypt2(plain.getText(), key));
            }
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
