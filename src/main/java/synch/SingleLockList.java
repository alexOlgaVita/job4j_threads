package synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    @GuardedBy("this")
    private final List<T> list;

    public SingleLockList(List<T> list) {
        this.list = copy(list);
    }

    public synchronized void add(T value) {
        list.add(value);
    }

    public synchronized T get(int index) {
        int i = -1;
        T element = null;
        while (i < index && iterator().hasNext()) {
            i++;
            if (i == index) {
                element = iterator().next();
            }
        }
        return element;
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(list).iterator();
    }

    private synchronized List<T> copy(List<T> origin) {
        List<T> coplyList = new ArrayList<>();
        for (T element : origin) {
            coplyList.add(element);
        }
        return coplyList;
    }
}
