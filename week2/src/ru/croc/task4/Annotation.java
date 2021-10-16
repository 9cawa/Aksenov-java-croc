package ru.croc.task4;

class Annotation {
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
}
