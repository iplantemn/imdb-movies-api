package com.iplante.imdb.movies.util;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link RandomUtil}.
 *
 * @author Isabelle Plante
 * @version 1
 * @since 8/27/20
 */
public class RandomUtilTest {

    @Test
    void generateRandomDate() {
        for (int i = 0; i < 100; i++) {
            final var result = RandomUtil.generateRandomDate(1990, 2010);
            final var year = getYear(result);

            assertThat(year).isGreaterThanOrEqualTo(1990);
            assertThat(year).isLessThanOrEqualTo(2010);
        }
    }

    @Test
    void generateRandomInt() {
        for (int i = 0; i < 100; i++) {
            final var result = RandomUtil.generateRandomInt(1, 10);
            assertThat(result).isGreaterThanOrEqualTo(1);
            assertThat(result).isLessThanOrEqualTo(10);
        }
    }

    @Test
    void generateRandomListOfLongs() {
        for (int i = 0; i < 100; i++) {
            final var result = RandomUtil.generateRandomListOfLongs(10, 20, 100, 500);

            assertThat(result).hasSizeGreaterThanOrEqualTo(10);
            assertThat(result).hasSizeLessThanOrEqualTo(20);

            for (Long longValue: result) {
                assertThat(longValue).isGreaterThanOrEqualTo(100);
                assertThat(longValue).isLessThanOrEqualTo(500);
            }
        }
    }

    @Test
    void generateRandomWords() {
        for (int i = 0; i < 100; i++) {
            final var result = RandomUtil.generateRandomWords(10, 20, 5, 10);
            var words = result.split(" ");

            assertThat(words).hasSizeGreaterThanOrEqualTo(10);
            assertThat(words).hasSizeLessThanOrEqualTo(20);
            for (String word: words) {
                assertThat(word).hasSizeGreaterThanOrEqualTo(5);
                assertThat(word).hasSizeLessThanOrEqualTo(10);
            }
        }
    }

    @Test
    void generateRandomString() {
        for (int i = 0; i < 100; i++) {
            final var result = RandomUtil.generateRandomString(10, 20);
            assertThat(result).hasSizeGreaterThanOrEqualTo(10);
            assertThat(result).hasSizeLessThanOrEqualTo(20);
        }
    }

    private int getYear(Date date) {
        final var calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }
}
