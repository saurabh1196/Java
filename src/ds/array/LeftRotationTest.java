package ds.array;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class LeftRotationTest {
    Solver solver = new Solver();
    ;

    @Test
    public void test() {
        assertArrayEquals(new int[]{4, 5, 6, 7, 1, 2, 3}, solver.solve1(new int[]{1, 2, 3, 4, 5, 6, 7}, 3));
        assertArrayEquals(new int[]{4, 5, 6, 7, 1, 2, 3}, solver.solve2(new int[]{1, 2, 3, 4, 5, 6, 7}, 3));
        assertArrayEquals(new int[]{4, 5, 6, 7, 1, 2, 3}, solver.solve3(new int[]{1, 2, 3, 4, 5, 6, 7}, 3));

    }

    @Test
    public void testRandom() {
        final Random random = new Random();
        final int testCases = random.nextInt(10);
        for (int i = 0; i < testCases; i++) {
            final int n = random.nextInt(50);
            int[] input = new int[n];
            for (int j = 0; j < n; j++) {
                input[i] = random.nextInt(99);
            }
            final int d = random.nextInt(input.length);
            assertArrayEquals(solver.solve1(input, d), solver.solve2(input, d));
            assertArrayEquals(solver.solve1(input, d), solver.solve3(input, d));
        }
    }
}
