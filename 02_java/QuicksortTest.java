//package de.vogella.algorithms.sort.quicksort;
// http://www.vogella.com/tutorials/JavaAlgorithmsQuicksort/article.html


import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class QuicksortTest {

        private int[] numbers;
        private final static int SIZE = 10000000;
        private final static int MAX =  10000000;

        @Before
        public void setUp() throws Exception {
                numbers = new int[SIZE];
                Random generator = new Random();
                for (int i = 0; i < numbers.length; i++) {
                        numbers[i] = generator.nextInt(MAX);
                }
        }

        @Test
        public void testNull() {
                Quicksort sorter = new Quicksort();
                sorter.sort(null);
        }

        @Test
        public void testQuickSort() {
                long startTime = System.currentTimeMillis();
                Quicksort sorter = new Quicksort();
                sorter.sort(numbers);
                long stopTime = System.currentTimeMillis();
                long elapsedTime = stopTime - startTime;
                System.out.println("Java Quicksort " + elapsedTime);
                if (!validate(numbers)) {
                        fail("Should not happen");
                }
                assertTrue(true);
        }

        @Test
        public void testStandardSort() {
                long startTime = System.currentTimeMillis();
                Arrays.sort(numbers);
                long stopTime = System.currentTimeMillis();
                long elapsedTime = stopTime - startTime;
                System.out.println("Standard Java sort " + elapsedTime);
                if (!validate(numbers)) {
                        fail("Should not happen");
                }
                assertTrue(true);
        }

        private boolean validate(int[] numbers) {
                for (int i = 0; i < numbers.length - 1; i++) {
                        if (numbers[i] > numbers[i + 1]) {
                                return false;
                        }
                }
                return true;
        }

        private void printResult(int[] numbers) {
                for (int i = 0; i < numbers.length; i++) {
                        System.out.print(numbers[i]);
                }
                System.out.println();
        }
}