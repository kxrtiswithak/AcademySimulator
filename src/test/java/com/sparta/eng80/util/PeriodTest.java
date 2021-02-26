package com.sparta.eng80.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PeriodTest {

    @Test
    void between() {
        BigInteger newYear = BigInteger.valueOf(LocalDate.now().plusYears(1).getYear());
        short newMonth = (short) LocalDate.now().plusMonths(1).getMonth().getValue();
        short newDay =  (short) LocalDate.now().plusDays(1).getDayOfMonth();
        Assertions.assertEquals(
                1,
                Period.between(Date.now(), Date.of(newYear, newMonth, newDay)).getYears().intValue()
        );
        Assertions.assertEquals(
                (short) 1,
                Period.between(Date.now(), Date.of(newYear, newMonth, newDay)).getMonths()
        );
        Assertions.assertEquals(
                (short) 1,
                Period.between(Date.now(), Date.of(newYear, newMonth, newDay)).getDays()
        );
    }
}