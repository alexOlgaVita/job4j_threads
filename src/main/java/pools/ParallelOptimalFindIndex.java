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

    private <T extends Comparable<T>> Integer indexOfBySimple(T[] arr, int first, int last, T value) {
        int i = first;
        while (i <= last && !value.equals(arr[i])) {
            i++;
        }
        return i == last + 1 ? -1 : i;
    }

    @Override
    protected Integer compute() {
        if (lastIndex - firstIndex <= MAX_SIZE_FOR_LINEAR) {
            return indexOfBySimple(arr, firstIndex, lastIndex, elementToSearch);
        }
        if (lastIndex >= firstIndex) {
            int mid = (lastIndex - firstIndex) / 2;
            if (elementToSearch.equals(arr[mid])) {
                return mid;
            }
            ParallelOptimalFindIndex oneFind = new ParallelOptimalFindIndex(arr, firstIndex, mid, elementToSearch);
            ParallelOptimalFindIndex twoFind = new ParallelOptimalFindIndex(arr, mid + 1, lastIndex, elementToSearch);
            oneFind.fork();
            twoFind.fork();
            return Math.max((Integer) oneFind.join(), (Integer) twoFind.join());
        }
        return -1;
    }

    public static <T extends Comparable<T>> Integer optimalSearch(T[] array, T value) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return (Integer) forkJoinPool.invoke(new ParallelOptimalFindIndex(array, 0, array.length - 1, value));
    }
}
