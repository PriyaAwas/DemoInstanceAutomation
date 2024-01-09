package sew.ai.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sew.ai.config.DataBaseModel;
import sew.ai.config.DatabaseConfiguration;
import sew.ai.helpers.props.SQLQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataBaseUtils {
    private static Logger log = LogManager.getLogger(DataBaseUtils.class);
    public static Connection connection;
    public static Connection chaseConn;
    public static Connection paypalConn;
    public static Connection postgresqlConn;

    public static void main(String[] args) {
        try {
            DataBaseUtils.callProcedureToDeleteUser(7486);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getBaseDatabaseConnection() {
        try {
            String baseDatabaseURI;
            DataBaseModel dataBaseModel = DatabaseConfiguration.getDatabaseDrivers("base_db");
            baseDatabaseURI = dataBaseModel.getDriver() + "//" + dataBaseModel.getHost() +
                    ";database=" + dataBaseModel.getDatabase() +
                    ";user=" + dataBaseModel.getUser() +
                    ";password=" + dataBaseModel.getPassword() +
                    ";ecrypt=" + dataBaseModel.getEncrypt() +
                    ";trustServerCertificate=" + dataBaseModel.getTrustServerCertificate();
            connection = DriverManager.getConnection(baseDatabaseURI);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeBaseDatabaseConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getPayPalDatabaseConnection() {
        try {
            String paypalDatabaseURI;
            DataBaseModel dataBaseModel = DatabaseConfiguration.getDatabaseDrivers("paypal_db");
            paypalDatabaseURI = dataBaseModel.getDriver() + "//" + dataBaseModel.getHost() +
                    ";database=" + dataBaseModel.getDatabase() +
                    ";user=" + dataBaseModel.getUser() +
                    ";password=" + dataBaseModel.getPassword() +
                    ";ecrypt=" + dataBaseModel.getEncrypt() +
                    ";trustServerCertificate=" + dataBaseModel.getTrustServerCertificate();
            paypalConn = DriverManager.getConnection(paypalDatabaseURI);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paypalConn;
    }

    public static void closePaypalDatabaseConnection() {
        try {
            paypalConn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getChaseDatabaseConnection() {
        try {
            String chaseDatabaseURI;
            DataBaseModel dataBaseModel = DatabaseConfiguration.getDatabaseDrivers("chase_db");
            chaseDatabaseURI = dataBaseModel.getDriver() + "//" + dataBaseModel.getHost() +
                    ";database=" + dataBaseModel.getDatabase() +
                    ";user=" + dataBaseModel.getUser() +
                    ";password=" + dataBaseModel.getPassword() +
                    ";ecrypt=" + dataBaseModel.getEncrypt() +
                    ";trustServerCertificate=" + dataBaseModel.getTrustServerCertificate();
            chaseConn = DriverManager.getConnection(chaseDatabaseURI);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chaseConn;
    }

    public static void closeChaseDatabaseConnection() {
        try {
            chaseConn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getPostGreSqlConnection() {
        String postgresqlDatabaseURI;
        try {
            DataBaseModel dataBaseModel = DatabaseConfiguration.getDatabaseDrivers("postgresql_db");
            postgresqlDatabaseURI = dataBaseModel.getDriver() + "//" + dataBaseModel.getHost() +
                    "/" + dataBaseModel.getDatabase();
            postgresqlConn = DriverManager.getConnection(postgresqlDatabaseURI, dataBaseModel.getUser(),
                    dataBaseModel.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return postgresqlConn;
    }

    public static void closePostGreSqlConnection() {
        try {
            postgresqlConn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void tearDownDatabaseConnection() {
        try {
            connection.close();
            chaseConn.close();
            paypalConn.close();
        } catch (SQLException e) {
            log.info(e.getMessage());
        }
    }

    /**
     * This method is used to get the result set by executing the given
     * query.
     *
     * @param query
     * @return
     * @throws Exception
     */
    public static ResultSet getResultSet(String query) {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            Thread.sleep(500);
            resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    /**
     * This method is used to get the result set by executing the given
     * query.
     *
     * @param query
     * @return
     * @throws Exception
     */
    public static ResultSet getResultSetPayPalDB(String query) {
        ResultSet resultSet = null;
        try {
            Statement statement = paypalConn.createStatement();
            Thread.sleep(500);
            resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    /**
     * This method is used to get the result set by executing the given
     * query.
     *
     * @param query
     * @return
     * @throws Exception
     */
    public static ResultSet getResultSetOnPostGreSQL(String query) {
        ResultSet resultSet = null;
        try {
            Statement statement = postgresqlConn.createStatement();
            Thread.sleep(500);
            resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    /**
     * This Method is used to get the result from multiple result set
     *
     * @param query
     * @return
     * @throws Exception
     * @author Souradeep.Ghosh
     */
    public static ResultSet getResultSetMulti(String query) {
        ResultSet resultSet2 = null;
        try {
            Statement statement = connection.createStatement();
            Thread.sleep(500);
            ResultSet resultSet1 = statement.executeQuery(query);
            resultSet1.close();
            boolean rs2 = statement.getMoreResults();
            if (rs2) {
                resultSet2 = statement.getResultSet();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet2;
    }

    /**
     * This method is used to get the result set from the Payment Database by
     * executing the given query.
     *
     * @param query
     * @return
     * @throws Exception
     */
    public static ResultSet getResultSetPaymentDatabase(String query) {
        ResultSet resultSet = null;
        try {
            Statement statement = chaseConn.createStatement();
            Thread.sleep(500);
            resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    /**
     * This method is used to get the result set from the Payment Database by
     * executing the given query from Chase Payment API DB
     *
     * @param query
     * @return
     * @throws Exception
     */
    public static ResultSet getResultSetPaymentDatabaseChase(String query) {
        ResultSet resultSet = null;
        try {
            Statement statement = chaseConn.createStatement();
            Thread.sleep(500);
            resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static void updateSqlComm(String sqlQuery) {
        Connection connection = null;
        try {
            DataBaseModel dataBaseModel = DatabaseConfiguration.getDatabaseDrivers("base_db");
            String baseDatabaseURI = dataBaseModel.getDriver() + "//" + dataBaseModel.getHost() +
                    ";database=" + dataBaseModel.getDatabase() +
                    ";user=" + dataBaseModel.getUser() +
                    ";password=" + dataBaseModel.getPassword() +
                    ";ecrypt=" + dataBaseModel.getEncrypt() +
                    ";trustServerCertificate=" + dataBaseModel.getTrustServerCertificate();
            connection = DriverManager.getConnection(baseDatabaseURI);
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sqlQuery);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void executeUpdateDeleteSQLQuery(String sqlQuery) {
        Connection connection = null;
        try {
            DataBaseModel dataBaseModel = DatabaseConfiguration.getDatabaseDrivers("base_db");
            String baseDatabaseURI = dataBaseModel.getDriver() + "//" + dataBaseModel.getHost() +
                    ";database=" + dataBaseModel.getDatabase() +
                    ";user=" + dataBaseModel.getUser() +
                    ";password=" + dataBaseModel.getPassword() +
                    ";ecrypt=" + dataBaseModel.getEncrypt() +
                    ";trustServerCertificate=" + dataBaseModel.getTrustServerCertificate();
            connection = DriverManager.getConnection(baseDatabaseURI);
            if (connection != null) {
                log.info("Connected to the database...");
            }
            else {
                log.info(
                        "Database connection failed to the Environment");
            }
            Statement stmt = connection.createStatement();
            stmt.executeQuery(sqlQuery);
        } catch (SQLException sqlEx) {
            log.info("SQL Exception:" + sqlEx.getStackTrace());
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Delete user by given user id by calling this method.
     *
     * @param userId
     * @return
     */
    public static int callProcedureToDeleteUser(int userId) {
        Connection connection = null;
        ResultSet rs;
        int FinalResult = 0;
        CallableStatement stmt;
        try {
            DataBaseModel dataBaseModel = DatabaseConfiguration.getDatabaseDrivers("base_db");
            String baseDatabaseURI = dataBaseModel.getDriver() + "//" + dataBaseModel.getHost() +
                    ";database=" + dataBaseModel.getDatabase() +
                    ";user=" + dataBaseModel.getUser() +
                    ";password=" + dataBaseModel.getPassword() +
                    ";ecrypt=" + dataBaseModel.getEncrypt() +
                    ";trustServerCertificate=" + dataBaseModel.getTrustServerCertificate();
            connection = DriverManager.getConnection(baseDatabaseURI);
            if (connection != null) {
                log.info("Connected to the database...");
            }
            else {
                log.info(
                        "Database connection failed to  Environment");
            }
            String insertUserID = "{call SetUnRegisterUser(?)}";
            stmt = connection.prepareCall(insertUserID);
            stmt.setInt(1, userId);
            stmt.execute();
            rs = stmt.getResultSet();
            rs.next();
            String message = rs.getString(1);
        } catch (Exception err) {
            log.info(
                    "No Records obtained for this specific query");
            err.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return FinalResult;
    }

    public static void callStoredProcedureUnblockAccountIp() {
        Connection connection = null;
        CallableStatement stmt;
        try {
            DataBaseModel dataBaseModel = DatabaseConfiguration.getDatabaseDrivers("base_db");
            String baseDatabaseURI = dataBaseModel.getDriver() + "//" + dataBaseModel.getHost() +
                    ";database=" + dataBaseModel.getDatabase() +
                    ";user=" + dataBaseModel.getUser() +
                    ";password=" + dataBaseModel.getPassword() +
                    ";ecrypt=" + dataBaseModel.getEncrypt() +
                    ";trustServerCertificate=" + dataBaseModel.getTrustServerCertificate();
            connection = DriverManager.getConnection(baseDatabaseURI);
            if (connection != null) {
                log.info("Connected to the database...");
            }
            else {
                log.info("Database connection failed to  Environment");
            }
            String SQL = "{call AutoUnLockAll}";
            // String SQL = "call AutoUnLockAll";
            stmt = connection.prepareCall(SQL);
            stmt.execute();
        } catch (Exception err) {
            log.info(
                    "No Records obtained for this specific query");
            err.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    

    
    public static HashMap<String, String> getInactiveAccInfo() {
        String query = SQLQueries.getInactiveAccDetails();
        HashMap<String, String> inactiveAccountInfo = new HashMap<>();
        try {
            ResultSet rs = DataBaseUtils.getResultSet(query);
            while (rs.next()) {
            	inactiveAccountInfo.put("UserName", rs
                        .getString("username"));
//                inactiveAccountInfo.put("UtilityAccountNumber", rs
//                        .getString("utilityaccountnumber"));
//                inactiveAccountInfo.put("ZipCode", rs.getString("zipcode"));
                inactiveAccountInfo.put("EmailID", rs.getString("emailid"));
//                inactiveAccountInfo.put("PhoneNo", rs.getString("mobilephone"));
                break;
            }
            return inactiveAccountInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return inactiveAccountInfo;
        }
    }
    
    public static void makeUserAccountTempLock(String username) {
    	String query1 = SQLQueries.getQueryUpdateUserStatus("5", username);
    	String query2 = SQLQueries.getQueryUpdateUserIsLockedValue("1", username);  	
    	executeUpdateDeleteSQLQuery(query1);
    	executeUpdateDeleteSQLQuery(query2);   	
    }
    
    public static List<String> getGuestNameCSP(String utilityAccountNumber) throws Exception {
        String sQuery = SQLQueries.getGuestUserDetails(utilityAccountNumber);
        Thread.sleep(1000);
        ResultSet rs = DataBaseUtils.getResultSet(sQuery);
        List<String> list = new ArrayList();
        while (rs.next()) {
            String value = rs.getString("GuestName");
            list.add(value);
        }
        return list;
    }
    
    public static List<String> getGuestEmailCSP(String utilityAccountNumber) throws Exception {
        String sQuery = SQLQueries.getGuestUserDetails(utilityAccountNumber);
        Thread.sleep(1000);
        ResultSet rs = DataBaseUtils.getResultSet(sQuery);
        List<String> list = new ArrayList();
        while (rs.next()) {
            String value = rs.getString("GuestEmailID");
            list.add(value);
        }
        return list;
    }
    
    public static List<String> getGuestRoleCSP(String utilityAccountNumber) throws Exception {
        String sQuery = SQLQueries.getGuestUserDetails(utilityAccountNumber);
        Thread.sleep(1000);
        ResultSet rs = DataBaseUtils.getResultSet(sQuery);
        List<String> list = new ArrayList();
        while (rs.next()) {
            String value = rs.getString("RoleId");
            if(value.equals("1")) {
            	list.add("Guest User Access");
            }
            
        }
        return list;
    }
    
    public static List<String> getGuestRequestDateCSP(String utilityAccountNumber) throws Exception {
        String sQuery = SQLQueries.getGuestUserDetails(utilityAccountNumber);
        Thread.sleep(1000);
        ResultSet rs = DataBaseUtils.getResultSet(sQuery);
        List<String> list = new ArrayList();
        while (rs.next()) {
            String value = rs.getString("CreatedDate").split(" ")[0];
            list.add(value);
        }
        return list;
    }
    
    public static List<String> getGuestExpirationDateCSP(String utilityAccountNumber) throws Exception {
        String sQuery = SQLQueries.getGuestUserDetails(utilityAccountNumber);
        Thread.sleep(1000);
        ResultSet rs = DataBaseUtils.getResultSet(sQuery);
        List<String> list = new ArrayList();
        while (rs.next()) {
            String value = rs.getString("AccessExpiryDate").split(" ")[0];
            list.add(value);
        }
        return list;
    }
    
	public static String getCountRowServiceAccount(String customerNum, String zipCode, String accountNum, String Status,
			String addressType, String cityName, String isPaperless, String linkedUser) {
		String value = "";
		if (isPaperless.equals("Yes")) {
			isPaperless = "0";
			String sQuery = SQLQueries.getCountOfAdvSearchResultServiceAccount(customerNum, zipCode, accountNum, Status,
					addressType, cityName, isPaperless, linkedUser);
			ResultSet rs = DataBaseUtils.getResultSet(sQuery);
			try {
				while (rs.next()) {
					value = rs.getString("TotalRecord");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (isPaperless.equals("No")) {
			isPaperless = "1";
			String sQuery = SQLQueries.getCountOfAdvSearchResultServiceAccount(customerNum, zipCode, accountNum, Status,
					addressType, cityName, isPaperless, linkedUser);
			ResultSet rs = DataBaseUtils.getResultSet(sQuery);
			try {
				while (rs.next()) {
					value = rs.getString("TotalRecord");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			String sQuery = SQLQueries.getCountOfAdvSearchResultServiceAccount(customerNum, zipCode, accountNum, Status,
					addressType, cityName, isPaperless, linkedUser);
			ResultSet rs = DataBaseUtils.getResultSet(sQuery);
			try {
				while (rs.next()) {
					value = rs.getString("TotalRecord");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return value;
	}
    
	public static List<String> getAccountStatus(String accountNum, String addressType, String status,
			String customerNum, String isPaperless, String linkedUsers, String zipCode, String cityName) {
		String sQuery = SQLQueries.getValueByFieldName(accountNum, addressType, status, customerNum, zipCode, cityName);
		ResultSet rs = DataBaseUtils.getResultSet(sQuery);
		List<String> list = new ArrayList();
		try {
			while (rs.next()) {
				String value = rs.getString("AccountStatus");
				list.add(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<String> getServiceAccountNumber(String accountNum, String AddressType, String status,
			String customerNum, String isPaperless, String linkedUsers, String zipCode, String cityName) {
		String sQuery = SQLQueries.getValueByFieldName(accountNum, AddressType, status, customerNum, zipCode, cityName);
		ResultSet rs = DataBaseUtils.getResultSet(sQuery);
		List<String> list = new ArrayList();
		try {
			while (rs.next()) {
				String value = rs.getString("UtilityAccountNumber");
				list.add(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<String> getCustomerNumber(String accountNumber, String AddressType, String status,
			String customerNum, String isPaperless, String linkedUsers, String zipCode, String cityName) {
		String sQuery = SQLQueries.getValueByFieldName(accountNumber, AddressType, status, customerNum, zipCode,
				cityName);
		ResultSet rs = DataBaseUtils.getResultSet(sQuery);
		List<String> list = new ArrayList();
		try {
			while (rs.next()) {
				String value = rs.getString("CustomerNo");
				list.add(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<String> getFullName(String accountNumber, String AddressType, String status, String customerNum,
			String isPaperless, String linkedUsers, String zipCode, String cityName) {
		String sQuery = SQLQueries.getValueByFieldName(accountNumber, AddressType, status, customerNum, zipCode,
				cityName);
		ResultSet rs = DataBaseUtils.getResultSet(sQuery);
		List<String> list = new ArrayList();
		try {
			while (rs.next()) {
				String value = rs.getString("FullName");
				list.add(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<String> getMobilePhone(String accountNumber, String AddressType, String status,
			String customerNumber, String isPaperless, String linkedUsers, String zipCode, String cityName) {
		String sQuery = SQLQueries.getValueByFieldName(accountNumber, AddressType, status, customerNumber, zipCode,
				cityName);
		ResultSet rs = DataBaseUtils.getResultSet(sQuery);
		List<String> list = new ArrayList();
		try {
			while (rs.next()) {
				String value = rs.getString("MobilePhone");
				list.add(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<String> getEmailID(String accountNum, String addType, String status, String customerNum,
			String isPaperless, String linkedUsers, String zipCode, String cityName) {
		String sQuery = SQLQueries.getValueByFieldName(accountNum, addType, status, customerNum, zipCode, cityName);
		ResultSet rs = DataBaseUtils.getResultSet(sQuery);
		List<String> list = new ArrayList();
		try {
			while (rs.next()) {
				String value = rs.getString("EmailID");
				list.add(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<String> getAccountType(String accountNumber, String AddressType, String status,
			String customerNum, String isPaperless, String linkedUsers, String zipCode, String cityName) {
		String sQuery = SQLQueries.getValueByFieldName(accountNumber, AddressType, status, customerNum, zipCode,
				cityName);
		ResultSet rs = DataBaseUtils.getResultSet(sQuery);
		List<String> list = new ArrayList();
		try {
			while (rs.next()) {
				String value = rs.getString("AddressType");
				list.add(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static String getCountRowCustomer(String customerNum, String zipCode, String accountNum, String contactNo,
			String addressType, String cityName, String firstName, String lastName, String email) {
		String value = "";
		String sQuery = SQLQueries.getCountOfAdvSearchResultCustomer(customerNum, zipCode, accountNum, contactNo,
				addressType, cityName, firstName, lastName, email);
		ResultSet rs = DataBaseUtils.getResultSet(sQuery);
		try {
			while (rs.next()) {
				value = rs.getString("TotalRecord");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static List<String> getCustomerNumberCustomer(String customerNumber, String zipCode, String accountNumber,
			String contactNo, String AddressType, String cityName, String firstName, String lastName, String email) {
		String sQuery = SQLQueries.getValueByFieldNameCustomer(customerNumber, zipCode, accountNumber, contactNo,
				AddressType, cityName, firstName, lastName, email);
		ResultSet rs = DataBaseUtils.getResultSet(sQuery);
		List<String> list = new ArrayList();
		try {
			while (rs.next()) {
				String value = rs.getString("CustomerNumber");
				list.add(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<String> getFullNameCustomer(String customerNumber, String zipCode, String accountNumber,
			String contactNo, String AddressType, String cityName, String firstName, String lastName, String email) {
		String sQuery = SQLQueries.getValueByFieldNameCustomer(customerNumber, zipCode, accountNumber, contactNo,
				AddressType, cityName, firstName, lastName, email);
		ResultSet rs = DataBaseUtils.getResultSet(sQuery);
		List<String> list = new ArrayList();
		try {
			while (rs.next()) {
				String value = rs.getString("CustomerName");
				list.add(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<String> getMobileNumberCustomer(String customerNum, String zipCode, String accountNum,
			String contactNo, String addType, String cityName, String firstName, String lastName, String email) {
		String sQuery = SQLQueries.getValueByFieldNameCustomer(customerNum, zipCode, accountNum, contactNo, addType,
				cityName, firstName, lastName, email);
		ResultSet rs = DataBaseUtils.getResultSet(sQuery);
		List<String> list = new ArrayList();
		try {
			while (rs.next()) {
				String value = rs.getString("PrimaryPhone");
				list.add(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<String> getEmailIdCustomer(String customerNum, String zipCode, String accountNum,
			String contactNo, String addType, String cityName, String firstName, String lastName, String email) {
		String sQuery = SQLQueries.getValueByFieldNameCustomer(customerNum, zipCode, accountNum, contactNo, addType,
				cityName, firstName, lastName, email);
		ResultSet rs = DataBaseUtils.getResultSet(sQuery);
		List<String> list = new ArrayList();
		try {
			while (rs.next()) {
				String value = rs.getString("Customersemail");
				list.add(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<String> getStatusUser(String accountNum, String addType, String status, String userType,
			String linkedAcc, String role, String isSocial, String customerNum, String contactNo, String zipCode,
			String cityName, String firstName, String lastName, String email) {
		String sQuery = SQLQueries.getValueByFieldNameUser(accountNum, addType, status, userType, linkedAcc, role,
				isSocial, customerNum, contactNo, zipCode, cityName, firstName, lastName, email);
		ResultSet rs = DataBaseUtils.getResultSet(sQuery);
		List<String> list = new ArrayList();
		try {
			while (rs.next()) {
				String value = rs.getString("UserStatus");
				list.add(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<String> getFullNameUser(String accountNum, String addType, String status, String userType,
			String linkedAcc, String role, String isSocial, String customerNum, String contactNo, String zipCode,
			String cityName, String firstName, String lastName, String email) {
		String sQuery = SQLQueries.getValueByFieldNameUser(accountNum, addType, status, userType, linkedAcc, role,
				isSocial, customerNum, contactNo, zipCode, cityName, firstName, lastName, email);
		ResultSet rs = DataBaseUtils.getResultSet(sQuery);
		List<String> list = new ArrayList();
		try {
			while (rs.next()) {
				String value = rs.getString("Name");
				list.add(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<String> getMobileNumberUser(String accountNum, String addType, String status, String userType,
			String linkedAcc, String role, String isSocial, String customerNum, String contactNo, String zipCode,
			String cityName, String firstName, String lastName, String email) {
		String sQuery = SQLQueries.getValueByFieldNameUser(accountNum, addType, status, userType, linkedAcc, role,
				isSocial, customerNum, contactNo, zipCode, cityName, firstName, lastName, email);
		ResultSet rs = DataBaseUtils.getResultSet(sQuery);
		List<String> list = new ArrayList();
		try {
			while (rs.next()) {
				String value = rs.getString("PrimaryPhone");
				list.add(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<String> getEmailIdUser(String accountNum, String addType, String status, String userType,
			String linkedAcc, String role, String isSocial, String customerNum, String contactNo, String zipCode,
			String cityName, String firstName, String lastName, String email) {
		String sQuery = SQLQueries.getValueByFieldNameUser(accountNum, addType, status, userType, linkedAcc, role,
				isSocial, customerNum, contactNo, zipCode, cityName, firstName, lastName, email);
		ResultSet rs = DataBaseUtils.getResultSet(sQuery);
		List<String> list = new ArrayList();
		try {
			while (rs.next()) {
				String value = rs.getString("EmailID");
				list.add(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static String getCountRowUser(String accountNum, String addressType, String status, String userType,
			String linkedAcc, String role, String isSocial, String customerNum, String contactNo, String zipCode,
			String cityName, String firstName, String lastName, String email) {
		String value = "";
		if (linkedAcc.equals("1")) {
			linkedAcc = "0-1";
			String sQuery = SQLQueries.getCountOfAdvSearchResultUser(accountNum, addressType, status, userType, linkedAcc,
					role, isSocial, customerNum, contactNo, zipCode, cityName, firstName, lastName, email);
			ResultSet rs = DataBaseUtils.getResultSet(sQuery);
			try {
				while (rs.next()) {
					value = rs.getString("TotalRecord");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			String sQuery = SQLQueries.getCountOfAdvSearchResultUser(accountNum, addressType, status, userType, linkedAcc,
					role, isSocial, customerNum, contactNo, zipCode, cityName, firstName, lastName, email);
			ResultSet rs = DataBaseUtils.getResultSet(sQuery);
			try {
				while (rs.next()) {
					value = rs.getString("TotalRecord");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return value;
	}
	
	public static List<String> getAccountStatusUniversalSearch(String itemToSearch) {
        String sQuery = SQLQueries.getUniversalSearchResultMode1(itemToSearch);
        ResultSet rs = DataBaseUtils.getResultSet(sQuery);
        List<String> list = new ArrayList();
        try {
            while (rs.next()) {
                String value = rs.getString("AccountStatus");
                list.add(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
	
	public static List<String> getServiceAccountNumberUniversalSearch(String itemToSearch) {
        String sQuery = SQLQueries.getUniversalSearchResultMode1(itemToSearch);
        ResultSet rs = DataBaseUtils.getResultSet(sQuery);
        List<String> list = new ArrayList();
        try {
            while (rs.next()) {
                String value = rs.getString("ServiceAccountnumber");
                list.add(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
	
	public static List<String> getCustomerNameUniversalSearch(String itemToSearch) {
        String sQuery = SQLQueries.getUniversalSearchResultMode1(itemToSearch);
        ResultSet rs = DataBaseUtils.getResultSet(sQuery);
        List<String> list = new ArrayList();
        try {
            while (rs.next()) {
                String value = rs.getString("CustomerName");
                list.add(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
	
	public static List<String> getPrimaryPhoneUniversalSearch(String itemToSearch) {
        String sQuery = SQLQueries.getUniversalSearchResultMode1(itemToSearch);
        ResultSet rs = DataBaseUtils.getResultSet(sQuery);
        List<String> list = new ArrayList();
        try {
            while (rs.next()) {
                String value = rs.getString("PrimaryPhone");
                list.add(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
	public static List<String> getCustomersemailUniversalSearch(String itemToSearch) {
        String sQuery = SQLQueries.getUniversalSearchResultMode1(itemToSearch);
        ResultSet rs = DataBaseUtils.getResultSet(sQuery);
        List<String> list = new ArrayList();
        try {
            while (rs.next()) {
                String value = rs.getString("Customersemail");
                list.add(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
	
	public static List<String> getAccountTypeUniversalSearch(String itemToSearch) {
        String sQuery = SQLQueries.getUniversalSearchResultMode1(itemToSearch);
        ResultSet rs = DataBaseUtils.getResultSet(sQuery);
        List<String> list = new ArrayList();
        try {
            while (rs.next()) {
                String value = rs.getString("AccountType");
                list.add(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
	
	public static String getCountFieldValueUniversalSearch(String customerName, String zipCode, String accountNumber,
			String emailId, String contactNo, String address) {
		String value = "";
		String sQuery = SQLQueries.getCountFieldValueUniversalSearch(customerName, zipCode, accountNumber, emailId,
				contactNo, address);
		ResultSet rs = DataBaseUtils.getResultSet(sQuery);
		try {
			while (rs.next()) {
				value = rs.getString("TotalRecords");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static List<String> getCustomerNameGlobal(String customerName, String zipCode, String accountNumber,
			String emailId, String contactNo, String address) {
		String sQuery = SQLQueries.getFieldValueUniversalSearch(customerName, zipCode, accountNumber, emailId, contactNo,
				address);
		ResultSet rs = DataBaseUtils.getResultSet(sQuery);
		List<String> list = new ArrayList();
		try {
			while (rs.next()) {
				String value = rs.getString("CustomerName");
				list.add(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<String> getServiceAccountNumberGlobal(String customerName, String zipCode, String accountNumber,
			String emailId, String contactNo, String address) {
		String sQuery = SQLQueries.getFieldValueUniversalSearch(customerName, zipCode, accountNumber, emailId, contactNo,
				address);
		ResultSet rs = DataBaseUtils.getResultSet(sQuery);
		List<String> list = new ArrayList();
		try {
			while (rs.next()) {
				String value = rs.getString("AccountNumber");
				list.add(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<String> getPrimaryContactNoGlobal(String customerName, String zipCode, String accountNumber,
			String emailId, String contactNo, String address) {
		String sQuery = SQLQueries.getFieldValueUniversalSearch(customerName, zipCode, accountNumber, emailId, contactNo,
				address);
		ResultSet rs = DataBaseUtils.getResultSet(sQuery);
		List<String> list = new ArrayList();
		try {
			while (rs.next()) {
				String value = rs.getString("MobilePhone");
				list.add(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<String> getEmailAddressGlobal(String customerName, String zipCode, String accountNumber,
			String emailId, String contactNo, String address) {
		String sQuery = SQLQueries.getFieldValueUniversalSearch(customerName, zipCode, accountNumber, emailId, contactNo,
				address);
		ResultSet rs = DataBaseUtils.getResultSet(sQuery);
		List<String> list = new ArrayList();
		try {
			while (rs.next()) {
				String value = rs.getString("EmailID");
				list.add(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<String> getCustomerStatusGlobal(String customerName, String zipCode, String accountNumber,
			String emailId, String contactNo, String address) {
		String sQuery = SQLQueries.getFieldValueUniversalSearch(customerName, zipCode, accountNumber, emailId, contactNo,
				address);
		ResultSet rs = DataBaseUtils.getResultSet(sQuery);
		List<String> list = new ArrayList();
		try {
			while (rs.next()) {
				String value = rs.getString("Status");
				list.add(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<String> getAccountStatusGlobal(String customerName, String zipCode, String accountNumber,
			String emailId, String contactNo, String address) {
		String sQuery = SQLQueries.getFieldValueUniversalSearch(customerName, zipCode, accountNumber, emailId, contactNo,
				address);
		ResultSet rs = DataBaseUtils.getResultSet(sQuery);
		List<String> list = new ArrayList();
		try {
			while (rs.next()) {
				String value = rs.getString("AccountStatus");
				list.add(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<String> getCustomerNameGlobalEmptySearch() throws Exception {
        String sQuery = SQLQueries.getEmptyFieldSearchUniversalSearch();
        ResultSet rs = DataBaseUtils.getResultSet(sQuery);
        List<String> list = new ArrayList();
        while (rs.next()) {
            String value = rs.getString("CustomerName");
            list.add(value);
        }
        return list;
    }

    public static List<String> getServiceAccountNumberGlobalEmptySearch() throws Exception {
        String sQuery = SQLQueries.getEmptyFieldSearchUniversalSearch();
        ResultSet rs = DataBaseUtils.getResultSet(sQuery);
        List<String> list = new ArrayList();
        while (rs.next()) {
            String value = rs.getString("AccountNumber");
            list.add(value);
        }
        return list;
    }

    public static List<String> getPrimaryContactNoGlobalEmptySearch() throws Exception {
        String sQuery = SQLQueries.getEmptyFieldSearchUniversalSearch();
        ResultSet rs = DataBaseUtils.getResultSet(sQuery);
        List<String> list = new ArrayList();
        while (rs.next()) {
            String value = rs.getString("MobilePhone");
            list.add(value);
        }
        return list;
    }

    public static List<String> getEmailAddressGlobalEmptySearch() throws Exception {
        String sQuery = SQLQueries.getEmptyFieldSearchUniversalSearch();
        ResultSet rs = DataBaseUtils.getResultSet(sQuery);
        List<String> list = new ArrayList();
        while (rs.next()) {
            String value = rs.getString("EmailID");
            list.add(value);
        }
        return list;
    }

    public static List<String> getCustomerStatusGlobalEmptySearch() throws Exception {
        String sQuery = SQLQueries.getEmptyFieldSearchUniversalSearch();
        ResultSet rs = DataBaseUtils.getResultSet(sQuery);
        List<String> list = new ArrayList();
        while (rs.next()) {
            String value = rs.getString("Status");
            list.add(value);
        }
        return list;
    }

    public static List<String> getAccountStatusGlobalEmptySearch() throws Exception {
        String sQuery = SQLQueries.getEmptyFieldSearchUniversalSearch();
        ResultSet rs = DataBaseUtils.getResultSet(sQuery);
        List<String> list = new ArrayList();
        while (rs.next()) {
            String value = rs.getString("AccountStatus");
            list.add(value);
        }
        return list;
    }
	
    public static Map<String, String> getUtilitySettingDetails() {
    	
        Map<String, String> utilitySettingDetails = null;
		try {
			ResultSet rs = DataBaseUtils.getResultSet(SQLQueries.sAllUtilitySettingsQuery);
			ResultSetMetaData metadata = rs.getMetaData();
			int columnCount = metadata.getColumnCount();
			ArrayList<String> columns = new ArrayList<>();
			for (int i = 1; i <= columnCount; i++) {
			    String columnName = metadata.getColumnName(i);
			    columns.add(columnName);
			}
			utilitySettingDetails = new LinkedHashMap<>();
			while (rs.next()) {
			    for (String columnName : columns) {
			        String value = rs.getString(columnName);
			        utilitySettingDetails.put(columnName, value);
			    }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return utilitySettingDetails;
    	
    }
}
