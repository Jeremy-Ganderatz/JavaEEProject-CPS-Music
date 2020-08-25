package com.jérém.CPSMusic;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class MySessionListener implements HttpSessionListener{
	
	private int sessionCounter = 0;
	private static final Logger LOG = Logger.getLogger(MySessionListener.class.getName());
	
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		synchronized (this) {
			sessionCounter++;
		}
		LOG.log(Level.INFO,"---------CPSMusic is created - {0} sesion in memory --------", sessionCounter );
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		synchronized (this) {
			sessionCounter--;
		}
		LOG.log(Level.INFO,"---------CPSMusic is destroyed - {0} sesion in memory --------" , sessionCounter);
	}

}
