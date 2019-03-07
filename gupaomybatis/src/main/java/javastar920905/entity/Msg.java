package javastar920905.entity;

/**
 * @author ouzhx on 2019/3/7.
 */
public class Msg {
    private String id;
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
