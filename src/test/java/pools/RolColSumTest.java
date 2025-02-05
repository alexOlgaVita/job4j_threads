package pools;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;
import static pools.RolColSum.asyncSum;
import static pools.RolColSum.sum;

class RolColSumTest {

    @Test
    public void whenSumsEmpty() {
        int[][] matrix = {};
        assertThat(sum(matrix)).isEmpty();
    }

    @Test
    public void whenSumsSize1() {
        int[][] matrix = {{2}};
        Sums sums = new Sums(2, 2);
        assertThat(sum(matrix)).containsExactly(sums);
    }

    @Test
    public void whenSumsSize2() {
        int[][] matrix = {{1, 2}, {4, 5}};
        assertThat(sum(matrix)).containsExactly(new Sums(3, 5),
                new Sums(9, 7));
    }

    @Test
    public void whenSumsSize3() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        assertThat(sum(matrix)).containsExactly(new Sums(6, 12),
                new Sums(15, 15),
                new Sums(24, 18));
    }

    @Test
    public void whenAsyncSumsEmpty() throws ExecutionException, InterruptedException {
        int[][] matrix = {};
        assertThat(asyncSum(matrix)).isEmpty();
    }

    @Test
    public void whenAsyncSumsSize1() throws ExecutionException, InterruptedException {
        int[][] matrix = {{2}};
        assertThat(asyncSum(matrix)).containsExactly(new Sums(2, 2));
    }

    @Test
    public void whenAsyncSumsSize2() throws ExecutionException, InterruptedException {
        int[][] matrix = {{1, 2}, {4, 5}};
        assertThat(asyncSum(matrix)).containsExactly(new Sums(3, 5),
                new Sums(9, 7));
    }

    @Test
    public void whenAsyncSumsSize3() throws ExecutionException, InterruptedException {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        assertThat(asyncSum(matrix)).containsExactly(new Sums(6, 12),
                new Sums(15, 15),
                new Sums(24, 18));
    }
}