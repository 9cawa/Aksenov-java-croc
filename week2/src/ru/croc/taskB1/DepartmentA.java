package ru.croc.taskb1;

import java.util.ArrayList;

public class DepartmentA extends Department{

    public DepartmentA() {
        super("NoName", 0, new ArrayList<>());
    }

    public DepartmentA(String name, int time, ArrayList<Department> departments) {
        super(name, time, departments);
    }
}
