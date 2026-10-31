package battleship.cpu;

import battleship.model.Board;
import battleship.model.ShotResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/** Easy mode: shoots at random and doesn't chase the ships it hits. */
public class RandomStrategy implements CpuStrategy {

    private final List<int[]> cells = new ArrayList<>();

    public RandomStrategy(Random rng) {
        for (int r = 0; r < Board.SIZE; r++)
            for (int c = 0; c < Board.SIZE; c++)
                cells.add(new int[]{r, c});
        // shuffling once means it never repeats a cell
        Collections.shuffle(cells, rng);
    }

    @Override
    public int[] chooseTarget() {
        return cells.isEmpty() ? null : cells.remove(cells.size() - 1);
    }

    @Override
    public void registerResult(int row, int col, ShotResult result) {
        // doesn't learn anything, that's the point
    }
}
