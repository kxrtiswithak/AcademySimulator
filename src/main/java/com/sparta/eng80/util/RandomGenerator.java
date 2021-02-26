package com.sparta.eng80.util;

import java.util.Random;

public class RandomGenerator {
    private Random random;

    public RandomGenerator() {
        random = new Random();
    }
    public RandomGenerator(long seed) {
        random = new Random(seed);
    }

    public int inRange(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Given min, " + min + ", is greater than the given max, " + max);
        }
        return random.nextInt((int) ((long) (max) - (long) (min) + 1)) + min;
    }

}
