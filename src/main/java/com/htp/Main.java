package com.htp;

import org.apache.commons.lang3.RandomUtils;

public class Main {
    public static void main(String[] args) {

        System.out.println(generateWelcomePhrase());
        System.out.println(generateInt());
        System.out.println(generateLong());
        System.out.println(generateDouble());
        System.out.println(generateWelcomePhrase());

    }

    private static double generateDouble() {
        return RandomUtils.nextDouble();
    }

    private static long generateLong() {
        return RandomUtils.nextLong();
    }

    private static int generateInt() {
        return RandomUtils.nextInt();
    }

    private static String generateWelcomePhrase() {
        return "My main REBASE phrase";
    }
}
