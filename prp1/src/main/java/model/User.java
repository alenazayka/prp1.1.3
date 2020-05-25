package model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User{

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    public User(int id, String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }


    @Override
    public int hashCode() {
        int result = 31 + name.hashCode();
        result = 31 * result + (age * 131 << 18);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if ((obj == null) || getClass() != obj.getClass()) return false;

        User other = (User) obj;
        if (!other.name.equals(name)) return false;
        if (other.age == age) return false;
        return true;
    }

    @Override
    public String toString() {
        return "User{" + name + ", " + age + " лет}";
    }
}
