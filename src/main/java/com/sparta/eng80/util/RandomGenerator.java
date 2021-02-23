package com.sparta.eng80.util;

import java.util.Random;

public class RandomGenerator {
    private Random random;

    public RandomGenerator(long seed) {
        random = new Random(seed);
    }

    public int inRange(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Given min, " + min + ", is greater than the given max, " + max);
        }
        return (int) ((long) min + Math.random() * ((long) max - min + 1));
    }
}
