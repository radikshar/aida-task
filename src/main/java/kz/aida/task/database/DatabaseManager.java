package kz.aida.task.database;

import java.sql.*;

public class DatabaseManager {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    private static final String DB_NAME = "bus_schedule";
    private static final String DB_HOST = "localhost";
    private static final int DB_PORT = 3306;

    private static Connection connection;

    public static BusJourney selectBusJourneyById(int id) {
        String sql = "SELECT * FROM bus_journey WHERE id = ?";

        openConnection();
        if (connection == null) return null;

        BusJourney busJourney = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    busJourney = new BusJourney();

                    busJourney.setId(resultSet.getInt(1));
                    busJourney.setFromStation(resultSet.getString(2));
                    busJourney.setFromTime(resultSet.getInt(3));
                    busJourney.setToStation(resultSet.getString(4));
                    busJourney.setToTime(resultSet.getInt(5));
                    busJourney.setPrice(resultSet.getInt(6));
                    busJourney.setDriver(resultSet.getString(7));
                    busJourney.setMonday(resultSet.getBoolean(8));
                    busJourney.setTuesday(resultSet.getBoolean(9));
                    busJourney.setWednesday(resultSet.getBoolean(10));
                    busJourney.setThursday(resultSet.getBoolean(11));
                    busJourney.setFriday(resultSet.getBoolean(12));
                    busJourney.setSaturday(resultSet.getBoolean(13));
                    busJourney.setSunday(resultSet.getBoolean(14));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();
        return busJourney;
    }

    public static void insertBusJourney(BusJourney busJourney) {
        String sql = "INSERT INTO bus_journey (from_station, from_time, to_station, to_time, price, driver, is_monday, is_tuesday, is_wednesday, is_thursday, is_friday, is_saturday, is_sunday)"
        + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        openConnection();
        if (connection == null) return;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, busJourney.getFromStation());
            preparedStatement.setInt(2, busJourney.getFromTime());
            preparedStatement.setString(3, busJourney.getToStation());
            preparedStatement.setInt(4, busJourney.getToTime());
            preparedStatement.setInt(5, busJourney.getPrice());
            preparedStatement.setString(6, busJourney.getDriver());
            preparedStatement.setBoolean(7, busJourney.isMonday());
            preparedStatement.setBoolean(8, busJourney.isThursday());
            preparedStatement.setBoolean(9, busJourney.isWednesday());
            preparedStatement.setBoolean(10, busJourney.isThursday());
            preparedStatement.setBoolean(11, busJourney.isFriday());
            preparedStatement.setBoolean(12, busJourney.isSaturday());
            preparedStatement.setBoolean(13, busJourney.isSunday());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();
    }

    public static void deleteBusJourney(int id){
        String sql = "DELETE FROM bus_journey WHERE id = ?";

        openConnection();
        if (connection == null) return;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();
    }

    public static void updateBusJourney(BusJourney busJourney){
        String sql = "UPDATE bus_journey SET from_station=?, from_time=?, to_station=?, to_time=?, price=?, driver=?, is_monday=?, is_tuesday=?, is_wednesday=?, is_thursday=?, is_friday=?, is_saturday=?, is_sunday=? WHERE id=?";

        openConnection();
        if (connection == null) return;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, busJourney.getFromStation());
            preparedStatement.setInt(2, busJourney.getFromTime());
            preparedStatement.setString(3, busJourney.getToStation());
            preparedStatement.setInt(4, busJourney.getToTime());
            preparedStatement.setInt(5, busJourney.getPrice());
            preparedStatement.setString(6, busJourney.getDriver());
            preparedStatement.setBoolean(7, busJourney.isMonday());
            preparedStatement.setBoolean(8, busJourney.isThursday());
            preparedStatement.setBoolean(9, busJourney.isWednesday());
            preparedStatement.setBoolean(10, busJourney.isThursday());
            preparedStatement.setBoolean(11, busJourney.isFriday());
            preparedStatement.setBoolean(12, busJourney.isSaturday());
            preparedStatement.setBoolean(13, busJourney.isSunday());
            preparedStatement.setInt(14, busJourney.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();
    }


    private static void openConnection() {
        try {
                                                       // jdbc:mysql://[host][:port]/[database]?characterEncoding=UTF-8
            connection = DriverManager.getConnection("jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "?characterEncoding=UTF-8", DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            connection = null;
        }
    }

    private static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


