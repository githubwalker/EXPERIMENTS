package com.alprojects.aop;

import java.lang.reflect.Method;
// import java.util.logging.Logger;


import org.apache.log4j.Logger;
import org.springframework.aop.MethodBeforeAdvice;

public class FunctionalityLogger implements MethodBeforeAdvice {

	// private static final Logger logger = Logger.getLogger(SomeFunctionality.class.getName());
	// private Logger = null;
	
	private static Logger logger = Logger.getLogger(SomeFunctionality.class);
	
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();

		sb.append("[");
		sb.append(target.toString());
		sb.append("]");

		sb.append(method.getName());
		sb.append("(");
		boolean bFirst = true;
		for (Object arg : args) {
			if (!bFirst)
				sb.append(", ");

			bFirst = false;

			String strParam = arg == null ? "null" : arg.toString();
			sb.append(strParam);
		}
		sb.append(")");

		logger.info(sb.toString());
	}
}
