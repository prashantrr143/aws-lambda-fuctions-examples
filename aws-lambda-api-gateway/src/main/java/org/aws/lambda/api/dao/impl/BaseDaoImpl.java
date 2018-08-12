package org.aws.lambda.api.dao.impl;

import java.sql.Connection;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aws.lambda.api.dao.BaseDao;
import org.aws.lambda.api.jdbc.DatabaseUtils;

public class BaseDaoImpl implements BaseDao {

	public static final Logger LOGGER = LogManager.getLogger(BaseDaoImpl.class);
	private static Properties dbConfigProperties = new Properties();
	static {

		ResourceBundle rb = ResourceBundle.getBundle("lambda-configuration");
		Enumeration<String> keys = rb.getKeys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			dbConfigProperties.put(key, rb.getString(key));
		}
		LOGGER.info("Total Properties configured is : " + dbConfigProperties.size());

	}

	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return DatabaseUtils.getConnection(dbConfigProperties.getProperty("jdbc.aws.rds.uri"),
				dbConfigProperties.getProperty("jdbc.rds.user"), dbConfigProperties.getProperty("jdbc.rds.password"),
				dbConfigProperties.getProperty("jdbc.rds.port"), dbConfigProperties.getProperty("jdbc.rds.dbName"));
	}

	@Override
	public void closeConnection(Connection con) {
		DatabaseUtils.closeConnection(con);
	}

}
