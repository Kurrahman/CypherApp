package Engine;

import Utils.Utils;

import java.util.ArrayList;

public class FullVigenereCypher {
    static ArrayList<ArrayList<Character>> table = new ArrayList<>();

    public static void initVigenereTable(int seed) {
        table.clear();
        Utils.setRandomSeed(seed);
        Character tmp;
        for (int i = 0; i < 26; i++) {
            table.add(new ArrayList<>());
            for (int j = 0; j < 26; j++) {
                table.get(i).add('0');
            }
        }
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                while (true) {
                    tmp = Utils.getNextRandomChar();
                    if (!table.get(i).contains(tmp)) {
                        table.get(i).set(j, tmp);
                        break;
                    }
                }
            }
        }
    }

    public static String encrypt(String plain, String key) {
        StringBuilder cypher = new StringBuilder();
        String upPlain = plain.toUpperCase();
        String upKey = key.toUpperCase();

        int plainLength = upPlain.length();
        int keyLength = upKey.length();

        int plainPointer = 0;
        int keyPointer = 0;

        char plainChar, keyChar;
        while (plainPointer < plainLength) {
            plainChar = upPlain.charAt(plainPointer);
            keyChar = upKey.charAt(keyPointer);

            if (!Utils.isAlphabet(plainChar)) {
                plainPointer += 1;
                cypher.append(plainChar);
            } else if (!Utils.isAlphabet(keyChar)) {
                keyPointer = (keyPointer + 1) % keyLength;
            } else {
                plainPointer += 1;
                keyPointer = (keyPointer + 1) % keyLength;
                cypher.append(table.get(Utils.charToInt(keyChar)).get(Utils.charToInt(plainChar)));
            }
        }
        return cypher.toString();
    }

    public static String decrypt(String cypher, String key) {
        StringBuilder plain = new StringBuilder();
        String upCypher = cypher.toUpperCase();
        String upKey = key.toUpperCase();

        int cypherLength = upCypher.length();
        int keyLength = upKey.length();

        int cypherPointer = 0;
        int keyPointer = 0;

        char cypherChar, keyChar;
        while (cypherPointer < cypherLength) {
            cypherChar = upCypher.charAt(cypherPointer);
            keyChar = upKey.charAt(keyPointer);

            if (!Utils.isAlphabet(cypherChar)) {
                cypherPointer += 1;
//                plain.append(cypherChar);
            } else if (!Utils.isAlphabet(keyChar)) {
                keyPointer = (keyPointer + 1) % keyLength;
            } else {
                cypherPointer += 1;
                keyPointer = (keyPointer + 1) % keyLength;
                plain.append(Utils.intToChar(table.get(Utils.charToInt(keyChar)).indexOf(cypherChar)));
            }
        }
        return plain.toString();
    }

    public static void main(String[] args) {
        FullVigenereCypher.initVigenereTable(100);
        for (int i = 0; i < 26; i++) {
            System.out.println(table.get(i).toString());
        }
        String plain = "Random number";
        String key = "test";
        String cypher = FullVigenereCypher.encrypt(plain, key);
        System.out.println(cypher);
        System.out.println(FullVigenereCypher.decrypt(cypher, key));
    }
}
