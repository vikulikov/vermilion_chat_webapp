package model;

import java.util.List;

public class Useful {
    public static <T> void printListElements(List<T> list) {
        for (T i: list) {
            System.out.println(i);
        }
    }
}
