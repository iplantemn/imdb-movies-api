package com.iplante.imdb.movies.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Random data generator.
 *
 * @author Isabelle Plante
 * @version 1
 * @since 8/26/20
 */
public class RandomUtil {

    private RandomUtil() {
        // no instantiation
    }

    /**
     * Generate a random Date object between a two bounding years.
     *
     * @param minYear the minimum year for the date.
     * @param maxYear the maximum year for the date.
     * @return a random Date between the boundaries.
     */
    public static Date generateRandomDate(int minYear, int maxYear) {
        final var minEpoch = (int) LocalDate.of(minYear, 1, 1).toEpochDay();
        final var maxEpoch = (int) LocalDate.of(maxYear, 1, 1).toEpochDay();
        final var randomEpoch = minEpoch + new Random().nextInt(maxEpoch - minEpoch);

        return new Date(randomEpoch);
    }

    /**
     * Generate a random integer between two given numbers.
     *
     * @param min the minimum number that can be returned.
     * @param max the maximum number that can be returned.
     * @return a bounded random integer.
     */
    public static int generateRandomInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    /**
     * Get a randomly sized list of random Long values.
     *
     * @param minSize  the minimum size of the list.
     * @param maxSize  the maximum size of the list.
     * @param minValue the minimum value of each list member.
     * @param maxValue the maximum value of each list member.
     * @return a randomly sized list of random Long values.
     */
    public static List<Long> generateRandomListOfLongs(int minSize, int maxSize, int minValue, int maxValue) {
        final var listSize = generateRandomInt(minSize, maxSize);

        return IntStream
                .range(0, listSize)
                .mapToObj((i) -> (long) generateRandomInt(minValue, maxValue))
                .collect(Collectors.toList());
    }

    /**
     * Generate a random number of variable-length words stringed together.
     *
     * @param minCount  the minimum amount of words desired.
     * @param maxCount  the maximum amount of words desired.
     * @param minLength the minimum length that any of the words can have.
     * @param maxLength the maximum length that any of the words can have.
     * @return a String containing a random number of randomly generated words separated by spaces.
     */
    public static String generateRandomWords(int minCount, int maxCount, int minLength, int maxLength) {
        final var totalWords = generateRandomInt(minCount, maxCount);

        final var words = IntStream
                .range(0, totalWords)
                .mapToObj((i) -> generateRandomString(minLength, maxLength))
                .collect(Collectors.toList());

        return String.join(" ", words);
    }

    /**
     * Generate a random String of a bounded random length.
     *
     * @param minLength the minimum length that the random string can be.
     * @param maxLength the maximum length that the string can be.
     * @return the random String of bounded random length.
     */
    public static String generateRandomString(int minLength, int maxLength) {
        return RandomStringUtils.randomAlphabetic(generateRandomInt(minLength, maxLength));
    }

}
