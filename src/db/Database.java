package db;

//~--- non-JDK imports --------------------------------------------------------

import crawler.Wrapper;
import static java.lang.System.out;

import productTypes.GraphicsCard;
import productTypes.Laptop;
import productTypes.MobilePhone;
import productTypes.Motherboard;

//~--- JDK imports ------------------------------------------------------------

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

/**
 *
 * @author d
 */
public class Database extends Wrapper {

    // query to insert data to skrouzt
    private static final String query = " INSERT INTO skrouzt(url,name,price,rating,specs,sk_category)"
                                        + " values (?, ?, ?, ?, ?, ?)";

    // select all rows from table skrouzt
    private static final String allRow = "SELECT COUNT(*) FROM skrouzt";

    //method to  establishing connection with database
    protected static Connection getConnection() throws Exception {
        String driver   = "org.gjt.mm.mysql.Driver";
        String url      = "jdbc:mysql://83.212.124.175:3306/zadmin_java?useUnicode=yes&characterEncoding=UTF-8";
        String username = "java";
        String password = "your password";

        Class.forName(driver);    // load MySQL driver

        Connection conn = DriverManager.getConnection(url, username, password);

        return conn;
    }
    
    //method to delete all data from skrouzt table
    public void deleteAllDataFromSkrouzt() throws SQLException{
        //query
        String deleteDataQuery = "truncate skrouzt";
        Connection        conn     = null;
        PreparedStatement deleteSkrouztData      = null;
        
        try {
            conn = getConnection();
            System.out.println("Connected database successfully...");
            System.out.println("Please wait...");
            System.out.println("Deleting all data from skrouzt table");
            deleteSkrouztData = conn.prepareStatement(deleteDataQuery);
            deleteSkrouztData.executeQuery();
           
           conn.close();
           System.out.println("Done...");
            
        } catch (Exception ex) {
            out.print(ex);
            
        } 
    }
    // counts all rows inseted in skrouzt table
    protected static int countRows(Connection conn) throws SQLException {

        // select the number of rows in the table
        PreparedStatement stmt     = null;
        ResultSet         rs       = null;
        int               rowCount = -1;

        try {
            stmt = conn.prepareStatement(allRow);
            rs   = stmt.executeQuery(allRow);

            // get the number of rows from the result set
            rs.next();
            rowCount = rs.getInt(1);
        } finally {
            rs.close();
            stmt.close();
        }

        return rowCount;
    }

    // method  insert Mobile Phones in db
    public void insertPhonesToDb() throws SQLException {
        Connection conn = null;

        try {
            conn = getConnection();
            System.out.println("Connected database successfully...");
            System.out.println("Please wait...");

            PreparedStatement updateSkrouztPs = conn.prepareStatement(query);

            // get phones  from skrouzt
            ArrayList<MobilePhone> skroutzPhones = getPhonesData();

            // insert phones in db
            for (int x = 0; x < skroutzPhones.size(); x++) {

                // in sk_category column insert the name phone for every phone product
                String sk_category = "phone";

                updateSkrouztPs.setString(1, skroutzPhones.get(x).getUrl());
                updateSkrouztPs.setString(2, skroutzPhones.get(x).getName());
                updateSkrouztPs.setBigDecimal(3, skroutzPhones.get(x).getPrice());
                updateSkrouztPs.setDouble(4, skroutzPhones.get(x).getRating());
                updateSkrouztPs.setString(5, skroutzPhones.get(x).getSpecs());
                updateSkrouztPs.setString(6, sk_category);
                updateSkrouztPs.executeUpdate();
            }


            // print the number of rows with products
            System.out.println("The table has: " + countRows(conn) + " inserts");

            // close the connection
            conn.close();
            System.out.println("Done!!");
        } catch (SQLException se) {

            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }
    
    //method to insert  laptops in db
    public void insertLaptopsToDb() throws SQLException {
        Connection conn = null;

        try {
            conn = getConnection();
            System.out.println("Connected database successfully...");
            System.out.println("Please wait...");

            PreparedStatement updateSkrouztPs = conn.prepareStatement(query);

            // get laptops from skrouzt
            ArrayList<Laptop> skroutzLaptops = getLaptopsData();

            // insert laptops  in db
            for (int x = 0; x < skroutzLaptops.size(); x++) {

                // in sk_category column insert the name laptop for every laptop product
                String sk_category = "laptop";

                updateSkrouztPs.setString(1, skroutzLaptops.get(x).getUrl());
                updateSkrouztPs.setString(2, skroutzLaptops.get(x).getName());
                updateSkrouztPs.setBigDecimal(3, skroutzLaptops.get(x).getPrice());
                updateSkrouztPs.setDouble(4, skroutzLaptops.get(x).getRating());
                updateSkrouztPs.setString(5, skroutzLaptops.get(x).getSpecs());
                updateSkrouztPs.setString(6, sk_category);
                updateSkrouztPs.executeUpdate();
            }
           

            // print the number of rows for all products
            System.out.println("The table has: " + countRows(conn) + " inserts");

            // close the connection
            conn.close();
            System.out.println("Done!!");
        } catch (SQLException se) {

            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    //method to insert  graphics card in db
    public void insertGraphicsCardsToDb() throws SQLException {
        Connection conn = null;

        try {
            conn = getConnection();
            System.out.println("Connected database successfully...");
            System.out.println("Please wait...");

            PreparedStatement updateSkrouztPs = conn.prepareStatement(query);

            // get graphics cards  from skrouzt
            ArrayList<GraphicsCard> skroutzGraphicsCards = getGraphicsCardsData();

            // insert graphics cards  in db
            for (int x = 0; x < skroutzGraphicsCards.size(); x++) {

                // in sk_category column insert the name graphics_card for every card product
                String sk_category = "graphics_card";

                updateSkrouztPs.setString(1, skroutzGraphicsCards.get(x).getUrl());
                updateSkrouztPs.setString(2, skroutzGraphicsCards.get(x).getName());
                updateSkrouztPs.setBigDecimal(3, skroutzGraphicsCards.get(x).getPrice());
                updateSkrouztPs.setDouble(4, skroutzGraphicsCards.get(x).getRating());
                updateSkrouztPs.setString(5, skroutzGraphicsCards.get(x).getSpecs());
                updateSkrouztPs.setString(6, sk_category);
                updateSkrouztPs.executeUpdate();
            }


            // print the number of rows for all products in db
            System.out.println("The table has: " + countRows(conn) + " inserts");

            // close the connection
            conn.close();
            System.out.println("Done!!");
        } catch (SQLException se) {

            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }
    
    //method to insert motherboards in db
    public void insertMotherboardsToDb() throws SQLException {
        Connection conn = null;

        try {
            conn = getConnection();
            System.out.println("Connected database successfully...");
            System.out.println("Please wait...");

            PreparedStatement updateSkrouztPs = conn.prepareStatement(query);

            // get motherboards  from skrouzt
            ArrayList<Motherboard> skroutzMotherboards = getMotherboardsData();

            // insert motherboards in db
            for (int x = 0; x < skroutzMotherboards.size(); x++) {

                // in sk_category column insert the name motherboards for every motherboards product
                String sk_category = "motherboard";

                updateSkrouztPs.setString(1, skroutzMotherboards.get(x).getUrl());
                updateSkrouztPs.setString(2, skroutzMotherboards.get(x).getName());
                updateSkrouztPs.setBigDecimal(3, skroutzMotherboards.get(x).getPrice());
                updateSkrouztPs.setDouble(4, skroutzMotherboards.get(x).getRating());
                updateSkrouztPs.setString(5, skroutzMotherboards.get(x).getSpecs());
                updateSkrouztPs.setString(6, sk_category);
                updateSkrouztPs.executeUpdate();
            }


            // print the number of rows for all products in db
            System.out.println("The table has: " + countRows(conn) + " inserts");

            // close the connection
            conn.close();
            System.out.println("Done!!");
        } catch (SQLException se) {

            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }
    
    //get all phones from db 
    public static ArrayList<MobilePhone> getPhonesFromDb() throws Exception {
        Connection conn = null;

        // arraylist to store products  from db
        ArrayList<MobilePhone> products = new ArrayList<>();

        try {
            conn = getConnection();
            System.out.println("Connected database successfully...");
            System.out.println("Please wait...");

            // query to get phones  products from db
            String            myquery   = "SELECT * FROM skrouzt WHERE sk_category='phone'";
            PreparedStatement skrouztPs = conn.prepareStatement(myquery);
            ResultSet         resultSet = skrouztPs.executeQuery(myquery);

            while (resultSet.next()) {
                MobilePhone product = new MobilePhone();

                // get data from columns and save to arraylist
                product.setUrl(resultSet.getString("url"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getBigDecimal("price"));
                product.setRating(resultSet.getDouble("rating"));
                product.setSpecs(resultSet.getString("specs"));

                // add to arraylist
                products.add(product);
            }

            // close the connection
            conn.close();
            System.out.println("Done!!");
        } catch (SQLException se) {

            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

        return products;
    }
    
    
    //get all laptops from db
    public static ArrayList<Laptop> getLaptopsFromDb() throws Exception {
        Connection conn = null;

        // arraylist to store products  from db
        ArrayList<Laptop> products = new ArrayList<>();

        try {
            conn = getConnection();
            System.out.println("Connected database successfully...");
            System.out.println("Please wait...");

            // query to get alll laptops  by category
            String            myquery   = "SELECT * FROM skrouzt WHERE sk_category='laptop'";
            PreparedStatement skrouztPs = conn.prepareStatement(myquery);
            ResultSet         resultSet = skrouztPs.executeQuery(myquery);

            while (resultSet.next()) {
                Laptop product = new Laptop();

                // get data from columns and save to arraylist
                product.setUrl(resultSet.getString("url"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getBigDecimal("price"));
                product.setRating(resultSet.getDouble("rating"));
                product.setSpecs(resultSet.getString("specs"));

                // add to arraylist
                products.add(product);
            }

            // close the connection
            conn.close();
            System.out.println("Done!!");
        } catch (SQLException se) {

            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

        return products;
    }
    //get all graphics from db
    public static ArrayList<GraphicsCard> getGraphicsCardsFromDb() throws Exception {
        Connection conn = null;

        // arraylist to store products  from db
        ArrayList<GraphicsCard> products = new ArrayList<>();

        try {
            conn = getConnection();
            System.out.println("Connected database successfully...");
            System.out.println("Please wait...");

            // query to get all graphics cards 
            String            myquery   = "SELECT * FROM skrouzt WHERE sk_category='graphics_card'";
            PreparedStatement skrouztPs = conn.prepareStatement(myquery);
            ResultSet         resultSet = skrouztPs.executeQuery(myquery);

            while (resultSet.next()) {
                GraphicsCard product = new GraphicsCard();

                // get data from columns and save to arraylist
                product.setUrl(resultSet.getString("url"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getBigDecimal("price"));
                product.setRating(resultSet.getDouble("rating"));
                product.setSpecs(resultSet.getString("specs"));

                // add to arraylist
                products.add(product);
            }

            // close the connection
            conn.close();
            System.out.println("Done!!");
        } catch (SQLException se) {

            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

        return products;
    }
    //get all motherboards from db
    public static ArrayList<Motherboard> getMotherBoardsFromDb() throws Exception {
        Connection conn = null;

        // arraylist to store products  from db
        ArrayList<Motherboard> products = new ArrayList<>();

        try {
            conn = getConnection();
            System.out.println("Connected database successfully...");
            System.out.println("Please wait...");

            // query to get all motherboards from db
            String            myquery   = "SELECT * FROM skrouzt WHERE sk_category='motherboard'";
            PreparedStatement skrouztPs = conn.prepareStatement(myquery);
            ResultSet         resultSet = skrouztPs.executeQuery(myquery);

            while (resultSet.next()) {
                Motherboard product = new Motherboard();

                // get data from columns and save to arraylist
                product.setUrl(resultSet.getString("url"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getBigDecimal("price"));
                product.setRating(resultSet.getDouble("rating"));
                product.setSpecs(resultSet.getString("specs"));

                // add to arraylist
                products.add(product);
            }

            // close the connection
            conn.close();
            System.out.println("Done!!");
        } catch (SQLException se) {

            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

        return products;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
