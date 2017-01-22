package ua.sars.inc.ofm;


import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyStringArray implements Serializable {

    private final List<Integer> array;

    public MyStringArray(List<Integer> array) {
        this.array = array;
    }

    @Override
    public String toString() {
        return array.stream()
            .map(String::valueOf)
            .collect(Collectors.joining(" "));
    }

    public static MyStringArray fromString(String str) {
        List<Integer> list = Stream.of(str.split(" ")).map(Integer::valueOf).collect(Collectors.toList());
        return new MyStringArray(list);
    }

    public List<Integer> getArray() {
        return array;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MyStringArray that = (MyStringArray) o;

        return array.equals(that.array);

    }

    @Override
    public int hashCode() {
        return array.hashCode();
    }
}
