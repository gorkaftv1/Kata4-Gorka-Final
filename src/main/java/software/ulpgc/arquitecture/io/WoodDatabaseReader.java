package software.ulpgc.arquitecture.io;

import software.ulpgc.arquitecture.model.Wood;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.Optional;

public class WoodDatabaseReader implements WoodReader{
    private final Connection connection;
    private final PreparedStatement select;

    public WoodDatabaseReader(String connection) throws SQLException {
        this(DriverManager.getConnection(connection));
    }

    public WoodDatabaseReader(Connection connection) throws SQLException {
        this.connection = connection;
        String sqliteSelectRandom = "SELECT * FROM woods ORDER BY RANDOM()";
        this.select = this.connection.prepareStatement(sqliteSelectRandom);
    }

    public static WoodDatabaseReader open(File file) throws SQLException {
        return new WoodDatabaseReader("jdbc:sqlite:" + file.getAbsolutePath());
    }

    @Override
    public Optional<Wood> read() {
        try {
            return Optional.of(getWoodOf(select.executeQuery()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Wood getWoodOf(ResultSet resultSet) throws SQLException {
        String line = stringOf(resultSet);
        Deserializer deserializer = line1 -> {
            String[] fields1 = line1.split("\\|");
            return new Wood(
                    fields1[0],
                    fields1[1],
                    Wood.Continent.valueOf(fields1[2]),
                    Wood.ToneColor.valueOf(fields1[3]),
                    Wood.Country.valueOf(fields1[4]),
                    Wood.Quality.valueOf(fields1[5]),
                    Float.valueOf(fields1[6]),
                    TsvWoodDeserializer.getExportCountries(fields1[7])
            );
        };
        return deserializer.deserialize(line);
    }

    private String stringOf(ResultSet resultSet) throws SQLException {
        String id= resultSet.getString("woodId");
        String name= resultSet.getString("name");
        String continent= resultSet.getString("continent");
        String tone= resultSet.getString("tone");
        String country= resultSet.getString("country");
        String quality= resultSet.getString("quality");
        String price= resultSet.getString("price");
        String export= resultSet.getString("exporters").replace("[", "").replace("]", "");
        return String.join("|", id, name, continent, quality, tone, country, price, export);    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
