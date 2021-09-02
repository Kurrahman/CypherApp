package Engine;

import Interface.AutoKeyVigenere;
import Utils.Utils;

public class AutoKeyVigenereCypher {

    public static String encrypt(String plain, String key){
        StringBuilder cypher = new StringBuilder();
        String upPlain = plain.toUpperCase();
        String upKey = key.toUpperCase() + upPlain;

        int plainLength = upPlain.length();
        int keyLength = upKey.length();

        int plainPointer = 0;
        int keyPointer = 0;

        char plainChar, keyChar;
        while (plainPointer < plainLength){
            plainChar = upPlain.charAt(plainPointer);
            keyChar = upKey.charAt(keyPointer);

            if ((((int)plainChar) < 65) || (((int)plainChar) > 90)){
                plainPointer += 1;
                cypher.append(plainChar);
            } else if ((((int)keyChar) < 65) || (((int)keyChar) > 90)){
                keyPointer = (keyPointer + 1) % keyLength;
            } else {
                plainPointer += 1;
                keyPointer = (keyPointer + 1) % keyLength;
                cypher.append(Utils.vigenereTranspose(plainChar, keyChar));
            }
        }
        return cypher.toString();
    }

    public static String decrypt(String cypher, String key){
        StringBuilder plain = new StringBuilder();
        String upCypher = cypher.toUpperCase();
        String upKey = key.toUpperCase();

        int cypherLength = upCypher.length();

        int cypherPointer = 0;
        int keyPointer = 0;

        char cypherChar, keyChar;
        while (cypherPointer < cypherLength){
            cypherChar = upCypher.charAt(cypherPointer);
            keyChar = upKey.charAt(keyPointer);

            if ((((int)cypherChar) < 65) || (((int)cypherChar) > 90)){
                cypherPointer += 1;
                plain.append(cypherChar);
            } else if ((((int)keyChar) < 65) || (((int)keyChar) > 90)){
                keyPointer += keyPointer + 1;
            } else {
                cypherPointer += 1;
                keyPointer = keyPointer + 1;
                Character tmp = Utils.vigenereTransposeReverse(cypherChar, keyChar);
                plain.append(tmp);
                upKey = upKey.concat(tmp.toString());
            }
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
        String cypher = AutoKeyVigenereCypher.encrypt(plain, key);
        System.out.println(cypher);
        System.out.println(AutoKeyVigenereCypher.decrypt(cypher,key));
    }
}
