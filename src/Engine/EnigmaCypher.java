package Engine;

import Utils.Utils;

public class EnigmaCypher {
    private static final Integer[] WHEEL_0 = {0, 18, 9, 13, 25, 19, 7, 15, 6, 21, 3, 10, 4, 16, 8, 11, 22, 17, 1, 24, 5, 23, 12, 20, 2, 14};
    private static final Integer[] WHEEL_1 = {0, 5, 3, 14, 2, 13, 11, 22, 4, 15, 1, 21, 18, 10, 17, 24, 23, 12, 6, 9, 7, 20, 8, 25, 16, 19};
    private static final Integer[] WHEEL_2 = {7, 17, 25, 16, 19, 21, 9, 2, 12, 10, 3, 22, 4, 23, 8, 11, 24, 15, 18, 5, 14, 20, 1, 6, 0, 13};

    private static final Integer[][] wheels = new Integer[3][26];
    private static final Integer[] counters = new Integer[3];

    public static void resetMachine() {
        System.arraycopy(WHEEL_0, 0, wheels[0], 0, 26);
        System.arraycopy(WHEEL_1, 0, wheels[1], 0, 26);
        System.arraycopy(WHEEL_2, 0, wheels[2], 0, 26);

        counters[0] = 0;
        counters[1] = 0;
        counters[2] = 0;
    }

    private static void rotateWheelNext(Integer[] wheel) {
        int tmp = wheel[25];
        System.arraycopy(wheel, 0, wheel, 1, 25);
        wheel[0] = tmp;
    }

    private static void rotateWheelPrev(Integer[] wheel) {
        int tmp = wheel[0];
        System.arraycopy(wheel, 1, wheel, 0, 25);
        wheel[25] = tmp;
    }

    private static void rotateNext() {
        rotateWheelNext(wheels[2]);
        counters[2] = Utils.modulo((counters[2] + 1), 26);
        if (counters[2] == 0) {
            rotateWheelNext(wheels[1]);
            counters[1] = Utils.modulo((counters[1] + 1), 26);
            if (counters[1] == 0) {
                rotateWheelNext(wheels[0]);
                counters[0] = Utils.modulo((counters[0] + 1), 26);
            }
        }
    }

    private static void rotatePrev() {
        rotateWheelPrev(wheels[2]);
        counters[2] = Utils.modulo((counters[2] - 1), 26);
        if (counters[2] == 25) {
            rotateWheelPrev(wheels[1]);
            counters[1] = Utils.modulo((counters[1] - 1), 26);
            if (counters[1] == 25) {
                rotateWheelPrev(wheels[0]);
                counters[0] = Utils.modulo((counters[0] - 1), 26);
            }
        }
    }

    private static int pass(int c) {
        int tmp = c;
        for (int i = 0; i < 3; i++) {
            tmp = wheels[i][tmp];
        }

        tmp = Utils.modulo(tmp + 13, 26);

        for (int i = 2; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                if (wheels[i][j] == tmp) {
                    tmp = j;
                    break;
                }
            }
        }
        return tmp;
    }

    public static String encrypt(String plain, Integer[] key) {
        resetMachine();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < key[i]; j++) {
                rotateWheelNext(wheels[i]);
            }
        }
        StringBuilder out = new StringBuilder();
        String upPlain = plain.toUpperCase().replaceAll("[^A-Z]", "").trim();
        int i = 0, length = upPlain.length();
        while (i < length) {
            int c = Utils.charToInt(upPlain.charAt(i));
            c = pass(c);
            rotateNext();
            out.append(Utils.intToChar(c));
            i++;
        }
        return out.toString().trim();
    }

    public static String decrypt(String cypher, Integer[] key) {
        resetMachine();
        StringBuilder out = new StringBuilder();
        String upCypher = cypher.toUpperCase().replaceAll("[^A-Z]", "").trim();
        int length = upCypher.length();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < key[i]; j++) {
                rotateWheelNext(wheels[i]);
            }
        }
        int i = 0;
        while (i < length) {
            int c = Utils.charToInt(upCypher.charAt(i));
            c = pass(c);
            rotateNext();
            out.append(Utils.intToChar(c));
            i++;
        }
        return out.toString().trim();
    }

    public static void main(String[] args) {
        char[] alph = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        for (char c : alph) {
            StringBuilder tmp = new StringBuilder();
            System.out.println(c + " : " + encrypt(tmp.append(c).toString(), new Integer[]{11, 25, 3}));
        }
    }
}
