package com.mycompany.app;

//import java.util.*;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * Hello world!
 */
public class MapFilterReduceApp {

    public static void main(String[] args) {
        
        // An "Stream" is a sequence of elements supporting sequential and parallel processing operations.
        // It generates the numbers is a lazy fashion (on demand) and does not store the numbers in memory.
        // The rangeClosed() method returns all the numbers in one range including the last number, i.e. 1 to 10
        IntStream intStream = IntStream.rangeClosed(1, 10);

        // This version returns only 1 to 9.
        //IntStream intStream2 = IntStream.range(1, 10);

        // The map function is used to iterate over each element and perform some operation on it.
        // Use map to square each number
        IntStream squaredStream = intStream.map(n -> n * n);

        // The filter function is used to remove some elements based on a condition which returns a boolean value.
        // Use filter to keep only even squares
        IntStream evenSquaresStream = squaredStream.filter(n -> n % 2 == 0);

        // The .reduce method looks at pairs of elements in the stream and combines them into a single result.
        // It starts at elements 0 then combines it with 1, then combines that result with 2, and so on.
        // Use reduce to sum the remaining numbers
        OptionalInt sumOfEvenSquares = evenSquaresStream.reduce((a, b) -> a + b);

        // Print the result
        sumOfEvenSquares.ifPresent(System.out::println); // Output: 220
    }

}
