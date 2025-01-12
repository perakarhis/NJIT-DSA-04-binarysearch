package oy.tol.tra;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

@DisplayName("Compare linear and binary search speeds")
public class BinarySearchTests {

    private static final int NUMBERS_TO_SEARCH = 10;

    @Test
    //@Timeout(value = 120, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    @DisplayName("Tests Integer search implementations (linear & binary)")
    void findFromIntArrayTests() {
        try {
            System.out.println("-- Starting the test with linear search --");
            Integer[] array = ArrayUtils.generateIntegerArray(50000);
            ThreadLocalRandom tlr = ThreadLocalRandom.current();

            int counter = NUMBERS_TO_SEARCH;
            long start = 0;
            long linearDuration = 0;
            while (counter-- >= 0) {
                int toFind = tlr.nextInt(0, array.length);
                start = System.nanoTime();
                int linearIndex = SearchArray.slowLinearSearch(toFind, array, 0, array.length - 1);
                assertTrue(linearIndex >= 0, () -> "Binary search could not find the element to search");
                linearDuration += System.nanoTime() - start;
                System.out.println("Index of " + toFind + " is: " + linearIndex);
            }
            linearDuration /= NUMBERS_TO_SEARCH;

            System.out.println("Sorting the array...");
            start = System.nanoTime();
            // You must have implemented this as instructed in Exercise 01-arrays!
            Algorithms.sort(array);
            System.out.println("Sorting the array took " + (System.nanoTime() - start) + " ns");

            System.out.println("-- Starting the test with binary search --");
            counter = NUMBERS_TO_SEARCH;
            start = 0;
            long binaryDuration = 0;
            while (counter-- >= 0) {
                int toFind = tlr.nextInt(1, array.length);
                start = System.nanoTime();
                int binaryIndex = Algorithms.binarySearch(toFind, array, 0, array.length - 1);
                assertTrue(binaryIndex >= 0, () -> "Binary search could not find the element to search");
                binaryDuration += System.nanoTime() - start;
                System.out.println("Index of " + toFind + " is: " + binaryIndex);
                int libraryIndex = Arrays.binarySearch(array, toFind, Comparator.naturalOrder());
                assertEquals(libraryIndex, binaryIndex, () -> "Index is different from real index");
            }
            binaryDuration /= NUMBERS_TO_SEARCH;

            System.out.println("-- Starting the test with linear search using fast sort--");
            array = ArrayUtils.generateIntegerArray(50000);
            tlr = ThreadLocalRandom.current();

            counter = NUMBERS_TO_SEARCH;
            start = 0;
            long linearDurationFastSort = 0;
            while (counter-- >= 0) {
                int toFind = tlr.nextInt(0, array.length);
                start = System.nanoTime();
                int linearIndex = SearchArray.slowLinearSearch(toFind, array, 0, array.length - 1);
                assertTrue(linearIndex >= 0, () -> "Binary search could not find the element to search");
                linearDurationFastSort += System.nanoTime() - start;
                System.out.println("Index of " + toFind + " is: " + linearIndex);
            }
            linearDurationFastSort /= NUMBERS_TO_SEARCH;

            System.out.println("Sorting the array...");
            start = System.nanoTime();
            //You must implement this in this task!
            Algorithms.fastSort(array);
            System.out.println("Sorting the array took " + (System.nanoTime() - start) + " ns");

            System.out.println("-- Starting the test with binary search --");
            counter = NUMBERS_TO_SEARCH;
            start = 0;
            long binaryDurationFastSort = 0;
            while (counter-- >= 0) {
                int toFind = tlr.nextInt(1, array.length);
                start = System.nanoTime();
                int binaryIndex = Algorithms.binarySearch(toFind, array, 0, array.length - 1);
                assertTrue(binaryIndex >= 0, () -> "Binary search could not find the element to search");
                binaryDurationFastSort += System.nanoTime() - start;
                System.out.println("Index of " + toFind + " is: " + binaryIndex);
                int libraryIndex = Arrays.binarySearch(array, toFind, Comparator.naturalOrder());
                assertEquals(libraryIndex, binaryIndex, () -> "Index is different from real index");
            }
            binaryDurationFastSort /= NUMBERS_TO_SEARCH;

            System.out.println("-------------------------------------------------------");
            System.out.format("Average linear search duration | Integer: %19d ns%n", linearDuration);
            System.out.format("Average binary search duration | Integer: %19d ns%n", binaryDuration);
            assertTrue(binaryDuration <= linearDuration, () -> "Binary search should be much faster in most cases.");
            System.out.format("Average linear search duration fastSort | Integer: %10d ns%n", linearDurationFastSort);
            System.out.format("Average binary search duration fastSort | Integer: %10d ns%n", binaryDurationFastSort);
            assertTrue(binaryDurationFastSort <= linearDurationFastSort, () -> "Binary search should be much faster in most cases.");
        } catch (Exception e) {
            fail("Something went wrong in the tests: " + e.getMessage());
        }
    }

    @Test
    //@Timeout(value = 120, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    @DisplayName("Tests String search implementations (linear & binary)")
    void findFromStringArrayTests() {
        try {
            System.out.println("-- Starting the test with linear search --");
            String[] array = ArrayUtils.generateStringArray(50000);
            ThreadLocalRandom tlr = ThreadLocalRandom.current();

            int counter = NUMBERS_TO_SEARCH;
            long start = 0;
            long linearDuration = 0;
            while (counter-- >= 0) {
                int toFind = tlr.nextInt(0, array.length);
                start = System.nanoTime();
                int linearIndex = SearchArray.slowLinearSearch(array[toFind], array, 0, array.length - 1);
                assertTrue(linearIndex >= 0, () -> "Binary search could not find the element to search");
                linearDuration += System.nanoTime() - start;
                System.out.println("Index of " + array[toFind] + " is: " + linearIndex);
            }
            linearDuration /= NUMBERS_TO_SEARCH;

            System.out.println("Sorting the array...");
            start = System.nanoTime();
            // You must have implemented this as instructed in Exercise 01-arrays!
            Algorithms.sort(array);
            System.out.println("Sorting the array took " + (System.nanoTime() - start) + " ns");

            System.out.println("-- Starting the test with binary search --");
            counter = NUMBERS_TO_SEARCH;
            start = 0;
            long binaryDuration = 0;
            while (counter-- >= 0) {
                int toFind = tlr.nextInt(0, array.length);
                start = System.nanoTime();
                int binaryIndex = Algorithms.binarySearch(array[toFind], array, 0, array.length - 1);
                assertTrue(binaryIndex >= 0, () -> "Binary search could not find the element to search");
                binaryDuration += System.nanoTime() - start;
                System.out.println("Index of " + array[toFind] + " is: " + binaryIndex);
                int libraryIndex = Arrays.binarySearch(array, array[toFind], Comparator.naturalOrder());
                assertEquals(libraryIndex, binaryIndex, () -> "Index is different from real index");
            }
            binaryDuration /= NUMBERS_TO_SEARCH;

            System.out.println("-- Starting the test with linear search using fast sort --");
            array = ArrayUtils.generateStringArray(50000);
            tlr = ThreadLocalRandom.current();

            counter = NUMBERS_TO_SEARCH;
            start = 0;
            long linearDurationFastSort = 0;
            while (counter-- >= 0) {
                int toFind = tlr.nextInt(0, array.length);
                start = System.nanoTime();
                int linearIndex = SearchArray.slowLinearSearch(array[toFind], array, 0, array.length - 1);
                assertTrue(linearIndex >= 0, () -> "Binary search could not find the element to search");
                linearDurationFastSort += System.nanoTime() - start;
                System.out.println("Index of " + array[toFind] + " is: " + linearIndex);
            }
            linearDurationFastSort /= NUMBERS_TO_SEARCH;

            System.out.println("Sorting the array using fast sort...");
            start = System.nanoTime();
            // You must implement this in this task!
            Algorithms.fastSort(array);
            System.out.println("Sorting the array with fast sort took " + (System.nanoTime() - start) + " ns");

            System.out.println("-- Starting the test with binary search --");
            counter = NUMBERS_TO_SEARCH;
            start = 0;
            long binaryDurationFastSort = 0;
            while (counter-- >= 0) {
                int toFind = tlr.nextInt(0, array.length);
                start = System.nanoTime();
                int binaryIndex = Algorithms.binarySearch(array[toFind], array, 0, array.length - 1);
                assertTrue(binaryIndex >= 0, () -> "Binary search could not find the element to search");
                binaryDurationFastSort += System.nanoTime() - start;
                System.out.println("Index of " + array[toFind] + " is: " + binaryIndex);
                int libraryIndex = Arrays.binarySearch(array, array[toFind], Comparator.naturalOrder());
                assertEquals(libraryIndex, binaryIndex, () -> "Index is different from real index");
            }
            binaryDurationFastSort /= NUMBERS_TO_SEARCH;
            System.out.println("-------------------------------------------------------");
            System.out.format("Average linear search duration | String: %20d ns%n", linearDuration);
            System.out.format("Average binary search duration | String: %20d ns%n", binaryDuration);
            assertTrue(binaryDuration <= linearDuration, () -> "Binary search should be much faster in most cases.");
            System.out.format("Average linear search duration fast sort | String: %10d ns%n", linearDurationFastSort);
            System.out.format("Average binary search duration fast sort | String: %10d ns%n", binaryDurationFastSort);
            assertTrue(binaryDuration <= linearDuration, () -> "Binary search should be much faster in most cases.");
        } catch (Exception e) {
            fail("Something went wrong in the tests: " + e.getMessage());
        }
    }
}
