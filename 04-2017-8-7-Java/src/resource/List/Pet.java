package resource.List;

/**
 * Created by wang on 2017/8/6.
 */
public class Pet implements Comparable {
    private String name;

    public Pet(String name) {
        this.name = name;
    }

    public Pet(String name, int heat) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Pet pet = (Pet) o;
        return this.name.compareTo(pet.getName());
    }

    @Override
    /*
    * 泛型..
    * 自定义equals的具体实现
    * */
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        /*
        * 强制类型转换
        * */
        Pet pet = (Pet) obj;
        if (this.name.equals(pet.getName())) {
            return true;
        } else {
            return false;
        }
    }
}
