import java.sql.*;
import java.util.Vector;

public class GardenPlannerDB {
    public static void main(String[] args) throws SQLException {


        //creation of GardenPlotSaves table in database that holds saved garden plots and their name
        try (Connection connection = DriverManager.getConnection(DBConfig.url); //database location
             Statement statement = connection.createStatement()) {
            String createTableSavedGardens = "CREATE TABLE IF NOT EXISTS Plants (name TEXT unique)";
            statement.execute(createTableSavedGardens);
        } catch (SQLException sqle) {
            System.err.println("Error creating savedGarden table because" + sqle);
        }
    }



    public static void createSavedGarden(String gardenName, String[][] gardenData){ //create arrray list of data
        try (Connection connection = DriverManager.getConnection(DBConfig.url); //database location
             PreparedStatement ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS ? (Column0 TEXT)")) {
            ps.setString(1,gardenName);
            ps.execute();


            for(int i=1; i<gardenData[0].length; i++){
                String alterTable = "ALTER TABLE " + gardenName + " ADD Column"+i+" TEXT";
                statement.executeUpdate(alterTable);


            }
            for (int r = 0; r < gardenData.length; r++) {
                String rowData="";
                for (int c = 0; c < gardenData[r].length; c++) {
                    String rowValue = "'" + gardenData[r][c] + "',";
                    rowData = rowData.concat(rowValue);
                }
                String insertData ="INSERT INTO " + gardenName + " VALUES (" + rowData +")";
                //statement.executeUpdate(insertData);
                System.out.println(insertData);
            }


        } catch (SQLException sqle) {
            System.err.println("Error" + sqle);
        }
    }

    public void showAllSavedGardens(){
        //will need return value, array list?
    }

    public void openSavedGarden(){
    }




}
