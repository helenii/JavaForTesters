package com.example.tests;

import java.util.Properties;
import java.util.logging.Logger;
import java.io.FileReader;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.example.fw.ApplicationManager;

public class TestBase {
	
	protected Logger log = Logger.getLogger("TEST");

	protected static ApplicationManager app;

	@BeforeTest
	public void setUp() throws Exception {
		String configFile = System.getProperty("configFile", "application.properties");
		Properties props = new Properties();
		props.load(new FileReader(configFile));
		log.info("setUp start");
		app = ApplicationManager.getInstance(props);
		log.info("setUp end");
	}

	@AfterTest
	public void tearDown() throws Exception {
		log.info("tearDown start");
		ApplicationManager.getInstance(null).stop();
		log.info("tearDown end");
	}
}
