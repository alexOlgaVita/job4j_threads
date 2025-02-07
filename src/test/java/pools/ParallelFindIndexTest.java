package pools;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParallelFindIndexTest {
    /* Считаем, что элементы в массиве уникальные => не рассматриваем кейсы с поиском первого найденного элемента среди таких же */

    /* Целочисленный тип */
    @Test
    public void whenIntegerSmallSizeIndexNotFound() {
        Integer[] arr = {32, 34, 101, 45, 654};
        Integer value = 100;
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(-1);
    }

    @Test
    public void whenIntegerSmallSizeFirstIndexFound() {
        Integer[] arr = {32, 34, 101, 45, 654};
        Integer value = 32;
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(0);
    }

    @Test
    public void whenIntegerSmallSizeFirstTwoSameIndexFound() {
        Integer[] arr = {32, 34, 101, 32, 45, 654};
        Integer value = 32;
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(0);
    }

    @Test
    public void whenIntegerSmallSizeSecondIndexFound() {
        Integer[] arr = {32, 34, 101, 45, 654};
        Integer value = 34;
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(1);
    }

    @Test
    public void whenIntegerSmallSizeSecondTwoSameIndexFound() {
        Integer[] arr = {32, 34, 101, 45, 654};
        Integer value = 34;
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(1);
    }

    @Test
    public void whenIntegerSmallSizeMiddleIndexFound() {
        Integer[] arr = {32, 34, 101, 45, 654};
        Integer value = 101;
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(2);
    }

    @Test
    public void whenIntegerSmallSizeMiddleTwoSameIndexFound() {
        Integer[] arr = {32, 34, 101, 45, 654, 101, 34, 11};
        Integer value = 101;
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(2);
    }

    @Test
    public void whenIntegerSmallSizeLastIndexFound() {
        Integer[] arr = {32, 34, 101, 45, 654};
        Integer value = 654;
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(4);
    }

    @Test
    public void whenIntegerSmallSizePrevLastIndexFound() {
        Integer[] arr = {32, 34, 101, 45, 654};
        Integer value = 45;
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(3);
    }

    @Test
    public void whenIntegerBigSizeIndexNotFound() {
        Integer[] arr = {32, 34, 101, 45, 654, 354, 24, 54, 27, 54, 83, 68, 27, 45};
        Integer value = 113;
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(-1);
    }

    @Test
    public void whenIntegerBigSizeFirstIndexFound() {
        Integer[] arr = {32, 34, 101, 45, 654, 354, 24, 54, 27, 754, 83, 68};
        Integer value = 32;
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(0);
    }

    @Test
    public void whenIntegerBigSizeSecondIndexFound() {
        Integer[] arr = {32, 34, 101, 45, 654, 354, 24, 54, 27, 754, 83, 68};
        Integer value = 34;
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(1);
    }

    @Test
    public void whenIntegerBigSizeMiddleIndexFound() {
        Integer[] arr = {32, 34, 101, 45, 654, 354, 24, 54, 27, 754, 83, 68};
        Integer value = 27;
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(8);
    }

    @Test
    public void whenIntegerBigSizeMiddleIndexTwoSameFound() {
        Integer[] arr = {32, 34, 101, 45, 654, 354, 24, 54, 27, 54, 83, 68, 27, 45};
        Integer value = 27;
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(8);
    }

    @Test
    public void whenIntegerBigSizeLastIndexFound() {
        Integer[] arr = {32, 34, 101, 45, 654, 354, 24, 54, 27, 754, 83, 68};
        Integer value = 68;
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(11);
    }

    @Test
    public void whenIntegerBigSizePrevLastIndexFound() {
        Integer[] arr = {32, 34, 101, 45, 654, 354, 24, 54, 27, 754, 83, 68};
        Integer value = 83;
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(10);
    }

    /* Строковый тип */
    @Test
    public void whenStringSmallSizeIndexNotFound() {
        String[] arr = {"Marusya", "Margo", "Nik", "Sveta", "Julia"};
        String value = "Olga";
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(-1);
    }

    @Test
    public void whenStringSmallSizeFirstIndexFound() {
        String[] arr = {"Marusya", "Margo", "Nik", "Sveta", "Julia"};
        String value = "Marusya";
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(0);
    }

    @Test
    public void whenStringSmallSizeFirstTwoSameIndexFound() {
        String[] arr = {"Marusya", "Margo", "Marusya", "Nik", "Sveta", "Julia"};
        String value = "Marusya";
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(0);
    }

    @Test
    public void whenStringSmallSizeSecondIndexFound() {
        String[] arr = {"Marusya", "Margo", "Marusya", "Nik", "Sveta", "Julia"};
        String value = "Margo";
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(1);
    }

    @Test
    public void whenStringSmallSizeSecondTwoSameIndexFound() {
        String[] arr = {"Marusya", "Margo", "Marusya", "Nik", "Sveta", "Margo", "Julia"};
        String value = "Margo";
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(1);
    }

    @Test
    public void whenStringSmallSizeMiddleIndexFound() {
        String[] arr = {"Marusya", "Margo", "Nik", "Sveta", "Margo", "Julia"};
        String value = "Nik";
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(2);
    }

    @Test
    public void whenStringSmallSizeMiddleTwoSameIndexFound() {
        String[] arr = {"Marusya", "Margo", "Nik", "Sveta", "Margo", "Nik", "Julia"};
        String value = "Nik";
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(2);
    }

    @Test
    public void whenStringSmallSizeLastIndexFound() {
        String[] arr = {"Marusya", "Margo", "Nik", "Sveta", "Julia"};
        String value = "Julia";
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(4);
    }

    @Test
    public void whenStringSmallSizePrevLastIndexFound() {
        String[] arr = {"Marusya", "Margo", "Nik", "Sveta", "Julia"};
        String value = "Sveta";
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(3);
    }

    @Test
    public void whenStringBigSizeFirstIndexFound() {
        String[] arr = {"Marusya", "Margo", "Nik", "Sveta", "Julia", "Timur", "Roma", "Alisa", "Andrey", "Eugene", "Bogdan", "Lubov"};
        String value = "Marusya";
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(0);
    }

    @Test
    public void whenStringBigSizeFirstTwoSameIndexFound() {
        String[] arr = {"Marusya", "Margo", "Nik", "Sveta", "Marusya", "Julia", "Timur", "Roma", "Alisa", "Andrey", "Eugene", "Bogdan", "Lubov"};
        String value = "Marusya";
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(0);
    }

    @Test
    public void whenStringBigSizeSecondIndexFound() {
        String[] arr = {"Marusya", "Margo", "Nik", "Sveta", "Julia", "Timur", "Roma", "Alisa", "Andrey", "Eugene", "Bogdan", "Lubov"};
        String value = "Margo";
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(1);
    }

    @Test
    public void whenStringBigSizeSecondTwoSameIndexFound() {
        String[] arr = {"Marusya", "Margo", "Nik", "Sveta", "Margo", "Julia", "Timur", "Roma", "Alisa", "Andrey", "Eugene", "Bogdan", "Lubov"};
        String value = "Margo";
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(1);
    }

    @Test
    public void whenStringBigSizeMiddleIndexFound() {
        String[] arr = {"Marusya", "Margo", "Nik", "Sveta", "Julia", "Timur", "Roma", "Alisa", "Andrey", "Eugene", "Bogdan", "Lubov"};
        String value = "Sveta";
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(3);
    }

    @Test
    public void whenStringBigSizeLastIndexFound() {
        String[] arr = {"Marusya", "Margo", "Nik", "Sveta", "Julia", "Timur", "Roma", "Alisa", "Andrey", "Eugene", "Bogdan", "Lubov"};
        String value = "Lubov";
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(11);
    }

    @Test
    public void whenStringBigSizePrevLastIndexFound() {
        String[] arr = {"Marusya", "Margo", "Nik", "Sveta", "Julia", "Timur", "Roma", "Alisa", "Andrey", "Eugene", "Bogdan", "Lubov"};
        String value = "Bogdan";
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(10);
    }

    @Test
    void whenRoleSmallSizeIndexNotFoundFound() {
        Role role1 = new Role(2, "Admin");
        Role role2 = new Role(1, "Security");
        Role role3 = new Role(3, "Test");
        Role role4 = new Role(3, "Test");
        Role[] arr = {role1, role2, role3};
        Role value = role4;
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(-1);
    }

    @Test
    void whenRoleSmallSizeFirstIndexFound() {
        Role role1 = new Role(2, "Admin");
        Role role2 = new Role(1, "Security");
        Role role3 = new Role(3, "Test");
        Role[] arr = {role1, role2, role3};
        Role value = role1;
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(0);
    }

    @Test
    void whenRoleSmallSizeFirstTwoSameIndexFound() {
        Role role1 = new Role(2, "Admin");
        Role role2 = new Role(1, "Security");
        Role role3 = new Role(3, "Test");
        Role[] arr = {role1, role2, role1, role3};
        Role value = role1;
        assertThat(ParallelFindIndex.optimalSearch(arr, value)).isEqualTo(0);
    }

    private static class Role implements Comparable<Role> {
        private Integer id;
        private String name;

        public Role(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public int compareTo(Role o) {
            return (this.getId() == o.getId()) ? this.getName().compareTo(o.getName()) : this.getId() - o.getId();
        }
    }
}