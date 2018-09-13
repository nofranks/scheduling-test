package org.olly.scheduler.model;

public class Task {

    private String name;
    private Task dependant;

    public Task(String name, Task dependant) {
        this.name = name;
        this.dependant = dependant;
    }

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Task getDependant() {
        return dependant;
    }

    public void setDependant(Task dependant) {
        this.dependant = dependant;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                '}';
    }
}
