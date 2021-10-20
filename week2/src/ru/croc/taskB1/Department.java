package ru.croc.taskb1;

import java.util.ArrayList;

class Department {
    String name;
    int time;
    ArrayList<Department> departments;

    //Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }

    //Constructor
    public Department(String name, int time, ArrayList<Department> departments) {
        this.name = name;
        this.time = time;
        this.departments = departments;
    }

    public void addDepartmentChild(Department department) {
        this.departments.add(department);
    }

    public int getAllTime() {
        int timeOfChildren = 0;
        for (Department department: departments) {
            timeOfChildren = Math.max(timeOfChildren, department.getAllTime());
        }
        return this.time + timeOfChildren;
    }

    public void printHierarchy(int indentLevel) {
        String indent = "  ";
        for(int i = 0; i < indentLevel; i++) {
            System.out.print(indent);
        }
        System.out.print(this.name + ": " + this.time);
        if (this.time != 1) {
            System.out.println(" часа");
        } else {
            System.out.println(" час");
        }
        for(Department department: departments) {
            department.printHierarchy(indentLevel + 1);
        }
    }
}
