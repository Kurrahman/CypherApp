package Utils;

import java.util.Random;

public class Utils {
    static Random gen;
    public static char caesarTranspose(char plain, int offset){
        // Asumsi seluruh karakter yang diterima merupakan huruf kapital
        int cypher = (((int) plain) - 65 + offset) % 26;
        return (char)(cypher + 65);
    }

    public static char caesarTransposeReverse(char plain, int offset){
        // Asumsi seluruh karakter yang diterima merupakan huruf kapital
        int cypher = (((int) plain) - 65 - offset + 26) % 26;
        return (char)(cypher + 65);
    }

    public static char vigenereTranspose(char plain, char key){
        int cypher = ((((int) plain) - 65) + (((int) key) - 65)) % 26;
        return (char)(cypher + 65);
    }

    public static char vigenereTransposeReverse(char plain, char key){
        int cypher = ((((int) plain) - 65) - (((int) key) - 65) + 26) % 26;
        return (char)(cypher + 65);
    }

    public static int charToInt(char c){
        return ((int) c - 65) % 26;
    }

    public static char intToChar(int i){
        return (char) (i + 65);
    }

    public static void setRandomSeed(int seed){
        gen = new Random(seed);
    }

    public static char getNextRandomChar(){
        return (char) (gen.nextInt(26) + 65);
    }

    public static void main(String[] args) {
        setRandomSeed(69);
        for (int i = 0; i < 26; i++) {
            System.out.println(getNextRandomChar());
        }
    }
}
