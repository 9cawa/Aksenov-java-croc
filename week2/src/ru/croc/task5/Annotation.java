package ru.croc.task5;


public class Annotation {
    public String info;
    public Figure figure;

    public Annotation(Figure figure, String info) {
        this.figure = figure;
        this.info = info;
    }

    @Override
    public String toString() {
        return this.figure.toString() + ": " + this.info;
    }

    //Getters and Setters
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }

    public Figure getFigure() {
        return figure;
    }
    public void setFigure(Figure figure) {
        this.figure = figure;
    }
}

