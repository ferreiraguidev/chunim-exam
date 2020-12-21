package Chunim;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarDAO {

    public static void save(Car carro) {

        String sql = "INSERT INTO `chunim`.`car`(" +
                "`name`," +
                "`type`," +
                "`brand`," +
                "`model`," +
                "`year`," +
                "`price`," +
                "`imagespath`," +
                "`description`)" +
                "VALUES(?,?,?,?,?,?,?,?)";

        try {
            Connection connection = ConnectionFactory.getConexao();
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, carro.getName());
            ps.setString(2, carro.getType());
            ps.setString(3, carro.getBrand());
            ps.setString(4, carro.getModel());
            ps.setString(5, carro.getYear());
            ps.setString(6, carro.getPrice());
            ps.setString(7, carro.getImagespath());
            ps.setString(8, carro.getDescription());

            ps.execute();
            ConnectionFactory.close(connection, ps);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void edit(Car carro) { // try with resources * update it !

        String sql = "UPDATE `chunim`.`car`" +
                "SET `name` = ?, " +
                "`type` = ?, " +
                "`brand` = ?, " +
                "`model` = ?, " +
                "`year` = ?, " +
                "`price` = ?, " +
                "`imagespath`= ?, " +
                "`description` = ? " +
                "WHERE (`id`= ?);";

        try (Connection connection = ConnectionFactory.getConexao();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, carro.getName());
            ps.setString(2, carro.getType());
            ps.setString(3, carro.getBrand());
            ps.setString(4, carro.getModel());
            ps.setString(5, carro.getYear());
            ps.setString(6, carro.getPrice());
            ps.setString(7, carro.getImagespath());

            ps.setInt(8, carro.getId());

            ps.execute();
            ConnectionFactory.close(connection, ps);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(Car car) {

        String sql = "DELETE FROM `chunim`.`car` WHERE (`id` = ?);";

        try {
            Connection connection = ConnectionFactory.getConexao();
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, car.getId());

            ps.executeUpdate();
            ConnectionFactory.close(connection, ps);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Car> search() {

        String sql = "SELECT * FROM car"; // Do we always have to close connection ?

        try {
            Connection connection = ConnectionFactory.getConexao();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            List<Car> cars = new ArrayList<>();
            while (resultSet.next()) {

                Car car = new Car();
                car.setId(resultSet.getInt("id"));
                car.setName(resultSet.getString("Name"));
                car.setType(resultSet.getString("Type"));
                car.setBrand(resultSet.getString("Brand"));
                car.setModel(resultSet.getString("Model"));
                car.setYear(resultSet.getString("Year"));
                car.setPrice(resultSet.getString("Price"));
                car.setImagespath(resultSet.getString("imagespath"));
                car.setDescription(resultSet.getString("description"));

                cars.add(car);
            }
            return cars;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Car> searchByBrand(String brand) {

        String sql = "SELECT FROM car WHERE brand LIKE ?";
        List<Car> cars = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConexao();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, "%" + brand + "%");

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {

                cars.add(new Car(resultSet.getString("name"), resultSet.getString("type"),
                        resultSet.getString("brand"), resultSet.getString("model"),
                        resultSet.getString("year"), resultSet.getString("price"),
                        resultSet.getInt("id"), resultSet.getString("description"),
                        resultSet.getString("imagespath"), resultSet.getString("pastaImagens"),
                        Collections.singletonList(resultSet.getString("imagesFolder"))));
            }
            ConnectionFactory.close(connection, ps);
            return cars;


        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
