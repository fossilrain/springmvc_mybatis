package com.smm.controller;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * <p>Title: JDBCTestController</p>
 * <p>Description: jdbc测试处理类</p>
 * <p>Company: </p>
 * @author RainFossil
 * @date 2017年1月12日 下午4:29:03
 * @version 1.0.0
 */
@Controller
public class JDBCTestController {
	@Autowired
	private DataSource dataSource;
	
	@RequestMapping("/jdbctest")
	public String jdbctest(String type){
		Connection con = DataSourceUtils.getConnection(dataSource);
		int tcount = 0;
		try {
			DatabaseMetaData databaseMetaData = con.getMetaData();
			System.out.println("getDatabaseProductName:"+databaseMetaData.getDatabaseProductName() +"\n"
					+"getDatabaseProductVersion:"+databaseMetaData.getDatabaseProductVersion() +"\n"
					+"getDriverName:"+databaseMetaData.getDriverName() +"\n"
					+"getURL:"+databaseMetaData.getURL() +"\n"
					+"getUserName:"+databaseMetaData.getUserName() +"\n"
					+"getUserName:"+databaseMetaData.getSchemaTerm() +"\n");
			String[]types = new String[]{"TABLE"};
			if("1".equals(type)){
				types = new String[]{"VIEW"};
			}
			ResultSet tableSet = databaseMetaData.getTables(null, null, "%", types);
			while(tableSet.next() && tcount < 3){  //tcount防止获取系统表或视图，测试用
				tcount++ ;
				String TABLE_TYPE = tableSet.getString("TABLE_TYPE");
				String tableName = tableSet.getString("TABLE_NAME");
			    String tableComment = tableSet.getString("REMARKS");
			    System.out.println("TABLE_TYPE:"+TABLE_TYPE);
			    System.out.println("表名:"+tableName);
			    System.out.println("表说明:"+tableComment);
			    ResultSet rs = databaseMetaData.getColumns(null, "%", tableName, "%"); 
			    boolean supportsIsAutoIncrement = false;
		        boolean supportsIsGeneratedColumn = false;
		        ResultSetMetaData rsmd = rs.getMetaData();
		        int colCount = rsmd.getColumnCount();
		        for (int i = 1; i <= colCount; i++) {
		            if ("IS_AUTOINCREMENT".equals(rsmd.getColumnName(i))) { //$NON-NLS-1$
		                supportsIsAutoIncrement = true;
		            }
		            if ("IS_GENERATEDCOLUMN".equals(rsmd.getColumnName(i))) { //$NON-NLS-1$
		                supportsIsGeneratedColumn = true;
		            }
		        }
			    while (rs.next()) {
			    	int DATA_TYPE = rs.getInt("DATA_TYPE");
			    	int COLUMN_SIZE = rs.getInt("COLUMN_SIZE");
			    	String COLUMN_NAME = rs.getString("COLUMN_NAME");
			    	int NULLABLE = rs.getInt("NULLABLE");
			    	int DECIMAL_DIGITS = rs.getInt("DECIMAL_DIGITS");
			    	String REMARKS = rs.getString("REMARKS");
			    	String COLUMN_DEF = rs.getString("COLUMN_DEF");
			    	String IS_AUTOINCREMENT = "无";
			    	if(supportsIsAutoIncrement){
			    		IS_AUTOINCREMENT = rs.getString("IS_AUTOINCREMENT");
			    	}
			    	String IS_GENERATEDCOLUMN = "无";
			    	if(supportsIsGeneratedColumn){
			    		IS_GENERATEDCOLUMN = rs.getString("IS_GENERATEDCOLUMN");
			    	}
			    	String TABLE_CAT = rs.getString("TABLE_CAT");
			    	String TABLE_SCHEM = rs.getString("TABLE_SCHEM");
			    	String TABLE_NAME = rs.getString("TABLE_NAME");
			    	System.out.println("DATA_TYPE:"+DATA_TYPE+"\n"+
			    			"COLUMN_SIZE:"+COLUMN_SIZE+"\n"+
			    			"COLUMN_NAME:"+COLUMN_NAME+"\n"+
			    			"NULLABLE:"+NULLABLE+"\n"+
			    			"DECIMAL_DIGITS:"+DECIMAL_DIGITS+"\n"+
			    			"REMARKS:"+REMARKS+"\n"+
			    			"COLUMN_DEF:"+COLUMN_DEF+"\n"+
			    			"IS_AUTOINCREMENT:"+IS_AUTOINCREMENT+"\n"+
			    			"IS_GENERATEDCOLUMN:"+IS_GENERATEDCOLUMN+"\n"+
			    			"TABLE_CAT:"+TABLE_CAT+"\n"+
			    			"TABLE_SCHEM:"+TABLE_SCHEM+"\n"+
			    			"TABLE_NAME:"+TABLE_NAME+"\n");
			    }
			    closeResultSet(rs);
	        }
			closeResultSet(tableSet);
			System.out.println("表/视图个数："+tcount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataSourceUtils.releaseConnection(con, dataSource);
		}
		
		return "success.jsp";
	}
	private void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // ignore
            }
        }
    }
}
