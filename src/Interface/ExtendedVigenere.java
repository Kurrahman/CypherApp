package Interface;

import Engine.ExtendedVigenereCypher;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class ExtendedVigenere extends Template implements ActionListener {
    private JTextArea key;
    private JButton encryptFileBtn, decryptFileBtn;
    private byte[] plainFile, cypherFile;

    public void displayExtendedVigenere(JFrame frame) {
        displayBase(frame);

        encryptFileBtn = new JButton("En File");
        encryptFileBtn.addActionListener(this);
        encryptFileBtn.setBounds(350, 310, 80, 30);
        frame.add(encryptFileBtn);

        decryptFileBtn = new JButton("De File");
        decryptFileBtn.addActionListener(this);
        decryptFileBtn.setBounds(350, 350, 80, 30);
        frame.add(decryptFileBtn);

        JLabel keyLabel = new JLabel("Vigenere Key : ");
        keyLabel.setBounds(30, 430, 720, 20);
        frame.add(keyLabel);

        key = new JTextArea();
        key.setBorder(textArea);
        key.setLineWrap(true);
        key.setBounds(30, 450, 720, 70);
        frame.add(key);
        frame.remove(splitCypher);

        frame.setVisible(false);
        frame.setVisible(true);
    }

    private byte[] readByteFile(File file){
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void writeByteFile(byte[] content){
        File tmp = new File(fc.getSelectedFile().getParent() + "\\output");
//        System.out.println(tmp.getAbsolutePath());
        try (FileOutputStream stream = new FileOutputStream(tmp.getAbsolutePath())){
            stream.write(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadPlain){
            int retVal = fc.showOpenDialog(null);
            if (retVal == JFileChooser.APPROVE_OPTION) {
                plainFile = readByteFile(fc.getSelectedFile());
                if (plainFile == null){
                    plain.setText("File loading failed");
                } else {
                    plain.setText(Arrays.toString(plainFile));
                }
            }
        }
        if (e.getSource() == loadCypher){
            int retVal = fc.showOpenDialog(null);
            if (retVal == JFileChooser.APPROVE_OPTION) {
                cypherFile = readByteFile(fc.getSelectedFile());
                if (cypherFile == null){
                    cypher.setText("File loading failed");
                } else {
                    cypher.setText(Arrays.toString(cypherFile));
                }
            }
        }
        if (e.getSource() == encryptFileBtn){
            byte[] out = ExtendedVigenereCypher.encryptByte(plainFile, key.getText());
            cypher.setText(Arrays.toString(out));
            writeByteFile(out);
        }
        if (e.getSource() == decryptFileBtn){
            byte[] out = ExtendedVigenereCypher.decryptByte(cypherFile, key.getText());
            plain.setText(Arrays.toString(out));
            writeByteFile(out);
        }
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
