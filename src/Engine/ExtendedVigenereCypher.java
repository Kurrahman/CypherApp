package Engine;

import Utils.Utils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ExtendedVigenereCypher {

    public static String encrypt(String plain, String key) {
        StringBuilder cypher = new StringBuilder();

        int plainLength = plain.length();
        int keyLength = key.length();

        int plainPointer = 0;
        int keyPointer = 0;

        char plainChar, keyChar;
        while (plainPointer < plainLength) {
            plainChar = plain.charAt(plainPointer);
            keyChar = key.charAt(keyPointer);

            plainPointer += 1;
            keyPointer = (keyPointer + 1) % keyLength;
            cypher.append(Utils.extendedVigenereTranspose(plainChar, keyChar));
        }
        return cypher.toString();
    }

    public static byte[] encryptByte(byte[] plain, String key){
        int plainLength = plain.length;
        int keyLength = key.length();
        byte[] cypher = new byte[plainLength];
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);


        int plainPointer = 0;
        int keyPointer = 0;
        int cypherPointer = 0;

        byte plainByte, keyByte;
        while (plainPointer < plainLength) {
            plainByte = plain[plainPointer];
            keyByte = keyBytes[keyPointer];

            plainPointer += 1;
            keyPointer = (keyPointer + 1) % keyLength;
            cypher[cypherPointer++] = Utils.extendedVigenereByteTranspose(plainByte, keyByte);
        }
        return cypher;
    }

    public static String decrypt(String cypher, String key) {
        StringBuilder plain = new StringBuilder();

        int cypherLength = cypher.length();
        int keyLength = key.length();

        int cypherPointer = 0;
        int keyPointer = 0;

        char cypherChar, keyChar;
        while (cypherPointer < cypherLength) {
            cypherChar = cypher.charAt(cypherPointer);
            keyChar = key.charAt(keyPointer);

            cypherPointer += 1;
            keyPointer = (keyPointer + 1) % keyLength;
            plain.append(Utils.extendedVigenereTransposeReverse(cypherChar, keyChar));
        }
        return plain.toString();
    }

    public static byte[] decryptByte(byte[] cypher, String key){
        int plainLength = cypher.length;
        int keyLength = key.length();
        byte[] plain = new byte[plainLength];
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);


        int cypherPointer = 0;
        int keyPointer = 0;
        int plainPointer = 0;

        byte cypherByte, keyByte;
        while (plainPointer < plainLength) {
            cypherByte = cypher[cypherPointer];
            keyByte = keyBytes[keyPointer];

            cypherPointer += 1;
            keyPointer = (keyPointer + 1) % keyLength;
            plain[plainPointer++] = Utils.extendedVigenereByteTransposeReverse(cypherByte, keyByte);
        }
        return plain;
    }

    public static void main(String[] args) {

        byte[] plain = {-1, -40, -1, -32, 0, 16, 74, 70, 73, 70, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, -1, -37, 0};
        String key = "random";
        byte[] cypher = ExtendedVigenereCypher.encryptByte(plain, key);

        System.out.println(Arrays.toString(plain));
        System.out.println(Arrays.toString(cypher));
        System.out.println(Arrays.toString(ExtendedVigenereCypher.decryptByte(cypher, key)));
    }
}
