package Utils;

import java.util.Random;

public class Utils {
    static Random gen;

    public static char caesarTranspose(char plain, int offset) {
        // Asumsi seluruh karakter yang diterima merupakan huruf kapital
        int cypher = (((int) plain) - 65 + offset) % 26;
        return (char) (cypher + 65);
    }

    public static char caesarTransposeReverse(char plain, int offset) {
        // Asumsi seluruh karakter yang diterima merupakan huruf kapital
        int cypher = (((int) plain) - 65 - offset + 26) % 26;
        return (char) (cypher + 65);
    }

    public static char vigenereTranspose(char plain, char key) {
        int cypher = ((((int) plain) - 65) + (((int) key) - 65)) % 26;
        return (char) (cypher + 65);
    }

    public static char vigenereTransposeReverse(char plain, char key) {
        int cypher = ((((int) plain) - 65) - (((int) key) - 65) + 26) % 26;
        return (char) (cypher + 65);
    }

    public static int modulo(int a, int b){
        return ((a % b) + b) % b;
    }

    public static int invModulo(int a, int b){
        int i;
        for (i = 1; i < b; i++) {
            if (modulo(a*i, b) == 1){
                break;
            }
        }
        return i;
    }

    public static char extendedVigenereTranspose(char plain, char key) {
        int cypher = ((int) plain) + ((int) key) % 256;
        return (char) cypher;
    }

    public static char extendedVigenereTransposeReverse(char plain, char key) {
        int cypher = (((int) plain) - ((int) key) + 256) % 256;
        return (char) cypher;
    }

    public static boolean isAlphabet(char c) {
        return ((((int) c) > 64) && (((int) c) < 91));
    }

    public static int charToInt(char c) {
        return ((int) c - 65) % 26;
    }

    public static char intToChar(int i) {
        return (char) (i + 65);
    }

    public static void setRandomSeed(int seed) {
        gen = new Random(seed);
    }

    public static char getNextRandomChar() {
        return (char) (gen.nextInt(26) + 65);
    }

    public static int[][] matrixMultiplication(int[][] a, int[][] b){
        int[][] c = new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                c[i][j] = 0;
                for (int k = 0; k < b[0].length; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
                c[i][j] = c[i][j] % 26;
            }
        }
        return c;
    }

    public static void main(String[] args) {
        setRandomSeed(69);
        for (int i = 0; i < 26; i++) {
            System.out.println(getNextRandomChar());
        }
    }
}
