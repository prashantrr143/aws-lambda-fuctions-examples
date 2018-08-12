package org.aws.lambda.api.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Core JDBC Mapper Interface
 * @author prasingh26
 *
 * @param <T>
 */
public interface JDBCMapper<T> {

	public T map(ResultSet rs) throws SQLException;

}
