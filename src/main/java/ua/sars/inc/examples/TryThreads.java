package ua.sars.inc.examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ihorreshetnov on 10/25/16.
 */
public class TryThreads {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(() -> System.out.println("Hi!"));
        //executorService.shutdown();
    }
}
