package cache;

public record Base(int id, String name, int version) {

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getVersion() {
        return version;
    }

//    public void setName(String newName) {
//        name = newName;
//    }
}
