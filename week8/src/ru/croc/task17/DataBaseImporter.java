package ru.croc.task17;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;

public class DataBaseImporter {

    //Строка с запросом к БД для создания таблицы Products
    private static final String CreateProducts = "CREATE TABLE Products" +
            "(ArticleID VARCHAR(255) PRIMARY KEY," +
            "Product VARCHAR(255) NOT NULL, " +
            "Cost INT NOT NULL)";

    //Строка с запросом к БД для создания таблицы Orders
    private static final String CreateOrders = "CREATE TABLE Orders" +
            "(ID INT NOT NULL, " +
            "UserName VARCHAR(255) NOT NULL, " +
            "Article VARCHAR(255), " +
            "foreign key (Article) references Products(ArticleID))";


    public static void main(String[] args) {
        List<String> orders = new ArrayList<>(); //Список, куда записываются заказы (ID, UserName, Article)
        List<String> products = new ArrayList<>(); //Список, куда записываются товары (ArticleID, Product, Cost)
        readFile(args[0],orders,products); //Считываем из файла заказы

        String connectionUrl = "jdbc:h2:tcp://localhost/~/test/test";

        try (Connection connection = DriverManager.getConnection(connectionUrl, "sa", "12")) {
            createTableInDB(connection, CreateProducts);
            createTableInDB(connection, CreateOrders);

            importProductsToDB(connection, products);
            importOrdersToDB(connection,orders);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /** Метод, считывающий из файла информацию о заказах и записывающий ее в два списка **/
    static void readFile(String path,List<String> orders, List<String> product) {
        Scanner scanner;
        try {
            scanner = new Scanner(Paths.get(path));
        } catch (IOException e) {
            System.out.println("Не удалось открыть файл");
            return;
        }

        String tmp; //В эту переменную в каждой итерации записываем текущую строку
        while (scanner.hasNextLine()) {
            tmp = scanner.nextLine();

            //В список orders добавляем (ID, UserName, Article)
            orders.add(tmp.split(",")[0] + "," + tmp.split(",")[1] + "," + tmp.split(",")[2]);

            //Если в списке product нет того же артикула,
            // что и в текущей строке, то добавляем (ArticleID, Product, Cost)
            if (!product.toString().contains(tmp.split(",")[2]))
                product.add(tmp.split(",")[2] + "," +tmp.split(",")[3] + "," + tmp.split(",")[4]);
        }
    }

    /** Метод, создающий таблицу в базе данных **/
    static void createTableInDB(Connection connection, String sql) throws SQLException{
        try (Statement statement = connection.createStatement()){
            statement.execute(sql);
        }
    }

    /** Метод, который заполняет таблицу Products **/
    static void importProductsToDB(Connection connection, List<String> products) throws SQLException{
        String sql = "INSERT INTO Products VALUES(?,?,?)";
        for (String product : products) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, product.split(",")[0]);
                statement.setString(2, product.split(",")[1]);
                statement.setInt(3, Integer.parseInt(product.split(",")[2]));
                statement.execute();
            }
        }
    }

    /** Метод, который заполняет таблицу Orders **/
    static void importOrdersToDB(Connection connection, List<String> orders) throws SQLException {
        String sql = "INSERT INTO Orders VALUES(?,?,?)";
        for (String order : orders) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, Integer.parseInt(order.split(",")[0]));
                statement.setString(2, order.split(",")[1]);
                statement.setString(3, order.split(",")[2]);
                statement.execute();
            }
        }
    }

}
