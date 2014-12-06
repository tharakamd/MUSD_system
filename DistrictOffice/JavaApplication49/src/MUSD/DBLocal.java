package MUSD;

// importing packages
import java.awt.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.log4j.*;

public class DBLocal {

    private static Logger logger = Logger.getLogger(DBLocal.class.getName());
    // global variables for database
    private String JDBC_DRIVER = new String("com.mysql.jdbc.Driver");
    private String DB_URL = new String("jdbc:mysql://localhost/musd");
    // private String DB_URL = new String("jdbc:mysql://db4free.net:3306/musd");
    private Connection conn = null;

    /*
     * this will execute a sql statement
     * this gives the sql resultset of the statement
     */
    private ResultSet executeStatement(String sql) {
        // creating objects
        Statement statement = null;
        ResultSet resultSet = null;

        // list to store the final result
        ArrayList<String> result = new ArrayList<String>();

        try {
            // creating the statement
            statement = conn.createStatement();

            // extract data from resultSet
            resultSet = statement.executeQuery(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return resultSet;

    }

    /*
     * this extracts the data in the resultset
     * and returns an arraylist of data
     */
    private ArrayList<String> exractData(ResultSet resultSet, String column) {

        // list to store the final result
        ArrayList<String> result = new ArrayList<String>();

        try {
            while (resultSet.next()) {

                // adding the data in to the array list
                result.add((String) resultSet.getString(column));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // closing the resultSet
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return result;

    }

    /*
     * this connects the database
     * returns true if success
     * returns false if fail
     */
    public boolean connectLocal() {
		// variables for user

        final String userName = "root";
      //  final String userName = "root";
       final String password = "";
     //   final String password = "";
        logger.log(Level.INFO, "trying to log in to the database un = " + userName + " pw = " + password);
        // trying to connect to database
        try {
            // register jdbc drive
            Class.forName("com.mysql.jdbc.Driver");
            // connecting to database
            conn = DriverManager.getConnection(DB_URL, userName, password);
            logger.log(Level.INFO, "logged in to the localDatabase");
        } catch (SQLException e) {

            System.out.println("Errorrrrrr");
                       // logger.log(Level.ERROR," SQLException in connecting the database");
            // if exception found return false
            return false;
        } catch (ClassNotFoundException e) {
            // System.out.println("error");
            logger.log(Level.ERROR, " ClassNotFoundException in connecting the database");
            // if exception found return false
            return false;
        }

        // if everything went perfect return true
        return true;
    }

    public boolean disconnectLocal() {
        try {
            conn.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public ArrayList selectLocal(String table, String column, String where) {
        // creating the sql statement
        String sql = "select " + column + " from " + table + " where " + where;

        if (where == null) {
            sql = "select " + column + " from " + table;

        }

        System.out.println(sql);
        // creating objects
        ResultSet resultSet = null;

        // list to store the final result
        ArrayList<String> result = new ArrayList<String>();

        // get the resultset
        resultSet = executeStatement(sql);

        // get the data array
        result = exractData(resultSet, column);

        return result;
    }

    public ArrayList selectLocal(String table, String column, String where,
            String groupBy, String orderBy) {
        // creating the sql statement
        String sql = "select " + column + " from " + table + " where " + where
                + " group by " + groupBy + " order by " + orderBy;

        // creating objects
        ResultSet resultSet = null;

        // list to store the final result
        ArrayList<String> result = new ArrayList<String>();

        // get the resultset
        resultSet = executeStatement(sql);

        // get the data array
        result = exractData(resultSet, column);

        return result;
    }

    public boolean addLocal(String tableName, Table table) {
        StringBuilder columns = new StringBuilder();
        StringBuilder value = new StringBuilder();
        ArrayList<Recode> tableTypes = table.getTable();
        Iterator<Recode> it = tableTypes.iterator();
        boolean first = true;
        while (it.hasNext()) {
            if (!first) {
                columns.append(",");
                value.append(",");
            } else {
                first = false;
            }
            Recode recode = it.next();
            columns.append(recode.getColumn());
            value.append(getValue(recode));

        }
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ");
        sql.append(tableName + " (");
        sql.append(columns.toString() + " ) VALUES ( ");
        sql.append(value.toString() + " )");
        return executeLocal(sql.toString());

    }

    public String getValue(Recode recode) {
        int type = recode.getType();
        switch (type) {
            case 0:
                return String.valueOf(recode.getValInt());
            case 1:
            //String.valueOf(recode.getv);break;
            case 2:
                return String.valueOf(recode.getValDouble());
            default:
                return "'" + String.valueOf(recode.getValString()) + "'";

        }

    }

    public boolean executeLocal(String sql) {
        System.out.println(sql);
        // creating objects
        boolean result = false;
        Statement statement = null;
        try {

            statement = conn.createStatement();
            statement.execute(sql);
            return true;

        } catch (Exception e) {
            System.out.println("sql statement executing error");
            return false;
        }

    }

    public void createInspectorTable(String id) {
        String sql = "CREATE TABLE IF NOT EXISTS  `" + id + "`" + "(\n"
                + "  `Index` int(11) NOT NULL AUTO_INCREMENT,\n"
                + "  `Date` date NOT NULL,\n"
                + "  `Stamping_Center` text NOT NULL,\n"
                + "  `Details_of_the_circuit_stamping` text NOT NULL,\n"
                + "  `ms2No` varchar(100) NOT NULL,\n"
                + "  `Issued_varification_No_From` text NOT NULL,\n"
                + "  `Issued_varification_No_To` text NOT NULL,\n"
                + "  `Cancelled_varification_No` text NOT NULL,\n"
                + "  `No_of_Traders` int(11) NOT NULL,\n"
                + "  `No_of_Manufacturers` int(11) NOT NULL,\n"
                + "  `Amount` double NOT NULL,\n"
                + "  `Date_of_money_deposit` date DEFAULT NULL,\n"
                + "  `Branch` text,\n"
                + "  `Bill_No` varchar(100) NOT NULL,\n"
                + "  `Deposit_Amount` double DEFAULT NULL,\n"
                + "  `assistant` varchar(200) NOT NULL,\n"
                + "  `Equipment_ID_1` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_2` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_3` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_4` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_5` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_6` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_7` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_8` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_9` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_10` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_11` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_12` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_13` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_14` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_15` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_16` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_17` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_18` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_19` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_20` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_21` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_22` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_23` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_24` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_25` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_26` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_27` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_28` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_29` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_30` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_31` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_32` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_33` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_34` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_35` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_36` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_37` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_38` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_39` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_40` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_41` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_42` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_43` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_44` int(11) DEFAULT NULL,\n"
                + "  `Equipment_ID_45` int(11) DEFAULT NULL,\n"
                + "  PRIMARY KEY (`Date`),\n"
                + "  KEY `Index` (`Index`)\n"
                + ") ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;";

        this.executeLocal(sql);

    }

}
