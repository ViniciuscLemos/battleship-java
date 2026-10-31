package battleship;

import battleship.cpu.CpuStrategy;
import battleship.cpu.HuntTargetStrategy;
import battleship.cpu.RandomStrategy;

import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Seed (empty for random): ");
        String seedStr = sc.nextLine().trim();
        Random rng;
        if (seedStr.isEmpty()) {
            rng = new Random();
        } else {
            long seed;
            try { seed = Long.parseLong(seedStr); }
            catch (NumberFormatException e) { seed = seedStr.hashCode(); }
            rng = new Random(seed);
        }

        int wins = 0, losses = 0;
        try {
            System.out.print("Difficulty: 1) Easy  2) Normal (default 2): ");
            boolean easy = sc.nextLine().trim().equals("1");
            do {
                CpuStrategy cpu = easy ? new RandomStrategy(rng) : new HuntTargetStrategy(rng);
                if (new Game(sc, rng, cpu).run()) wins++;
                else losses++;
                System.out.printf("%nScore: you %d x %d CPU%n", wins, losses);
                System.out.print("Play again? (y/N): ");
            } while (yes(sc.nextLine()));
        } catch (NoSuchElementException e) {
            // Ctrl+D / Ctrl+Z
            System.out.println("\nInput closed. See you next time!");
        } finally {
            sc.close();
        }
    }

    private static boolean yes(String answer) {
        String s = answer.trim().toLowerCase(Locale.ROOT);
        return s.equals("y") || s.equals("yes");
    }
}
