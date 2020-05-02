package com.htp;

import org.apache.commons.lang3.RandomUtils;

public class Main {
    public static void main(String[] args) {

        System.out.println(generateWelcomePhrase());
        System.out.println(generateInt());
        System.out.println(generateFloat());
    }

    private static float generateFloat() {
        return RandomUtils.nextFloat();
    }

    private static int generateInt() {
        return RandomUtils.nextInt();
    }

    private static String generateWelcomePhrase() {
        return "My main";
    }
}
