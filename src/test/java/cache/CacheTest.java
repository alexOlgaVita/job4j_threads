package cache;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CacheTest {
    @Test
    public void whenAddFind() throws OptimisticException {
        var base = new Base(1, "Base", 1);
        var cache = new Cache();
        cache.add(base);
        var find = cache.findById(base.id());
        assertThat(find.get().name())
                .isEqualTo("Base");
    }

    @Test
    public void whenAddUpdateFind() throws OptimisticException {
        var base = new Base(1, "Base", 1);
        var cache = new Cache();
        cache.add(base);
        cache.update(new Base(1, "Base updated", 1));
        var find = cache.findById(base.id());
        assertThat(find.get().name())
                .isEqualTo("Base updated");
    }

    @Test
    public void whenAddDeleteFind() throws OptimisticException {
        var base = new Base(1, "Base", 1);
        var cache = new Cache();
        cache.add(base);
        cache.delete(1);
        var find = cache.findById(base.id());
        assertThat(find.isEmpty()).isTrue();
    }

    @Test
    public void whenMultiUpdateThrowException() throws OptimisticException {
        var base = new Base(1, "Base", 1);
        var cache = new Cache();
        cache.add(base);
        cache.update(base);
        assertThatThrownBy(() -> cache.update(base))
                .isInstanceOf(OptimisticException.class);
    }

    @Test
    public void whenNotMultiUpdateThrowException() throws OptimisticException {
        var base = new Base(1, "Base", 1);
        var cache = new Cache();
        cache.add(base);
        cache.update(base);
        assertThatThrownBy(() -> cache.update(base))
                .isInstanceOf(OptimisticException.class);
    }

    @Test
    public void whenNotMultiUpdate() throws OptimisticException {
        var base = new Base(1, "Base", 1);
        var cache = new Cache();
        cache.add(base);
        cache.update(base);
        assertThat(cache.findById(1).get().getVersion()).isEqualTo(2);
    }

    @Test
    public void whenNotMultiUpdateSecond() throws OptimisticException {
        var base = new Base(1, "Base", 1);
        var cache = new Cache();
        cache.add(base);
        cache.update(base);
        var baseNew = new Base(1, "Base2", 2);
        cache.update(baseNew);
        assertThat(cache.findById(1).get().getVersion()).isEqualTo(3);
    }
}