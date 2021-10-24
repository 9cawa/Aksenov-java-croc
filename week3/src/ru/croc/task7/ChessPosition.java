package ru.croc.task7;

public class ChessPosition {
    private int x;
    private int y;

    /** Constructors **/
    public ChessPosition() {

    }

    public ChessPosition(int x, int y) throws IllegalPositionException {
        if ((x > 7 || x < 0) || (y > 7 || y < 0)) {
            throw new IllegalPositionException(x, y);
        } else {
            this.x = x;
            this.y = y;
        }
    }

    /** Фабричный метод **/
    static ChessPosition parse(String position) throws IllegalPositionException {
        //X - это значение первого символа в строке в соответствии с таблицей символов ASCII
        int x = (position.charAt(0) - 97);
        //Y - это int значение второго символа в строке - 1, т.к. координаты отсчитываются от нуля
        int y = Integer.parseInt(String.valueOf(position.charAt(1))) - 1;
        return new ChessPosition(x, y);
    }

    /** Getters and Setters **/
    public int getX() {
        return x;
    }
    public void setX(int x) throws IllegalPositionException {
        if (x > 7 || x < 0) {
            throw new IllegalPositionException(x,y);
        } else {
            this.x = x;
        }
    }

    public int getY() {
        return y;
    }
    public void setY(int y) throws IllegalPositionException {
        if (y > 7 || y < 0) {
            throw new IllegalPositionException(x,y);
        } else {
            this.y = y;
        }
    }

    @Override
    public String toString() {
        //X делаю в char и прибавляю 97, так как в таблице ASCII: 97 -> a
        //к Y прибавляю единицу, т.к. отсчет координат начинается с нуля
        return "" + (char) (this.x + 97) + (this.y + 1);
    }
}
