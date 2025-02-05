package pools;

public class Search<T> {

    public static <T extends Comparable<T>> Integer indexOfBySimple(T[] arr, T value) {
        int i = 0;
        while (i < arr.length && value.equals(arr[i])) {
            i++;
        }
        return i == arr.length ? -1 : i;
    }
}