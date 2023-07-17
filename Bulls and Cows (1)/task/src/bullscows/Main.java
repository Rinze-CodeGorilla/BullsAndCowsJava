package bullscows;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        var s = new Scanner(System.in);
        System.out.println("Please, enter the secret code's length:");
        if (!s.hasNextInt()) {
            System.out.println("Error: not a number");
            return;
        }
        var length = s.nextInt();
        System.out.println("Input the number of possible symbols");
        if (!s.hasNextInt()) {
            System.out.println("Error: not a number");
            return;
        }
        var symbols = s.nextInt();
        if (length > symbols) {
            System.out.println("Error: code can't be more than " + symbols + " symbols");
            return;
        }
        if (symbols < 2) {
            System.out.println("Error: can't have fewer than 2 symbols");
            return;
        }
        if (symbols > 36) {
            System.out.println("Error: can't have more than 36 symbols");
            return;
        }
        var list = IntStream.range(0, symbols).boxed().collect(Collectors.toList());
        Collections.shuffle(list);
        var code = list.stream().limit(length).reduce("", (a, i) -> a + Character.forDigit(i, symbols), (a, b) -> a + b);
        System.out.println("The secret is prepared: " + "*".repeat(length) + " (0-" + (symbols < 10 ? symbols - 1 : ("9, a-" + Character.forDigit(symbols - 1, symbols))) + ")");
        System.out.println("Okay, let's start a game!");
        var turn = 0;
        var bulls = 0;
        var cows = 0;
        var codeArray = code.toCharArray();
        while (bulls < length) {
            bulls = 0;
            cows = 0;
            System.out.println("Turn " + ++turn);
            var testArray = s.next().toCharArray();
            for (int i = 0; i < length && i < testArray.length; i++) {
                if (testArray[i] == codeArray[i]) bulls++;
                else for (var c : codeArray) {
                    if (c == testArray[i]) cows++;
                }
            }
            System.out.println("Grade: " + bulls + " bulls and " + cows + " cows");
        }
        System.out.println("Congratulations! You guessed the secret code.");

    }
}
