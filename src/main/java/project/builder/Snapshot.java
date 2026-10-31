package project.builder;

import lombok.Getter;

import java.util.Arrays;

@Getter
public class Snapshot {
    private final char[] valueCopy;
    private final int count;

    public Snapshot(char[] value, int count) {
        this.valueCopy = Arrays.copyOf(value, count);
        this.count = count;
    }
}