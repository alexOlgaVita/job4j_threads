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

    private boolean exit(Integer a, Integer b) {
        return a != -1 || b != -1;
    }

    private Integer indexOf(Integer a, Integer b) {
        return a != -1 ? a : b;
    }

    @Override
    protected Integer compute() {
        if (lastIndex >= firstIndex) {
            int mid = firstIndex + (lastIndex - firstIndex) / 2;
            if (elementToSearch.equals(arr[mid])) {
                return mid;
            }
            ParallelOptimalFindIndex oneFind = new ParallelOptimalFindIndex(arr, firstIndex, mid - 1, elementToSearch);
            oneFind.fork();
            ParallelOptimalFindIndex twoFind = new ParallelOptimalFindIndex(arr, mid + 1, lastIndex, elementToSearch);
            twoFind.fork();
            Integer one = (Integer) oneFind.join();
            Integer two = (Integer) twoFind.join();
            if (exit(one, two)) {
                return indexOf(one, two);
            }
        }
        return -1;
    }

    public static <T extends Comparable<T>> Integer optimalSearch(T[] array, T value) {
        Integer result = -1;
        if (array.length <= MAX_SIZE_FOR_LINEAR) {
            System.out.println("Линейный поиск");
            return Search.indexOfBySimple(array, value);
        }
        System.out.println("Бинарный поиск");
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        result = (Integer) forkJoinPool.invoke(new ParallelOptimalFindIndex(array, 0, array.length - 1, value));
        return result;
    }
}
