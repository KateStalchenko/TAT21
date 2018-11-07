package main;

import java.util.Random;

public class Solution {
    public static void main(String[] args) {
        System.out.println("Hello, world!");
        System.out.println("And hi again");
        Random rnd = new Random();
        int number = rnd.nextInt(46) + 5;
        for (int i = 0; i < number; i++) {
            System.out.print("!");
        }
    }
}
