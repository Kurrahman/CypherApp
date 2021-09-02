package Engine;

import Utils.Utils;

import java.util.ArrayList;

public class PlayFairCypher {
    static ArrayList<ArrayList<Character>> square = new ArrayList<>();

    public static ArrayList<ArrayList<Character>> getSquare(){
        return square;
    }
    public static void generateSquare(String key){
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
            if ((tmp >= 0) && (tmp != 9)){
                sequence.append(Utils.intToChar(tmp));
            }
        }

        String temp = sequence.toString();

        for (int i = 0; i < 5; i++) {
            ArrayList<Character> tmp = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                tmp.add(temp.charAt(i*5+j));
            }
            square.set(i,tmp);
        }
    }

    public static void main(String[] args) {
        PlayFairCypher.generateSquare("random");
    }
}
