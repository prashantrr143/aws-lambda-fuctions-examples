package org.aws.lambda.api.gateway;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

	@Override
	public String handleRequest(Object input, Context context) {

		LambdaLogger lambdaLogger = context.getLogger();
		lambdaLogger.log("Entered method handleRequest : Logged by lambda Logger");

		LOGGER.info("entering method handleRequest with input : " + input);
		
		LOGGER.info("Function name: " + context.getFunctionName());
		LOGGER.info("Max mem allocated: " + context.getMemoryLimitInMB());
		LOGGER.info("Time remaining in milliseconds: " + context.getRemainingTimeInMillis());
		LOGGER.info("CloudWatch log stream name: " + context.getLogStreamName());
		LOGGER.info("CloudWatch log group name: " + context.getLogGroupName());

		LOGGER.info("Exiting Method handleRequest");

		return "Hello from Lambda!";
	}

}
