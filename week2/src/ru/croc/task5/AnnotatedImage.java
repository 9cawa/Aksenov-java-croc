package ru.croc.task5;


import ru.croc.task4.Annotation;

class AnnotatedImage {

    private final String imagePath;

    private final Annotation[] annotations;

    public AnnotatedImage(String imagePath, Annotation... annotations) {
        this.imagePath = imagePath;
        this.annotations = annotations;
    }

    Annotation findByPoint(int x, int y) {
        for (Annotation annotation: annotations) {
            if (annotation.toString().contains("(" + x + ", " + y + ")")) {
                return annotation;
            }
        }
        return null;
    }

    Annotation findByLabel(String label) {
        for (Annotation annotation: annotations) {
            if (annotation.toString().contains(label)) {
                return annotation;
            }
        }
        return null;
    }

    //Getters
    public String getImagePath() {
        return this.imagePath;
    }
    public Annotation[] getAnnotations() {
        return this.annotations;
    }
}
