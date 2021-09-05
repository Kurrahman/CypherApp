package Engine;

import Utils.Utils;

import java.util.ArrayList;

public class PlayFairCypher {
    static ArrayList<ArrayList<Character>> square = new ArrayList<>();

    public static ArrayList<ArrayList<Character>> getSquare() {
        return square;
    }

    public static void generateSquare(String key) {
        square.clear();
        for (int i = 0; i < 26; i++) {
            square.add(new ArrayList<>());
            for (int j = 0; j < 26; j++) {
                square.get(i).add('0');
            }
        }


        key = key.toUpperCase() + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int keyLength = key.length();
        StringBuilder sequence = new StringBuilder();
        ArrayList<Integer> charIndex = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            charIndex.add(key.indexOf(Utils.intToChar(i)));
        }

        for (int i = 0; i < keyLength; i++) {
            int tmp = charIndex.indexOf(i);
            if ((tmp >= 0) && (tmp != 9)) {
                sequence.append(Utils.intToChar(tmp));
            }
        }

        String temp = sequence.toString();

        for (int i = 0; i < 5; i++) {
            ArrayList<Character> tmp = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                tmp.add(temp.charAt(i * 5 + j));
            }
            square.set(i, tmp);
        }
    }

    public static String generateBigraph(String plain) {
        StringBuilder out = new StringBuilder();
        String upPlain = plain.toUpperCase().trim();
        int length = upPlain.length();
        int i = 0;
        char lastChar = ' ';
        boolean firstChar = true;
        while (i < length) {
            Character tmp = upPlain.charAt(i);
            if (Utils.isAlphabet(tmp)) {
                if (tmp == 'J') {
                    tmp = 'I';
                }
                if (firstChar) {
                    if (i == length - 1) {
                        out.append(tmp).append('X');
                    } else {
                        out.append(tmp);
                        firstChar = false;
                    }
                    lastChar = tmp;
                } else {
                    if (tmp == lastChar) {
                        out.append("X ").append(tmp);
                        if (i == length - 1) {
                            out.append('X');
                            break;
                        }
                        lastChar = 'X';
                    } else if (tmp == 'X') {
                        out.append("X X");
                        if (i == length - 1) {
                            out.append('X');
                        }
                        lastChar = tmp;
                    } else {
                        out.append(tmp).append(' ');
                        firstChar = true;
                    }
                }
            }
            i += 1;
        }
        return out.toString();
    }

    public static String encrypt(String plain, String key) {
        generateSquare(key);
        String[] bigraphs = generateBigraph(plain).split(" ");

        StringBuilder out = new StringBuilder();
        for (String bigraph : bigraphs) {
            char a = bigraph.charAt(0), b = bigraph.charAt(1);
            int xa = 0, ya = 0, xb = 0, yb = 0, xc, yc, xd, yd;
            for (int j = 0; j < 5; j++) {
                int idx = square.get(j).indexOf(a);
                if (idx >= 0) {
                    xa = j;
                    ya = idx;
                }
            }

            for (int j = 0; j < 5; j++) {
                int idx = square.get(j).indexOf(b);
                if (idx >= 0) {
                    xb = j;
                    yb = idx;
                }
            }

            if (xa == xb) {
                xc = xa;
                xd = xa;
                yc = (ya + 1) % 5;
                yd = (yb + 1) % 5;
            } else if (ya == yb) {
                yc = ya;
                yd = ya;
                xc = (xa + 1) % 5;
                xd = (xb + 1) % 5;
            } else {
                xc = xa;
                yc = yb;
                xd = xb;
                yd = ya;
            }
            out.append(square.get(xc).get(yc)).append(square.get(xd).get(yd)).append(" ");
        }
        return out.toString().trim();
    }

    public static String decrypt(String cypher, String key) {
        generateSquare(key);
        StringBuilder tmp = new StringBuilder();
        tmp.append(cypher.toUpperCase().replaceAll("[^A-Z]", "").trim());
        for (int i = 0; i < tmp.length(); i++) {
            if (i % 3 == 2){
                tmp.insert(i,' ');
            }
        }
        String[] bigraphs = tmp.toString().split(" ");

        StringBuilder out = new StringBuilder();
        for (String bigraph : bigraphs) {
            char a = bigraph.charAt(0), b = bigraph.charAt(1);
            int xa = 0, ya = 0, xb = 0, yb = 0, xc, yc, xd, yd;
            for (int j = 0; j < 5; j++) {
                int idx = square.get(j).indexOf(a);
                if (idx >= 0) {
                    xa = j;
                    ya = idx;
                }
            }

            for (int j = 0; j < 5; j++) {
                int idx = square.get(j).indexOf(b);
                if (idx >= 0) {
                    xb = j;
                    yb = idx;
                }
            }

            if (xa == xb) {
                xc = xa;
                xd = xa;
                yc = ((ya - 1) + 5) % 5;
                yd = ((yb - 1) + 5) % 5;
            } else if (ya == yb) {
                yc = ya;
                yd = ya;
                xc = ((xa - 1) + 5) % 5;
                xd = ((xb - 1) + 5) % 5;
            } else {
                xc = xa;
                yc = yb;
                xd = xb;
                yd = ya;
            }
            out.append(square.get(xc).get(yc)).append(square.get(xd).get(yd) == 'X' ? new Character(' ') : square.get(xd).get(yd)).append(" ");
        }
        return out.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println(PlayFairCypher.generateBigraph("temui ibu nanti malax"));
        String cypher = PlayFairCypher.encrypt("temui ibu nanti malax", "JALAN GANESHA SEPULUH");
        System.out.println(cypher);
        System.out.println(PlayFairCypher.decrypt(cypher, "JALAN GANESHA SEPULUH"));
    }
}
