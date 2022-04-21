package com.hello.hello;

import java.sql.SQLException;
import java.util.HashMap;

public class mythread implements Runnable {
private String message;
public mythread(String message) {
	this.message=message;
}
@Override
public void run() {
	// TODO Auto-generated method stub
	decoder obj=new decoder();
	database_manager a = new database_manager();
	
	HashMap<String,String> gtp_decoded =obj.decode(message);
	System.out.println( gtp_decoded);
	try {
		a.write_data(gtp_decoded);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	obj=null;
	a=null;
}

}
