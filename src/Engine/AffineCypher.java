package Engine;

import Utils.Utils;

import java.util.HashMap;

public class AffineCypher {
    private static final HashMap<Character, Character> encryptMap = new HashMap<>();
    private static final HashMap<Character, Character> decryptMap = new HashMap<>();
    private static final Character[] ALPHABET = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static void generateCypherMap(int a, int b) {
        encryptMap.clear();
        decryptMap.clear();

        for (Character c : ALPHABET) {
            int x = Utils.charToInt(c);
            int y = (a * x + b) % 26;
            char tmp = Utils.intToChar(y);
            encryptMap.put(c, tmp);
            decryptMap.put(tmp, c);
        }
    }

    public static String encrypt(String plain, int a, int b) {
        StringBuilder out = new StringBuilder();
        generateCypherMap(a, b);
        char[] upPlain = plain.toUpperCase().trim().toCharArray();

        for (char c : upPlain) {
            if (Utils.isAlphabet(c)) {
                out.append(encryptMap.get(c));
            } else {
                out.append(c);
            }
        }

        return out.toString().trim();
    }

    public static String decrypt(String cypher, int a, int b) {
        StringBuilder out = new StringBuilder();
        generateCypherMap(a, b);
        char[] upCypher = cypher.toUpperCase().trim().toCharArray();

        for (char c : upCypher) {
            if (Utils.isAlphabet(c)) {
                out.append(decryptMap.get(c));
            } else {
                out.append(c);
            }
        }

        return out.toString().trim();
    }


    public static void main(String[] args) {
        String plain = "kripto";
        String cypher = AffineCypher.encrypt(plain, 7, 10);
        System.out.println(cypher);
        System.out.println(AffineCypher.decrypt(cypher, 7, 10));
    }
}
