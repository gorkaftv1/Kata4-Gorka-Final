package software.ulpgc.arquitecture.io;

import software.ulpgc.arquitecture.model.Wood;

import java.io.File;
import java.sql.*;
import java.util.List;

import static java.sql.Types.FLOAT;
import static java.sql.Types.NVARCHAR;

public class WoodDatabaseWriter implements WoodWriter{
    private final Connection connection;
    private final PreparedStatement insertWoodStatement;

    public WoodDatabaseWriter(String connection) throws SQLException {
        this(DriverManager.getConnection(connection));
    }

    public WoodDatabaseWriter(Connection connection) throws SQLException {
        this.connection = connection;
        this.connection.setAutoCommit(false);
        this.createTables();
        this.insertWoodStatement = createInsertStatement();
    }

    private final static String insertStatement = """
            INSERT INTO woods (woodId, name, continent, quality, tone, country, price, exporters)
            VALUES (?,?,?,?,?,?,?,?)
            """;
    private PreparedStatement createInsertStatement() throws SQLException {
        return connection.prepareStatement(insertStatement);
    }

    private final static String createStatement = """
            CREATE TABLE IF NOT EXISTS woods (
            woodId TEXT PRIMARY KEY,
            name TEXT,
            continent TEXT,
            quality TEXT,
            tone TEXT,
            country TEXT,
            price FLOAT,
            exporters TEXT);
            """;

    private final static String truncate = "DELETE FROM woods;";
    private void createTables() throws SQLException {
        connection.createStatement().execute(createStatement);
        connection.createStatement().execute(truncate);
    }

    public static WoodDatabaseWriter open(File file) throws SQLException {
        return new WoodDatabaseWriter("jdbc:sqlite:" + file.getAbsolutePath());
    }

    private record Parameter(int index, Object value, int type){}

    @Override
    public void write(Wood wood) {
        try {
            insertWoodStatementFor(wood).execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PreparedStatement insertWoodStatementFor(Wood wood) throws SQLException {
        insertWoodStatement.clearParameters();
        for (Parameter parameter : parametersOf(wood)) define(parameter);
        return insertWoodStatement;
    }

    private void define(Parameter parameter) throws SQLException {
        if (parameter.value == null) insertWoodStatement.setNull(parameter.index,parameter.type);
        insertWoodStatement.setObject(parameter.index, parameter.value);
    }

    private List<Parameter> parametersOf(Wood wood) {
        return List.of(
                new Parameter(1, wood.id(), NVARCHAR),
                new Parameter(2, wood.name(), NVARCHAR),
                new Parameter(3, wood.continent(), NVARCHAR),
                new Parameter(4, wood.tone(), NVARCHAR),
                new Parameter(5, wood.country(), NVARCHAR),
                new Parameter(6, wood.quality(), NVARCHAR),
                new Parameter(7, wood.pricePerSquareMeters(), FLOAT),
                new Parameter(8, wood.exportCountry(), NVARCHAR)
        );
    }

    @Override
    public void close() throws Exception {
        connection.commit();
        connection.close();
    }
}
