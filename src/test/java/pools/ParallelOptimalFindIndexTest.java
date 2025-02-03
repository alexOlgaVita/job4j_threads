package pools;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParallelOptimalFindIndexTest {

    /* Целочисленный тип */
    @Test
    public void whenIntegerSmallSizeIndexNotFound() {
        Integer[] arr = {32, 34, 101, 45, 654};
        Integer value = 100;
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(-1);
    }

    @Test
    public void whenIntegerSmallSizeFirstIndexFound() {
        Integer[] arr = {32, 34, 101, 45, 654};
        Integer value = 32;
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(0);
    }

    @Test
    public void whenIntegerSmallSizeFirstTwoSameIndexFound() {
        Integer[] arr = {32, 34, 101, 32, 45, 654};
        Integer value = 32;
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(0);
    }

    @Test
    public void whenIntegerSmallSizeSecondIndexFound() {
        Integer[] arr = {32, 34, 101, 45, 654};
        Integer value = 34;
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(1);
    }

    @Test
    public void whenIntegerSmallSizeSecondTwoSameIndexFound() {
        Integer[] arr = {32, 34, 101, 45, 654};
        Integer value = 34;
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(1);
    }

    @Test
    public void whenIntegerSmallSizeMiddleIndexFound() {
        Integer[] arr = {32, 34, 101, 45, 654};
        Integer value = 101;
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(2);
    }

    @Test
    public void whenIntegerSmallSizeMiddleTwoSameIndexFound() {
        Integer[] arr = {32, 34, 101, 45, 654, 101, 34, 11};
        Integer value = 101;
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(2);
    }

    @Test
    public void whenIntegerSmallSizeLastIndexFound() {
        Integer[] arr = {32, 34, 101, 45, 654};
        Integer value = 654;
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(4);
    }

    @Test
    public void whenIntegerSmallSizePrevLastIndexFound() {
        Integer[] arr = {32, 34, 101, 45, 654};
        Integer value = 45;
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(3);
    }

    @Test
    public void whenIntegerBigSizeIndexNotFound() {
        Integer[] arr = {32, 34, 101, 45, 654, 354, 24, 54, 27, 54, 83, 68, 27, 45};
        Integer value = 113;
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(-1);
    }

    @Test
    public void whenIntegerBigSizeFirstIndexFound() {
        Integer[] arr = {32, 34, 101, 45, 654, 354, 24, 54, 27, 754, 83, 68};
        Integer value = 32;
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(0);
    }

    @Test
    public void whenIntegerBigSizeFirstTwoSameIndexFound() {
        Integer[] arr = {32, 34, 101, 45, 654, 354, 24, 54, 27, 754, 32, 83, 68};
        Integer value = 32;
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(0);
    }

    @Test
    public void whenIntegerBigSizeSecondIndexFound() {
        Integer[] arr = {32, 34, 101, 45, 654, 354, 24, 54, 27, 754, 83, 68};
        Integer value = 34;
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(1);
    }

    @Test
    public void whenIntegerBigSizeSecondTwoSameIndexFound() {
        Integer[] arr = {32, 34, 101, 45, 654, 354, 24, 54, 27, 754, 34, 83, 68};
        Integer value = 34;
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(1);
    }

    @Test
    public void whenIntegerBigSizeMiddleIndexFound() {
        Integer[] arr = {32, 34, 101, 45, 654, 354, 24, 54, 27, 754, 83, 68};
        Integer value = 27;
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(8);
    }

    @Test
    public void whenIntegerBigSizeMiddleIndexTwoSameFound() {
        Integer[] arr = {32, 34, 101, 45, 654, 354, 24, 54, 27, 54, 83, 68, 27, 45};
        Integer value = 27;
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(8);
    }

    @Test
    public void whenIntegerBigSizeLastIndexFound() {
        Integer[] arr = {32, 34, 101, 45, 654, 354, 24, 54, 27, 754, 83, 68};
        Integer value = 68;
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(11);
    }

    @Test
    public void whenIntegerBigSizePrevLastIndexFound() {
        Integer[] arr = {32, 34, 101, 45, 654, 354, 24, 54, 27, 754, 83, 68};
        Integer value = 83;
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(10);
    }

    /* Строковый тип */
    @Test
    public void whenStringSmallSizeIndexNotFound() {
        String[] arr = {"Marusya", "Margo", "Nik", "Sveta", "Julia"};
        String value = "Olga";
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(-1);
    }

    @Test
    public void whenStringSmallSizeFirstIndexFound() {
        String[] arr = {"Marusya", "Margo", "Nik", "Sveta", "Julia"};
        String value = "Marusya";
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(0);
    }

    @Test
    public void whenStringSmallSizeFirstTwoSameIndexFound() {
        String[] arr = {"Marusya", "Margo", "Marusya", "Nik", "Sveta", "Julia"};
        String value = "Marusya";
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(0);
    }

    @Test
    public void whenStringSmallSizeSecondIndexFound() {
        String[] arr = {"Marusya", "Margo", "Marusya", "Nik", "Sveta", "Julia"};
        String value = "Margo";
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(1);
    }

    @Test
    public void whenStringSmallSizeSecondTwoSameIndexFound() {
        String[] arr = {"Marusya", "Margo", "Marusya", "Nik", "Sveta", "Margo", "Julia"};
        String value = "Margo";
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(1);
    }

    @Test
    public void whenStringSmallSizeMiddleIndexFound() {
        String[] arr = {"Marusya", "Margo", "Nik", "Sveta", "Margo", "Julia"};
        String value = "Nik";
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(2);
    }

    @Test
    public void whenStringSmallSizeMiddleTwoSameIndexFound() {
        String[] arr = {"Marusya", "Margo", "Nik", "Sveta", "Margo", "Nik", "Julia"};
        String value = "Nik";
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(2);
    }

    @Test
    public void whenStringSmallSizeLastIndexFound() {
        String[] arr = {"Marusya", "Margo", "Nik", "Sveta", "Julia"};
        String value = "Julia";
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(4);
    }

    @Test
    public void whenStringSmallSizePrevLastIndexFound() {
        String[] arr = {"Marusya", "Margo", "Nik", "Sveta", "Julia"};
        String value = "Sveta";
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(3);
    }

    @Test
    public void whenStringBigSizeIndexNotFound() {
        String[] arr = {"Marusya", "Margo", "Nik", "Sveta", "Julia", "Timur", "Roma", "Alisa", "Andrey", "Eugene", "Bogdan", "Lubov"};
        String value = "Micke";
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(-1);
    }

    @Test
    public void whenStringBigSizeFirstIndexFound() {
        String[] arr = {"Marusya", "Margo", "Nik", "Sveta", "Julia", "Timur", "Roma", "Alisa", "Andrey", "Eugene", "Bogdan", "Lubov"};
        String value = "Marusya";
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(0);
    }

    @Test
    public void whenStringBigSizeFirstTwoSameIndexFound() {
        String[] arr = {"Marusya", "Margo", "Nik", "Sveta", "Marusya", "Julia", "Timur", "Roma", "Alisa", "Andrey", "Eugene", "Bogdan", "Lubov"};
        String value = "Marusya";
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(0);
    }

    @Test
    public void whenStringBigSizeSecondIndexFound() {
        String[] arr = {"Marusya", "Margo", "Nik", "Sveta", "Julia", "Timur", "Roma", "Alisa", "Andrey", "Eugene", "Bogdan", "Lubov"};
        String value = "Margo";
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(1);
    }

    @Test
    public void whenStringBigSizeSecondTwoSameIndexFound() {
        String[] arr = {"Marusya", "Margo", "Nik", "Sveta", "Margo", "Julia", "Timur", "Roma", "Alisa", "Andrey", "Eugene", "Bogdan", "Lubov"};
        String value = "Margo";
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(1);
    }

    @Test
    public void whenStringBigSizeMiddleIndexFound() {
        String[] arr = {"Marusya", "Margo", "Nik", "Sveta", "Julia", "Timur", "Roma", "Alisa", "Andrey", "Eugene", "Bogdan", "Lubov"};
        String value = "Sveta";
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(3);
    }

    @Test
    public void whenStringBigSizeMiddleIndexTwoSameFound() {
        String[] arr = {"Marusya", "Margo", "Nik", "Sveta", "Julia", "Timur", "Roma", "Alisa", "Andrey", "Julia", "Eugene", "Bogdan", "Lubov"};
        String value = "Julia";
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(4);
    }

    @Test
    public void whenStringBigSizeLastIndexFound() {
        String[] arr = {"Marusya", "Margo", "Nik", "Sveta", "Julia", "Timur", "Roma", "Alisa", "Andrey", "Eugene", "Bogdan", "Lubov"};
        String value = "Lubov";
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(11);
    }

    @Test
    public void whenStringBigSizePrevLastIndexFound() {
        String[] arr = {"Marusya", "Margo", "Nik", "Sveta", "Julia", "Timur", "Roma", "Alisa", "Andrey", "Eugene", "Bogdan", "Lubov"};
        String value = "Bogdan";
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(10);
    }

    @Test
    void whenRoleSmallSizeIndexNotFoundFound() {
        Role role1 = new Role(2, "Admin");
        Role role2 = new Role(1, "Security");
        Role role3 = new Role(3, "Test");
        Role role4 = new Role(3, "Test");
        Role[] arr = {role1, role2, role3};
        Role value = role4;
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(-1);
    }

    @Test
    void whenRoleSmallSizeFirstIndexFound() {
        Role role1 = new Role(2, "Admin");
        Role role2 = new Role(1, "Security");
        Role role3 = new Role(3, "Test");
        Role[] arr = {role1, role2, role3};
        Role value = role1;
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(0);
    }

    @Test
    void whenRoleSmallSizeFirstTwoSameIndexFound() {
        Role role1 = new Role(2, "Admin");
        Role role2 = new Role(1, "Security");
        Role role3 = new Role(3, "Test");
        Role[] arr = {role1, role2, role1, role3};
        Role value = role1;
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(0);
    }

    @Test
    void whenRoleBigSizeMiddleTwoSameIndexFound() {
        Role role1 = new Role(2, "Admin");
        Role role2 = new Role(1, "Security");
        Role role3 = new Role(3, "Test");
        Role role4 = new Role(3, "Test4");
        Role role5 = new Role(3, "Test");
        Role role6 = new Role(3, "Test");
        Role role7 = new Role(3, "Test");
        Role role8 = new Role(3, "Test");
        Role role9 = new Role(3, "Test");
        Role role10 = new Role(3, "Test");
        Role role11 = new Role(3, "Test");
        Role role12 = new Role(3, "Test");
        Role[] arr = {role1, role2, role3, role4, role5, role6, role7, role8, role9, role10, role11, role12, role4};
        Role value = role4;
        assertThat(ParallelOptimalFindIndex.optimalSearch(arr, value)).isEqualTo(3);
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