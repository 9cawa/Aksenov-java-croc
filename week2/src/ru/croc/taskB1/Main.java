package ru.croc.taskb1;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        DepartmentA A = new DepartmentA("A",1, new ArrayList<>());
        DepartmentB B1 = new DepartmentB("B1",3, new ArrayList<>(), A);
        DepartmentB B2 = new DepartmentB("B2",1, new ArrayList<>(), A);
        DepartmentB B3 = new DepartmentB("B3",2, new ArrayList<>(), A);
        DepartmentC C11 = new DepartmentC("C11", 1, new ArrayList<>(), B1);
        DepartmentC C12 = new DepartmentC("C12", 1, new ArrayList<>(), B1);
        DepartmentC C21 = new DepartmentC("C21", 4, new ArrayList<>(), B2);
        DepartmentC C31 = new DepartmentC("C31", 1, new ArrayList<>(), B3);
        DepartmentD D311 = new DepartmentD("D311", 1, new ArrayList<>(), C31);
        A.printHierarchy(0);
        System.out.println("Время обработки заявки для этой конфигурации: " + A.getAllTime());
    }
}
