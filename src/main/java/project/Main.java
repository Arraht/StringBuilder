package project;

import project.service.Process;

public class Main {
    private static final Process process = new Process();

    public static void main(String[] args) {
        process.process();
    }
}