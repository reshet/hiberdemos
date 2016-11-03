package ua.sars.inc.examples;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by ihorreshetnov on 10/25/16.
 */
public class SumSearch {



    public static void main(String[] args) {
        Random r = new Random();
        List<Integer> integers = Stream.generate(() -> r.nextInt(100)).limit(100).collect(toList());

        integers.sort(Integer::compare);

        System.out.println(integers);

        int sum = 50;
        integers.forEach(number -> {
            int remainder = sum - number;
            if (remainder > 0) {

                if (Collections.binarySearch(integers, remainder) > -1) {
                    System.out.println("Pair found: " + number + " + " + remainder + " = " + sum);
                }
            }
        });
    }
}
