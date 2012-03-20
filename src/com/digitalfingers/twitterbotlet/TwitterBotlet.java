package com.digitalfingers.twitterbotlet;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class TwitterBotlet
 *
 */
@WebListener
public class TwitterBotlet implements ServletContextListener, ServletContextAttributeListener {
	
	private class RefreshTask extends TimerTask {

		@Override
		public void run() {
			// search for query
			// validate oauth
			// loop n query matches
				// parse match
				// construct response
				// update with @reply
		}
		
	}
	
	Timer timer;
	TimerTask task;
	
    /**
     * Default constructor. 
     */
    public TwitterBotlet() {
    	timer = new Timer();
    	task = new RefreshTask();
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event) {
    	timer.schedule(task, 0, 1000*60*10);
    }

	/**
     * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
     */
    public void attributeAdded(ServletContextAttributeEvent event) {
        // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
     */
    public void attributeReplaced(ServletContextAttributeEvent event) {
        // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
     */
    public void attributeRemoved(ServletContextAttributeEvent event) {
        // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent event) {
        timer.cancel();
    }
	
}
