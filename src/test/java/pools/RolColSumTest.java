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
        RolColSum.Sums[] result = sum(matrix);
        assertThat(result).isEmpty();
    }

    @Test
    public void whenSumsSize1() {
        int[][] matrix = {{2}};
        RolColSum.Sums[] result = sum(matrix);
        assertThat(result[0].getRowSum()).isEqualTo(2);
        assertThat(result[0].getColSum()).isEqualTo(2);
    }

    @Test
    public void whenSumsSize2() {
        int[][] matrix = {{1, 2}, {4, 5}};
        RolColSum.Sums[] result = sum(matrix);
        assertThat(result[0].getRowSum()).isEqualTo(3);
        assertThat(result[0].getColSum()).isEqualTo(5);
        assertThat(result[1].getRowSum()).isEqualTo(9);
        assertThat(result[1].getColSum()).isEqualTo(7);
    }

    @Test
    public void whenSumsSize3() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RolColSum.Sums[] result = sum(matrix);
        assertThat(result[0].getRowSum()).isEqualTo(6);
        assertThat(result[0].getColSum()).isEqualTo(12);
        assertThat(result[1].getRowSum()).isEqualTo(15);
        assertThat(result[1].getColSum()).isEqualTo(15);
        assertThat(result[2].getRowSum()).isEqualTo(24);
        assertThat(result[2].getColSum()).isEqualTo(18);
    }

    @Test
    public void whenAsyncSumsEmpty() throws ExecutionException, InterruptedException {
        int[][] matrix = {};
        RolColSum.Sums[] result = asyncSum(matrix);
        assertThat(result).isEmpty();
    }

    @Test
    public void whenAsyncSumsSize1() throws ExecutionException, InterruptedException {
        int[][] matrix = {{2}};
        RolColSum.Sums[] result = asyncSum(matrix);
        assertThat(result[0].getRowSum()).isEqualTo(2);
        assertThat(result[0].getColSum()).isEqualTo(2);
    }

    @Test
    public void whenAsyncSumsSize2() throws ExecutionException, InterruptedException {
        int[][] matrix = {{1, 2}, {4, 5}};
        RolColSum.Sums[] result = asyncSum(matrix);
        assertThat(result[0].getRowSum()).isEqualTo(3);
        assertThat(result[0].getColSum()).isEqualTo(5);
        assertThat(result[1].getRowSum()).isEqualTo(9);
        assertThat(result[1].getColSum()).isEqualTo(7);
    }

    @Test
    public void whenAsyncSumsSize3() throws ExecutionException, InterruptedException {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RolColSum.Sums[] result = asyncSum(matrix);
        assertThat(result[0].getRowSum()).isEqualTo(6);
        assertThat(result[0].getColSum()).isEqualTo(12);
        assertThat(result[1].getRowSum()).isEqualTo(15);
        assertThat(result[1].getColSum()).isEqualTo(15);
        assertThat(result[2].getRowSum()).isEqualTo(24);
        assertThat(result[2].getColSum()).isEqualTo(18);
    }
}