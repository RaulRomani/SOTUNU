/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sot.tests;



import com.sot.controllers.util.Log4jConfig;
import java.io.File;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Raul
 */
public class Log4j {
  
  final static Logger logger = Log4jConfig.getLogger(Log4j.class.getName());
  
  public static void main(String[] args) {
    
    //File log4jfile = new File("log4j.properties");
    //PropertyConfigurator.configure(log4jfile.getAbsolutePath());
    logger.info("joder");
    System.out.println("jss");
    
  }
}
