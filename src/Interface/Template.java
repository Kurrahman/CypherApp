package Interface;

import Engine.ExtendedVigenereCypher;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class Template implements ActionListener {
    protected JTextArea plain, cypher;
    protected JButton encryptBtn, decryptBtn, loadPlain, savePlain, loadCypher, saveCypher, splitCypher;
    protected JFileChooser fc;
    protected JScrollPane plainScroll, cypherScroll;
    Border textArea = BorderFactory.createLoweredBevelBorder();

    public void displayBase(JFrame frame) {
        frame.getContentPane().removeAll();

        JLabel plainLabel = new JLabel("Plain Text : ");
        plainLabel.setBounds(30, 5, 300, 20);
        frame.add(plainLabel);

        savePlain = new JButton("Save");
        savePlain.addActionListener(this);
        savePlain.setBounds(165, 5, 80, 20);
        frame.add(savePlain);

        loadPlain = new JButton("Load");
        loadPlain.addActionListener(this);
        loadPlain.setBounds(250, 5, 80, 20);
        frame.add(loadPlain);

        JLabel cypherLabel = new JLabel("Cypher Text : ");
        cypherLabel.setBounds(450, 5, 300, 20);
        frame.add(cypherLabel);

        saveCypher = new JButton("Save");
        saveCypher.addActionListener(this);
        saveCypher.setBounds(585, 5, 80, 20);
        frame.add(saveCypher);

        loadCypher = new JButton("Load");
        loadCypher.addActionListener(this);
        loadCypher.setBounds(670, 5, 80, 20);
        frame.add(loadCypher);

        plain = new JTextArea();
        plain.setBorder(textArea);
        plain.setLineWrap(true);
        plain.setBounds(30, 30, 300, 400);

        plainScroll = new JScrollPane(plain);
        plainScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(plain);
        frame.add(plainScroll);

        cypher = new JTextArea();
        cypher.setBorder(textArea);
        cypher.setLineWrap(true);
        cypher.setBounds(450, 30, 300, 400);

        cypherScroll = new JScrollPane(cypher);
        cypherScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(cypher);
        frame.add(cypherScroll);

        encryptBtn = new JButton("Encrypt");
        encryptBtn.addActionListener(this);
        encryptBtn.setBounds(350, 30, 80, 30);
        frame.add(encryptBtn);

        decryptBtn = new JButton("Decrypt");
        decryptBtn.addActionListener(this);
        decryptBtn.setBounds(350, 70, 80, 30);
        frame.add(decryptBtn);

        splitCypher = new JButton("Split");
        splitCypher.addActionListener(this);
        splitCypher.setBounds(350, 400, 80, 30);
        frame.add(splitCypher);

        fc = new JFileChooser(System.getProperty("user.dir"));
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
    }

    protected boolean templateAction(ActionEvent e) {
        return (
                (e.getSource() == loadPlain) ||
                        (e.getSource() == savePlain) ||
                        (e.getSource() == loadCypher) ||
                        (e.getSource() == saveCypher) ||
                        (e.getSource() == splitCypher)
        );
    }

    private String readFile(File file) {
        StringBuilder out = new StringBuilder();
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                out.append(scanner.nextLine());
            }
            return out.toString().trim();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void writeFile(String fileDir, String content) {
        try {
            FileWriter writer = new FileWriter(fileDir);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadPlain) {
            int retVal = fc.showOpenDialog(null);
            if (retVal == JFileChooser.APPROVE_OPTION) {
                plain.setText(readFile(fc.getSelectedFile()));
            }
        }
        if (e.getSource() == savePlain) {
            int retVal = fc.showSaveDialog(null);
            if (retVal == JFileChooser.APPROVE_OPTION) {
                writeFile(fc.getSelectedFile().getAbsolutePath(), plain.getText());
            }
        }
        if (e.getSource() == loadCypher) {
            int retVal = fc.showOpenDialog(null);
            if (retVal == JFileChooser.APPROVE_OPTION) {
                cypher.setText(readFile(fc.getSelectedFile()));
            }
        }
        if (e.getSource() == saveCypher) {
            int retVal = fc.showSaveDialog(null);
            if (retVal == JFileChooser.APPROVE_OPTION) {
                writeFile(fc.getSelectedFile().getAbsolutePath(), cypher.getText());
            }
        }
        if (e.getSource() == splitCypher){
            StringBuilder s = new StringBuilder();
            s.append(cypher.getText().toUpperCase().replaceAll("[^A-Z]", "").trim());
            int i = 0;
            while (i < s.length()){
                if (i % 6 == 5){
                    s.insert(i,' ');
                }
                i++;
            }
            cypher.setText(s.toString().trim());
        }
    }

    public static void main(String[] args) {
        String test = "randomnum123,./ \n";
        test = ExtendedVigenereCypher.encrypt(test, "random");
        System.out.println(Arrays.toString(test.getBytes(StandardCharsets.UTF_8)));
    }
}
