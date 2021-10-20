package ru.croc.taskb1;

import java.util.ArrayList;

public class DepartmentB extends Department {

    public DepartmentB() {
        super("NoName", 0, new ArrayList<>());
    }

    public DepartmentB(String name, int time, ArrayList<Department> departments, DepartmentA parent) {
        super(name, time, departments);
        parent.addDepartmentChild(this);
    }
}
