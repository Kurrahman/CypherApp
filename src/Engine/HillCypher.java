package Engine;

import Utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;

public class HillCypher {
    public static int[][] inverse = {
            {0,0,0},
            {0,0,0},
            {0,0,0}
    };

    public static void generateInverse2(int[][] key){
        int a = key[0][0];
        int b = key[0][1];
        int c = key[1][0];
        int d = key[1][1];

        int det = Utils.modulo(a*d - b*c, 26);
        det = Utils.invModulo(det, 26);

        inverse[0][0] = (Utils.modulo(Utils.modulo(d,26)*det,26));
        inverse[0][1] = (Utils.modulo(Utils.modulo(-b,26)*det,26));
        inverse[1][0] = (Utils.modulo(Utils.modulo(-c,26)*det,26));
        inverse[1][1] = (Utils.modulo(Utils.modulo(a,26)*det,26));
    }

    public static void generateInverse3(int[][] key){
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
                {a,d,g},
                {b,e,h},
                {c,f,i}
        };
        int det = Utils.modulo(a*key[0][0] + b*key[0][1] + c*key[0][2],26);
        det = Utils.invModulo(det,26);

        for (int k = 0; k < 3; k++) {
            for (int j = 0; j < 3; j++){
                inverse[k][j] = Utils.modulo(Utils.modulo(trans[k][j],26) * det,26);
            }
        }
    }

    public static void main(String[] args) {
        int[][] matr = {
                {4,9,15},
                {15,17,6},
                {24,0,17}
        };

        HillCypher.generateInverse3(matr);
        System.out.println(Arrays.deepToString(HillCypher.inverse));
        System.out.println(Arrays.deepToString(Utils.matrixMultiplication(matr, HillCypher.inverse)));
    }
}
