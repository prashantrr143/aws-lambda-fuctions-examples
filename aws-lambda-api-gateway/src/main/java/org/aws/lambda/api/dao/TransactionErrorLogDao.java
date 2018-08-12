package org.aws.lambda.api.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import org.aws.lambda.api.jdbc.model.TransactionErrorLog;

public interface TransactionErrorLogDao {

	public Serializable save(TransactionErrorLog erroLog);
	
	public int[] save(List<TransactionErrorLog> errorLogs);

	public List<TransactionErrorLog> getErrorLog(Timestamp createDt);

}
