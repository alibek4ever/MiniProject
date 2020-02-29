import java.io.Serializable;

public class Student implements Serializable{
    Long id;
    String name;
    String surname;
    int age;

    public Student() {
    }

    public Student(Long id, String name, String surname, int age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }


    public String toString(){
        return id+" "+name+" "+surname+" "+age;
    }

}