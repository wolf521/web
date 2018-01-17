package main.java.util;

import java.util.*;

/**
 * Created by Administrator on 2018/1/17.
 */
public class SortTest {

    public static void main(String[] args) {
        new SortTest().testCollection();
    }

    public void testCollection() {
        List<Dog> list = new ArrayList<Dog>();
        list.add(new Dog(5, "DogA"));
        list.add(new Dog(1, "DogB"));
        list.add(new Dog(3, "DogC"));
        list.add(new Dog(8, "DogD"));
        list.add(new Dog(6, "DogE"));
        list.add(new Dog(7, "DogF"));
        list.add(new Dog(4, "DogD"));
        list.add(new Dog(20, "DogH"));
        list.add(new Dog(34, "DogG"));
        Collections.sort(list, new Comparator<Dog>() {

            @Override
            public int compare(Dog o1, Dog o2) {
                return o1.age - o2.age;
            }
        });
        System.out.println("给狗狗按照年龄倒序：" + list);
        Collections.sort(list, new Comparator<Dog>() {

            @Override
            public int compare(Dog o1, Dog o2) {
                return o2.name.compareTo(o1.name);
            }
        });
        System.out.println("给狗狗按名字字母顺序排序：" + list);
        System.out.println("b".compareTo("b"));
    }
}
class Dog {
    public int age;
    public String name;

    public Dog(int age, String name) {
        super();
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog [age=" + age + ", name=" + name + "]";
    }
}