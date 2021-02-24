package com.sparta.eng80.controller;

import com.sparta.eng80.util.RandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class RandomGeneratorTest {
    final int MIN = 0;
    final int MAX = 20;
    final long seed = 735654231;

    @Test
    public void generateRandomNumberCheck() {
        RandomGenerator randomGenerator = new RandomGenerator(seed);
        List<Integer> listOfIntegers= new ArrayList<>();
        for (int count = 0; count < 10; count++) {
            int randomVal = randomGenerator.inRange(MIN, MAX);
        }
        for (int number : listOfIntegers) {
            Assertions.assertTrue(number >= MIN && number <= MAX);
        }
    }

    @Test
    public void generateRandomNumberWithMinGreaterThanMax() {
        RandomGenerator randomGenerator = new RandomGenerator(seed);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            randomGenerator.inRange(MAX, MIN);});
    }

    @Test
    public void generateRandomNumberWithSameMinMax() {
        RandomGenerator randomGenerator = new RandomGenerator(seed);
        randomGenerator.inRange(MAX, MAX);
    }
}
