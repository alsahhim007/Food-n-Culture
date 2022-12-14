package com.dalhousie.server.utilities;

import java.util.Random;

public class RNG {

    public static int getRandomNumber(int length) {
        Random random = new Random();
        int m = (int) Math.pow(10, length - 1);
        return m + random.nextInt(9 * m);
    }

}
