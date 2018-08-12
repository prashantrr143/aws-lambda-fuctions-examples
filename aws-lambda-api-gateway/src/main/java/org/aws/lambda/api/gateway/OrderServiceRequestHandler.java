package org.aws.lambda.api.gateway;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aws.lambda.api.dao.TransactionErrorLogDao;
import org.aws.lambda.api.dao.impl.TransactionErrorLogDaoImpl;
import org.aws.lambda.api.http.HttpInvokerRequestHandler;
import org.aws.lambda.api.jdbc.model.TransactionErrorLog;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * 
 * @author prasingh26
 *
 */
public class OrderServiceRequestHandler implements RequestHandler<Object, String> {

	public static final Logger LOGGER = LogManager.getLogger(OrderServiceRequestHandler.class);

	private TransactionErrorLogDao errorDao = new TransactionErrorLogDaoImpl();

	@Override
	public String handleRequest(Object input, Context context) {
		LOGGER.info("entering method handleRequest with input : " + input);
		LambdaLogger lambdaLogger = context.getLogger();
		lambdaLogger.log("Entered method handleRequest : Logged by lambda Logger");

		String baseUri = "http://services.groupkt.com/state/get/IND/all";

		LOGGER.info("Invoking a get Request at URI " + baseUri);
		HttpInvokerRequestHandler httpInvokerRequestHander = new HttpInvokerRequestHandler();

		try {

			String response = httpInvokerRequestHander.callExternalService("GET", baseUri);
			LOGGER.info(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		for (int i = 0; i < 5; i++) {
			TransactionErrorLog log = new TransactionErrorLog();
			log.setError("Customized Error Log for Iteration Count  " + (i + 1));
			log.setUserId("prashant_singh");
			log.setCreateDt(new Timestamp(new Date().getTime()));
			// errorDao.save(log);
		}

		List<TransactionErrorLog> list = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			TransactionErrorLog log = new TransactionErrorLog();
			log.setError("Customized Error Log for Iteration Count  " + (i + 1));
			log.setUserId("prashant_singh");
			log.setCreateDt(new Timestamp(new Date().getTime()));
			list.add(log);
			// errorDao.save(log);
		}

		int[] status = errorDao.save(list);

		LOGGER.info("Done with savin data to AWS RDS Instance");
		LOGGER.info("Fetching Data from RDS Instance");

		List<TransactionErrorLog> errorLogList = errorDao.getErrorLog(new Timestamp(new Date().getTime()));
		errorLogList.forEach(e -> {
			LOGGER.info("Retrieved from Database : " + e);
		});

		LOGGER.info("Function name: " + context.getFunctionName());
		LOGGER.info("Max mem allocated: " + context.getMemoryLimitInMB());
		LOGGER.info("Time remaining in milliseconds: " + context.getRemainingTimeInMillis());
		LOGGER.info("CloudWatch log stream name: " + context.getLogStreamName());
		LOGGER.info("CloudWatch log group name: " + context.getLogGroupName());

		LOGGER.info("Exiting Method handleRequest");

		return "Hello from Lambda!";
	}

}
