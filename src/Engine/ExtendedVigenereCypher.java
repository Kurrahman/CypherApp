package Engine;

import Utils.Utils;

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
    public static void main(String[] args) {

        String plain = "Semburan lumpur panas di desa Porong, Sidoarjo, Jawa Timur belum\n" +
                "juga berakhir. Sudah beberapa desa tenggelam. Entah sudah berapa\n" +
                "rumah, bangunan, pabrik, dan sawah yang tenggelam.\n" +
                "Sampai kapan semburan lumpur berhenti, tiada yang tahu. Teknologi\n" +
                "manusia tidak berhasil menutupi lubang semburan. Jika semburan\n" +
                "lumpur tidak berhenti juga, mungkin Jawa Timur akan tenggelam";
        String key = "langitbiru";
        String cypher = ExtendedVigenereCypher.encrypt(plain,key);
        System.out.println(cypher);
        System.out.println(VigenereCypher.decrypt(cypher, key));
    }
}
