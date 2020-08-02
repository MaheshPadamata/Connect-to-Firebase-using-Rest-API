package com.firebaseconnection.Firebase;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.google.firebase.database.ServerValue;
import com.google.firebase.database.core.ServerValues;

/**
 * Hello world!
 *
 */
@ComponentScan(value="com.controller")
@SpringBootApplication
public class FirebaseApp 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(FirebaseApp.class, args);
        System.out.println( "Hello World!" );
        
    }
    
    /*public static final Map<String, String> TIMESTAMP = createServerValuePlaceholder("timestamp");

    private static Map<String, String> createServerValuePlaceholder(String key) {
      Map<String, String> result = new HashMap<String, String>();
      result.put(ServerValues.NAME_SUBKEY_SERVERVALUE, key);
      return Collections.unmodifiableMap(result);
    }*/
}
