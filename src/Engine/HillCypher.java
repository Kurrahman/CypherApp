package Engine;

import Utils.Utils;

public class HillCypher {
    public static int[][] inverse3 = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
    };
    public static int[][] inverse2 = {
            {0, 0},
            {0, 0},
    };

    public static void generateInverse2(int[][] key) {
        int a = key[0][0];
        int b = key[0][1];
        int c = key[1][0];
        int d = key[1][1];

        int det = Utils.modulo(a * d - b * c, 26);
        det = Utils.invModulo(det, 26);

        inverse2[0][0] = (Utils.modulo(Utils.modulo(d, 26) * det, 26));
        inverse2[0][1] = (Utils.modulo(Utils.modulo(-b, 26) * det, 26));
        inverse2[1][0] = (Utils.modulo(Utils.modulo(-c, 26) * det, 26));
        inverse2[1][1] = (Utils.modulo(Utils.modulo(a, 26) * det, 26));
    }

    public static void generateInverse3(int[][] key) {
        int a = key[1][1] * key[2][2] - key[1][2] * key[2][1],
                b = -(key[1][0] * key[2][2] - key[2][0] * key[1][2]),
                c = key[1][0] * key[2][1] - key[1][1] * key[2][0],
                d = -(key[0][1] * key[2][2] - key[0][2] * key[2][1]),
                e = key[0][0] * key[2][2] - key[0][2] * key[2][0],
                f = -(key[0][0] * key[2][1] - key[0][1] * key[2][0]),
                g = key[0][1] * key[1][2] - key[0][2] * key[1][1],
                h = -(key[0][0] * key[1][2] - key[0][2] * key[1][0]),
                i = key[0][0] * key[1][1] - key[1][0] * key[0][1];
        int[][] trans = {
                {a, d, g},
                {b, e, h},
                {c, f, i}
        };
        int det = Utils.modulo(a * key[0][0] + b * key[0][1] + c * key[0][2], 26);
        det = Utils.invModulo(det, 26);

        for (int k = 0; k < 3; k++) {
            for (int j = 0; j < 3; j++) {
                inverse3[k][j] = Utils.modulo(Utils.modulo(trans[k][j], 26) * det, 26);
            }
        }
    }

    public static String encrypt3(String plain, int[][] key) {
        StringBuilder out = new StringBuilder();
        String upPlain = plain.toUpperCase().trim();
        upPlain = upPlain.replaceAll("[^A-Z]", "");
        int length = upPlain.length();
        switch (length % 3) {
            case 1: {
                upPlain = upPlain + "ZZ";
                break;
            }
            case 2: {
                upPlain = upPlain + "Z";
                break;
            }
        }
        int i = 0;
        while (i < length) {
            int[][] tmp = {
                    {Utils.charToInt(upPlain.charAt(i))},
                    {Utils.charToInt(upPlain.charAt(i + 1))},
                    {Utils.charToInt(upPlain.charAt(i + 2))},
            };
            int[][] res = Utils.matrixMultiplication(key, tmp);
            for (int j = 0; j < 3; j++) {
                res[j][0] = Utils.modulo(res[j][0], 26);
            }
            for (int[] row : res) {
                out.append(Utils.intToChar(row[0]));
            }
            i += 3;
        }
        return out.toString().trim();
    }

    public static String decrypt3(String cypher, int[][] key) {
        generateInverse3(key);
        StringBuilder out = new StringBuilder();
        String upCypher = cypher.toUpperCase().trim();
        upCypher = upCypher.replaceAll("[^A-Z]", "");
        int length = upCypher.length();
        int i = 0;
        while (i < length) {
            int[][] tmp = {
                    {Utils.charToInt(upCypher.charAt(i))},
                    {Utils.charToInt(upCypher.charAt(i + 1))},
                    {Utils.charToInt(upCypher.charAt(i + 2))},
            };
            int[][] res = Utils.matrixMultiplication(inverse3, tmp);
            for (int j = 0; j < 3; j++) {
                res[j][0] = Utils.modulo(res[j][0], 26);
            }
            for (int[] row : res) {
                out.append(Utils.intToChar(row[0]));
            }
            i += 3;
        }
        return out.toString().trim();
    }

    public static String encrypt2(String plain, int[][] key) {
        StringBuilder out = new StringBuilder();
        String upPlain = plain.toUpperCase().trim();
        upPlain = upPlain.replaceAll("[^A-Z]", "");
        int length = upPlain.length();
        if (length % 2 == 1) {
            upPlain = upPlain + "Z";
        }
        int i = 0;
        while (i < length) {
            int[][] tmp = {
                    {Utils.charToInt(upPlain.charAt(i))},
                    {Utils.charToInt(upPlain.charAt(i + 1))}
            };
            int[][] res = Utils.matrixMultiplication(key, tmp);
            for (int j = 0; j < 2; j++) {
                res[j][0] = Utils.modulo(res[j][0], 26);
            }
            for (int[] row : res) {
                out.append(Utils.intToChar(row[0]));
            }
            i += 2;
        }
        return out.toString().trim();
    }

    public static String decrypt2(String cypher, int[][] key) {
        generateInverse2(key);
        StringBuilder out = new StringBuilder();
        String upCypher = cypher.toUpperCase().trim();
        upCypher = upCypher.replaceAll("[^A-Z]", "");
        int length = upCypher.length();
        if (length % 2 == 1) {
            upCypher = upCypher + "Z";
        }
        int i = 0;
        while (i < length) {
            int[][] tmp = {
                    {Utils.charToInt(upCypher.charAt(i))},
                    {Utils.charToInt(upCypher.charAt(i + 1))}
            };
            int[][] res = Utils.matrixMultiplication(inverse2, tmp);
            for (int j = 0; j < 2; j++) {
                res[j][0] = Utils.modulo(res[j][0], 26);
            }
            for (int[] row : res) {
                out.append(Utils.intToChar(row[0]));
            }
            i += 2;
        }
        return out.toString().trim();
    }

    public static void main(String[] args) {
        int[][] key = {
                {17, 17, 5},
                {21, 18, 21},
                {2, 2, 19}
        };

        int[][] key2 = {
                {3, 3},
                {2, 5}
        };

        String cypher = HillCypher.encrypt3("paymoremoney", key);
        System.out.println(cypher);
        System.out.println(HillCypher.decrypt3(cypher, key));

        String cypher2 = HillCypher.encrypt2("help", key2);
        System.out.println(cypher2);
        System.out.println(HillCypher.decrypt2(cypher2, key2));
    }
}
