package ru.croc.taskb1;

import java.util.ArrayList;

public class DepartmentD extends Department{

    public DepartmentD() {
        super("NoName", 0, new ArrayList<>());
    }

    public DepartmentD(String name, int time, ArrayList<Department> departments, DepartmentC parent) {
        super(name, time, departments);
        parent.addDepartmentChild(this);
    }
}
