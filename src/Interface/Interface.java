package Interface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface implements ActionListener {

    JMenuItem vigenere, fullVigenere, autoKeyVigenere, extVigenere, playFair, affine, hill, enigma;
    JMenu cypherMenu;
    JMenuBar menuBar;
    JFrame frame;

    private void initFrame() {
        frame = new JFrame("CypherApp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(menuBar);
        frame.setSize(800, 600);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private void initMenuBar() {
        menuBar = new JMenuBar();
        menuBar.add(cypherMenu);
    }

    private void initCypherMenu() {
        cypherMenu = new JMenu("Cypher");

        vigenere = new JMenuItem("Vigenere");
        fullVigenere = new JMenuItem("Full Vigenere");
        autoKeyVigenere = new JMenuItem("Auto-Key Vigenere");
        extVigenere = new JMenuItem("Extended Vigenere");
        playFair = new JMenuItem("PlayFair");
        affine = new JMenuItem("Affine");
        hill = new JMenuItem("Hill");
        enigma = new JMenuItem("Enigma");

        vigenere.addActionListener(this);
        fullVigenere.addActionListener(this);
        autoKeyVigenere.addActionListener(this);
        extVigenere.addActionListener(this);
        playFair.addActionListener(this);
        affine.addActionListener(this);
        hill.addActionListener(this);
        enigma.addActionListener(this);

        cypherMenu.add(vigenere);
        cypherMenu.add(fullVigenere);
        cypherMenu.add(autoKeyVigenere);
        cypherMenu.add(extVigenere);
        cypherMenu.add(playFair);
        cypherMenu.add(affine);
        cypherMenu.add(hill);
        cypherMenu.add(enigma);
    }

    public void initInterface() {
        initCypherMenu();
        initMenuBar();
        initFrame();
    }

    public static void main(String[] args) {
        Interface i = new Interface();
        i.initInterface();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vigenere) {
            Vigenere display = new Vigenere();
            display.displayVigenere(frame);
        }
        if (e.getSource() == fullVigenere) {
            FullVigenere display = new FullVigenere();
            display.displayFullVigenere(frame);
        }
        if (e.getSource() == autoKeyVigenere) {
            AutoKeyVigenere display = new AutoKeyVigenere();
            display.displayAutoKeyVigenere(frame);
        }
        if (e.getSource() == extVigenere) {
            ExtendedVigenere display = new ExtendedVigenere();
            display.displayExtendedVigenere(frame);
        }
        if (e.getSource() == playFair) {
            System.out.println("PlayFair");
        }
        if (e.getSource() == affine) {
            System.out.println("Affine");
        }
        if (e.getSource() == hill) {
            System.out.println("Hill");
        }
        if (e.getSource() == enigma) {
            System.out.println("Enigma");
        }
    }
}
