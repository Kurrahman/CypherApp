package Engine;

import Utils.Utils;


public class VigenereCypher {
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
                cypher.append(Utils.vigenereTranspose(plainChar, keyChar));
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
                plain.append(cypherChar);
            } else if (!Utils.isAlphabet(keyChar)) {
                keyPointer = (keyPointer + 1) % keyLength;
            } else {
                cypherPointer += 1;
                keyPointer = (keyPointer + 1) % keyLength;
                plain.append(Utils.vigenereTransposeReverse(cypherChar, keyChar));
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
        String cypher = "DEZHCKBV COXPHX XTOIJ XT DRYI IPZFHR, SVJWTSRF, DLWN ZQFVZ SYWUZ\n" +
                "PCZB JVLLKUOZ. LVLRB MEOKZTQI UYDA GKVZHMCUX. EAZIA TCUUS BRXIIB\n" +
                "ZLGLH, OGVZVVRH, AAOXQD, EIE MLWNN GTOO KYYGTKTTN.\n" +
                "ARGAAV QIIBV JYXBHXIG MCDJFR OKZAFVKC, EINJI RBVX NLHH. ZMDOWCIRI\n" +
                "ZGVNTQR NTDNQ JXSPRMTL ZKVNUCGC WUOGVZ TMDVFRNT. RBLI JYXBHXIG\n" +
                "MCDJFR GOLTL JVLSEAZQ CVOR, GFNTQQG KINU EIZAZ TLIE NPNTMMEBU\n";
        String key = "langitbiru";
//        System.out.println(Utils.vigenereTransposeReverse('D','L'));
        System.out.println(VigenereCypher.decrypt(cypher, key));
    }
}
