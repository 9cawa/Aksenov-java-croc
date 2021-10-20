package ru.croc.taskb1;

import java.util.ArrayList;

public class DepartmentC extends Department{

    public DepartmentC() {
        super("NoName", 0, new ArrayList<>());
    }

    public DepartmentC(String name, int time, ArrayList<Department> departments, DepartmentB parent) {
        super(name, time, departments);
        parent.addDepartmentChild(this);
    }
}
