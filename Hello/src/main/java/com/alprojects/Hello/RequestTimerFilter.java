package com.alprojects.Hello;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
 
// @WebFilter(urlPatterns={"/*"})
public class RequestTimerFilter implements Filter {
   private static final Logger logger
           = Logger.getLogger(RequestTimerFilter.class.getName());

   // @Override
   public void init(FilterConfig config) throws ServletException {
      logger.info("RequestTimerFilter initialized");
   }
 
   // @Override
   public void doFilter(ServletRequest request, ServletResponse response,
           FilterChain chain)
           throws IOException, ServletException {
      long before = System.currentTimeMillis();
      chain.doFilter(request, response);
      long after = System.currentTimeMillis();
      String path = ((HttpServletRequest)request).getRequestURI();
      if ( path == null )
    	  path = "<NULL>";
      logger.info(path + ": " + (after - before) + " msec");
   }
 
   // @Override
   public void destroy() {
      logger.info("RequestTimerFilter destroyed");
   }

}

