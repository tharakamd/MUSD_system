/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HeadOffice;

import java.awt.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.util.ArrayList;

public class DBLocal {

    // global variables for database
    private String JDBC_DRIVER = new String("com.mysql.jdbc.Driver");
    private String DB_URL = new String("jdbc:mysql://localhost/musd");
 //   private String DB_URL = new String("jdbc:mysql://db4free.net:3306/musd");
  //  String username = "root";
   // String passward = "";
    String username = "root";
     String passward = "";
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
       // private String DB_URL = new String("jdbc:mysql://db4free.net:3306/musd");

	//private Connection conn = null;

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

     //   final String userName = "root";
     //   final String password = "";

               final String userName = "root";
        	final String password = "";
        // trying to connect to database
        try {
            // register jdbc drive
            Class.forName("com.mysql.jdbc.Driver");
            // connecting to database
            conn = DriverManager.getConnection(DB_URL, userName, password);
        } catch (SQLException e) {

            // if exception found return false
            return false;
        } catch (ClassNotFoundException e) {
                   // System.out.println("error");
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
                + "  `No_of_Traders` int(11) DEFAULT NULL,\n"
                + "  `No_of_Manufacturers` int(11) DEFAULT NULL,\n"
                + "  `Amount` double DEFAULT NULL,\n"
                + "  `Date_of_money_deposit` date DEFAULT NULL,\n"
                + "  `Branch` text,\n"
                + "  `Bill_No` varchar(100) DEFAULT NULL,\n"
                + "  `Deposit_Amount` double DEFAULT NULL,\n"
                + "  `assistant` varchar(200) NOT NULL,\n"
                + "  `EQ001` int(11) DEFAULT NULL,\n"
                + "  `EQ002` int(11) DEFAULT NULL,\n"
                + "  `EQ003` int(11) DEFAULT NULL,\n"
                + "  `EQ004` int(11) DEFAULT NULL,\n"
                + "  `EQ005` int(11) DEFAULT NULL,\n"
                + "  `EQ006` int(11) DEFAULT NULL,\n"
                + "  `EQ007` int(11) DEFAULT NULL,\n"
                + "  `EQ008` int(11) DEFAULT NULL,\n"
                + "  `EQ009` int(11) DEFAULT NULL,\n"
                + "  `EQ010` int(11) DEFAULT NULL,\n"
                + "  `EQ011` int(11) DEFAULT NULL,\n"
                + "  `EQ012` int(11) DEFAULT NULL,\n"
                + "  `EQ013` int(11) DEFAULT NULL,\n"
                + "  `EQ014` int(11) DEFAULT NULL,\n"
                + "  `EQ015` int(11) DEFAULT NULL,\n"
                + "  `EQ016` int(11) DEFAULT NULL,\n"
                + "  `EQ017` int(11) DEFAULT NULL,\n"
                + "  `EQ018` int(11) DEFAULT NULL,\n"
                + "  `EQ019` int(11) DEFAULT NULL,\n"
                + "  `EQ020` int(11) DEFAULT NULL,\n"
                + "  `EQ021` int(11) DEFAULT NULL,\n"
                + "  `EQ022` int(11) DEFAULT NULL,\n"
                + "  `EQ023` int(11) DEFAULT NULL,\n"
                + "  `EQ024` int(11) DEFAULT NULL,\n"
                + "  `EQ025` int(11) DEFAULT NULL,\n"
                + "  `EQ026` int(11) DEFAULT NULL,\n"
                + "  `EQ027` int(11) DEFAULT NULL,\n"
                + "  `EQ028` int(11) DEFAULT NULL,\n"
                + "  `EQ029` int(11) DEFAULT NULL,\n"
                + "  `EQ030` int(11) DEFAULT NULL,\n"
                + "  `EQ031` int(11) DEFAULT NULL,\n"
                + "  `EQ032` int(11) DEFAULT NULL,\n"
                + "  `EQ033` int(11) DEFAULT NULL,\n"
                + "  `EQ034` int(11) DEFAULT NULL,\n"
                + "  `EQ035` int(11) DEFAULT NULL,\n"
                + "  `EQ036` int(11) DEFAULT NULL,\n"
                + "  `EQ037` int(11) DEFAULT NULL,\n"
                + "  `EQ038` int(11) DEFAULT NULL,\n"
                + "  `EQ039` int(11) DEFAULT NULL,\n"
                + "  `EQ040` int(11) DEFAULT NULL,\n"
                + "  `EQ041` int(11) DEFAULT NULL,\n"
                + "  `EQ042` int(11) DEFAULT NULL,\n"
                + "  `EQ043` int(11) DEFAULT NULL,\n"
                + "  `EQ044` int(11) DEFAULT NULL,\n"
                + "  `EQ045` int(11) DEFAULT NULL,\n"
                + "  PRIMARY KEY (`Date`),\n"
                + "  KEY `Index` (`Index`)\n"
                + ") ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ";
        System.out.println(sql);
        this.executeLocal(sql);

    }

    boolean addInspector(Inspector em) {
        try {
            conn = (Connection) DriverManager.getConnection(DB_URL, username, passward);
            String query = "INSERT INTO inspectordetails values(?,?,?,?,?,?,?,?,?,?,?,?)";
            pst = (PreparedStatement) conn.prepareStatement(query);

            pst.setInt(1, em.getRegId());
            pst.setString(2, em.getFirstname());
            pst.setString(3, em.getLastname());
            pst.setInt(4, em.getAge());
            pst.setString(5, em.getAddress());
            pst.setString(6, em.getGender());
            pst.setInt(7, em.getYearOfReg());
            pst.setString(8, em.getDistrict());
            pst.setString(9, em.getGrade());
            pst.setString(10, em.getUserName());
            pst.setString(11, em.getPassword());
            System.out.println(em.getUserName());
            pst.setString(12, em.getConfirmPassword());

            pst.executeUpdate();

            return true;

        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
            }
        }

    }

    ArrayList<Inspector> getInspector() {
        try {
            ArrayList<Inspector> list = new ArrayList<Inspector>();
            conn = (Connection) DriverManager.getConnection(DB_URL, username, passward);
            String query = "SELECT * FROM inspectordetails";
            pst = (PreparedStatement) conn.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                Inspector i = new Inspector();
                i.setRegId(rs.getInt(1));
                i.setFirstname(rs.getString(2));
                i.setLastname(rs.getString(3));
                i.setAge(rs.getInt(4));
                i.setAddress(rs.getString(5));
                i.setGender(rs.getString(6));
                i.setYearOfReg(rs.getInt(7));
                i.setDistrict(rs.getString(8));
                i.setGrade(rs.getString(9));
                i.setUserName(rs.getString(10));
                i.setPassword(rs.getString(11));
                list.add(i);

            }
            return list;

        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
            }
        }
    }

    int checkAccount(String usrname, String password) {
        try {
            conn = (Connection) DriverManager.getConnection(DB_URL, username, passward);
            String query = "SELECT username,password FROM inspectordetails";
            pst = (PreparedStatement) conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                if (usrname.equals(rs.getString(1)) && password.equals(rs.getString(2))) {
                    return 0;
                }
            }
            return 1;

        } catch (Exception e) {
            System.out.println(e);
            return 2;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    int checkUsername(String usrname) {

        try {
            conn = (Connection) DriverManager.getConnection(DB_URL, username, passward);
            String query = "SELECT username FROM inspectordetails";
            pst = (PreparedStatement) conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                if (usrname.equals(rs.getString(1))) {
                    return 0;
                }
            }
            return 1;

        } catch (Exception e) {
            System.out.println(e);
            return 2;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public boolean updateInspector(Inspector in) {
        try {
            conn = (Connection) DriverManager.getConnection(DB_URL, username, passward);
            String query = "UPDATE inspectordetails SET firstname='" + in.getFirstname() + "',lastname='" + in.getLastname() + "',age=" + in.getAge() + ",address='" + in.getAddress() + "',gender='" + in.getGender() + "',yearofreg=" + in.getYearOfReg() + ",disrtict='" + in.getDistrict() + "',grade='" + in.getGrade() + "' WHERE regid=" + in.getRegId();
            pst = (PreparedStatement) conn.prepareStatement(query);
            pst.executeUpdate();

            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public boolean deleteInspector(Inspector in) {
        try {
            conn = (Connection) DriverManager.getConnection(DB_URL, username, passward);
            String query = "DELETE FROM inspectordetails WHERE regid=" + in.getRegId();
            pst = (PreparedStatement) conn.prepareStatement(query);
            pst.executeUpdate();

            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }
}
