package ua.sars.inc.examples;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by ihorreshetnov on 10/27/16.
 */
public class MapsPrimer {

    public static void main(String[] args) {
       new HashMap<String, String>() {
         void findAndReplace(String key, String newValue) {
            if (this.containsKey(key)) {
                this.put(key, newValue);
            }
         }
       };
    }
}
