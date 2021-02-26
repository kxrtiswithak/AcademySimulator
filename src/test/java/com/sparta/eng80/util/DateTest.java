package com.sparta.eng80.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    Date date;

    @BeforeEach
    public void first() {
        date = Date.now();
    }

    @Test
    void plusYears() {
        Assertions.assertEquals(
                date.plusYears(BigInteger.valueOf(1)).getYear().intValue(),
                LocalDate.now().plusYears(1).getYear()
        );
    }

    @Test
    void plusMonths() {
        Assertions.assertEquals(
                (date.plusMonths(BigInteger.valueOf(1)).getMonth()),
                LocalDate.now().plusMonths(1).getMonth().getValue()
        );
    }

    @Test
    void plusDays() {
        Assertions.assertEquals(
                (date.plusDays(BigInteger.valueOf(1)).getDay()),
                LocalDate.now().plusDays(1).getDayOfMonth()
        );
    }

    @Test
    void isBefore() {
        Assertions.assertTrue(
                date.isBefore(date.plusYears(BigInteger.valueOf(1)))
        );
    }

    @Test
    void isAfter() {
        Assertions.assertTrue(
                date.plusYears(BigInteger.valueOf(1)).isAfter(date)
        );
    }

    @Test
    void isEqual() {
        Assertions.assertTrue(
                date.isEqual(date)
        );
    }

    @Test
    void getYear() {
        Assertions.assertEquals(
                date.getYear().intValue(),
                LocalDate.now().getYear()
        );
    }

    @Test
    void getMonth() {
        Assertions.assertEquals(
                date.getMonth(),
                LocalDate.now().getMonth().getValue()
        );
    }

    @Test
    void getDay() {
        Assertions.assertEquals(
                date.getDay(),
                LocalDate.now().getDayOfMonth()
        );
    }
}