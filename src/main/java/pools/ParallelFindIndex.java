package pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelFindIndex<T extends Comparable<T>> extends RecursiveTask<Integer> {
    private final T[] arr;
    private final int firstIndex;
    private final int lastIndex;
    private final T elementToSearch;
    private static final int MAX_SIZE_FOR_LINEAR = 10;

    public ParallelFindIndex(T[] arr, int firstIndex, int lastIndex, T elementToSearch) {
        this.arr = arr;
        this.firstIndex = firstIndex;
        this.lastIndex = lastIndex;
        this.elementToSearch = elementToSearch;
    }

    private <T extends Comparable<T>> Integer indexOf() {
        int i = firstIndex;
        while (i <= lastIndex && !elementToSearch.equals(arr[i])) {
            i++;
        }
        return i == lastIndex + 1 ? -1 : i;
    }

    @Override
    protected Integer compute() {
        if (lastIndex - firstIndex <= MAX_SIZE_FOR_LINEAR) {
            return indexOf();
        }
            int mid = (lastIndex - firstIndex) / 2;
            ParallelFindIndex oneFind = new ParallelFindIndex(arr, firstIndex, mid, elementToSearch);
            ParallelFindIndex twoFind = new ParallelFindIndex(arr, mid + 1, lastIndex, elementToSearch);
            oneFind.fork();
            twoFind.fork();
            return Math.max((Integer) oneFind.join(), (Integer) twoFind.join());
    }

    public static <T extends Comparable<T>> Integer optimalSearch(T[] array, T value) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return (Integer) forkJoinPool.invoke(new ParallelFindIndex(array, 0, array.length - 1, value));
    }
}
