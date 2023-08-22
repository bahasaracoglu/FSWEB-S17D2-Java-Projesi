package model;

public class Developer {
    private int id;
    private String name;
    private int salary;
    private Experience experience;

    public Developer(int id, String name, int salary, Experience experience) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.experience = experience;
    }
}
