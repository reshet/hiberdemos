package ua.sars.inc.examples;

import java.util.Random;

/**
 * Created by ihorreshetnov on 10/27/16.
 */
public class ExceptionsPrimer {

    public static void main(String[] args) {
        int [] arr = new int [20];
        Random r = new Random();
        for (int i = 0; i < 20; i++) {
            arr[i] = r.nextInt(1000);
        }

        for (int el: arr) {
            System.out.println(el);
        }

        throw new RuntimeException("Hi");
    }
}
