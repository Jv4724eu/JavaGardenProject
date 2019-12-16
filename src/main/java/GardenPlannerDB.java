import java.sql.*;

public class GardenPlannerDB {
    public static void main(String[] args) throws SQLException {


        //creation of GardenPlotSaves table in database that holds saved garden plots and their name
        try (Connection connection = DriverManager.getConnection(DBConfig.url); //database location
             Statement statement = connection.createStatement()) {
            String createTableSavedGardens = "CREATE TABLE IF NOT EXISTS savedGardens (name TEXT unique, garden BLOB)";
            statement.execute(createTableSavedGardens);
        } catch (SQLException sqle) {
            System.err.println("Error creating inventory table because" + sqle);
        }
    }

    public static void addSavedGarden(String name, Blob garden){
        String addSavedGarden = "INSERT INTO saveGardens(name, garden) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(DBConfig.url);
             PreparedStatement preparedStatement = connection.prepareStatement(addSavedGarden)) {

            preparedStatement.setString(1, name);
            preparedStatement.setBlob(2, garden);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void showAllSavedGardens(){
        //will need return value, array list?
    }

    public void openSavedGarden(){
    }


}
