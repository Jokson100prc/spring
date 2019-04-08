package spring.myselfhelloworld;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;


@Entity
public class MyObject {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Collection<ChildObject> childObjects = new ArrayList<>();


    public MyObject() {
    }

    public MyObject(String name) {
        this.name = name;
    }

    public MyObject(int id){
        this.id = id;
    }


    public Collection<ChildObject> getChildObjects() {
        return childObjects;
    }

    public void setChildObjects(Collection<ChildObject> childObjects) {
        this.childObjects = childObjects;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyObject{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyObject myObject = (MyObject) o;
        return id == myObject.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
