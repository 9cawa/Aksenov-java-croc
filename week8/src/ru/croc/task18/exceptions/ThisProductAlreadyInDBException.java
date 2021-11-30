package ru.croc.task18.exceptions;

public class ThisProductAlreadyInDBException extends NullPointerException {
    @Override
    public String getMessage() {
        return "Продукт с этим артикулом уже есть в базе данных!";
    }
}
