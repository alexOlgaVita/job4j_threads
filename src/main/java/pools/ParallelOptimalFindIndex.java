package pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelOptimalFindIndex<T extends Comparable<T>> extends RecursiveTask<Integer> {
    private final T[] arr;
    private final int firstIndex;
    private final int lastIndex;
    private final T elementToSearch;
    private static final int MAX_SIZE_FOR_LINEAR = 10;

    public ParallelOptimalFindIndex(T[] arr, int firstIndex, int lastIndex, T elementToSearch) {
        this.arr = arr;
        this.firstIndex = firstIndex;
        this.lastIndex = lastIndex;
        this.elementToSearch = elementToSearch;
    }

    @Override
    protected Integer compute() {
        if (lastIndex >= firstIndex) {
            int mid = firstIndex + (lastIndex - firstIndex) / 2;
            if (elementToSearch.equals(arr[mid])) {
                return mid;
            }
            if (arr[mid] == elementToSearch) {
                return mid;
            }
            ParallelOptimalFindIndex oneFind = new ParallelOptimalFindIndex(arr, firstIndex, mid - 1, elementToSearch);
            oneFind.fork();
            ParallelOptimalFindIndex twoFind = new ParallelOptimalFindIndex(arr, mid + 1, lastIndex, elementToSearch);
            twoFind.fork();
            Integer one = (Integer) oneFind.join();
            Integer two = (Integer) twoFind.join();
            if (one != -1) {
                return one;
            } else if (two != -1) {
                return two;
            }
        }
        return -1;
    }

    private static <T extends Comparable<T>> Integer linearSearch(T[] arr, int firstIndex, int lastIndex, T value) {
        if (lastIndex < firstIndex) {
            return -1;
        }
        if (value.equals(arr[firstIndex])) {
            return firstIndex;
        }
        return linearSearch(arr, firstIndex + 1, lastIndex, value);
    }

    public static <T extends Comparable<T>> Integer optimalSearch(T[] array, T value) {
        Integer result = -1;
        if (array.length <= MAX_SIZE_FOR_LINEAR) {
            System.out.println("Линейный поиск");
            result = linearSearch(array, 0, array.length - 1, value);
        } else {
            System.out.println("Бинарный поиск");
            ForkJoinPool forkJoinPool = new ForkJoinPool();
            result = (Integer) forkJoinPool.invoke(new ParallelOptimalFindIndex(array, 0, array.length - 1, value));
        }
        return result;
    }
}
