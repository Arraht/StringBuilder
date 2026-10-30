package project.builder;

import lombok.Getter;

import java.util.Arrays;
import java.util.Stack;

public class CustomStringBuilder {
    @Getter
    private char[] value;
    private int count;
    private final Stack<Snapshot> history = new Stack<>();

    public CustomStringBuilder() {
        this.value = new char[16];
        this.count = 0;
    }

    public void append(String text) {
        if (text == null || text.isEmpty()) {
            return;
        }
        history.push(new Snapshot(this.value, this.count));
        extendsArray(text);
        addToArray(text);
    }

    public void undo() {
        if (history.isEmpty()) {
            System.out.println("История изменений пуста. Отменять нечего!");
            return;
        }
        Snapshot lastSnapshot = history.pop();
        this.count = lastSnapshot.getCount();

        this.value = Arrays.copyOf(lastSnapshot.getValueCopy(), Math.max(16, lastSnapshot.getCount()));
    }


    public String getStringValue() {
        return new String(this.value, 0, this.count);
    }

    public String[] getHistoryAsArray() {
        return history
                .stream()
                .map(snapshot -> new String(
                        snapshot.getValueCopy(),
                        0,
                        snapshot.getCount()))
                .filter(str -> !str.isEmpty())
                .toArray(String[]::new);
    }


    private void addToArray(String text) {

        for (int i = 0; i < text.length(); i++) {
            this.value[this.count + i] = text.charAt(i);
        }

        this.count = this.count + text.length();
    }

    private void extendsArray(String text) {
        int newCount = this.count + text.length();

        if (newCount > this.value.length) {
            int newCapacity = this.value.length * 2;

            if (newCapacity < newCount) {
                newCapacity = newCount;
            }

            this.value = Arrays.copyOf(this.value, newCapacity);
        }
    }
}