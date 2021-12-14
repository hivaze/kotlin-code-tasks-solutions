package javatest;

import java.util.Scanner;

public class Something {

    public static void main(String[] args) {
        System.out.println("AAAAA");
        var scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            System.out.println("Typed: " + line);
        }
    }

}
