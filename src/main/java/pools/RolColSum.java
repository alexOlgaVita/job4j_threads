package pools;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {

    public static Sums[] sum(int[][] matrix) {
        Sums[] result = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            result[i] = new Sums();
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                result[i].setRowSum(result[i].getRowSum() + matrix[i][j]);
                result[j].setColSum(result[j].getColSum() + matrix[i][j]);
            }
        }
        return result;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] result = new Sums[matrix.length];
        Map<Integer, CompletableFuture<Sums>> futures = new HashMap<>();
        for (int k = 0; k < matrix.length / 2; k++) {
            futures.put(k, getTask(matrix, k));
            futures.put(matrix.length / 2 + k, getTask(matrix, matrix.length / 2 + k));
        }
        if (matrix.length % 2 == 1) {
            futures.put(matrix.length - 1, getTask(matrix, matrix.length - 1));
        }
        for (Integer key : futures.keySet()) {
            result[key] = futures.get(key).get();
        }
        return result;
    }

    public static CompletableFuture<Sums> getTask(int[][] data, int index) {
        return CompletableFuture.supplyAsync(() -> {
            Sums res = new Sums();
            for (int i = 0; i < data.length; i++) {
                res.setColSum(res.getColSum() + data[i][index]);
                res.setRowSum(res.getRowSum() + data[index][i]);
            }
            return res;
        });
    }
}
