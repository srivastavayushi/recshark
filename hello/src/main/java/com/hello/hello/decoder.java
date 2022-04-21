package com.hello.hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.sql.*;
public class decoder {
	     public  static String hexToBinary(String hex)
	    {   
	    	 String binary = "";
	    	   // variable to store the converted
	          // Binary Sequence
	         // converting the accepted Hexadecimal
	        // string to upper case
	        hex = hex.toUpperCase();
	        // initializing the HashMap class
	        HashMap<Character, String> hashMap = new HashMap<Character, String>();
	        // storing the key value pairs
	        hashMap.put('0', "0000");
	        hashMap.put('1', "0001");
	        hashMap.put('2', "0010");
	        hashMap.put('3', "0011");
	        hashMap.put('4', "0100");
	        hashMap.put('5', "0101");
	        hashMap.put('6', "0110");
	        hashMap.put('7', "0111");
	        hashMap.put('8', "1000");
	        hashMap.put('9', "1001");
	        hashMap.put('A', "1010");
	        hashMap.put('B', "1011");
	        hashMap.put('C', "1100");
	        hashMap.put('D', "1101");
	        hashMap.put('E', "1110");
	        hashMap.put('F', "1111");
	        int i;
	        char ch;
	        // loop to iterate through the length
	        // of the Hexadecimal String
	        for (i = 0; i < hex.length(); i++) {
	            // extracting each character
	            ch = hex.charAt(i);
	 
	            // checking if the character is
	            // present in the keys
	            if (hashMap.containsKey(ch))
	 
	                // adding to the Binary Sequence
	                // the corresponding value of
	                // the key
	                binary += hashMap.get(ch);
	 
	            // returning Invalid Hexadecimal
	            // String if the character is
	            // not present in the keys
	            else {
	                binary = "Invalid Hexadecimal String";
	                return binary;
	            }
	        }
	 
	        // returning the converted Binary
	        return binary;
	    }
	     public static String bcdToDecimal(String s)
	     {
	    	 HashMap<String,Character> hashMap = new HashMap<String,Character>();
		        // storing the key value pairs
		        hashMap.put("0000",'0');
		        hashMap.put("0001",'1');
		        hashMap.put("0010",'2');
		        hashMap.put("0011",'3');
		        hashMap.put("0100",'4');
		        hashMap.put("0101",'5');
		        hashMap.put("0110",'6');
		        hashMap.put("0111",'7');
		        hashMap.put("1000",'8');
		        hashMap.put("1001",'9');
		        int j=0;
		        String ans="";
		        while(j<=s.length()-4)
		        { if (hashMap.get(s.substring(j,j+4))!=null)
		        	{ans=ans+hashMap.get(s.substring(j,j+4));}
		        	j=j+4;
		        }
		        return ans;
	     }
	 	public static int binaryToDecimal(String n)
	    {
	        String num = n;
	        int dec_value = 0;
	 
	        // Initializing base value to 1,
	        // i.e 2^0
	        int base = 1;
	 
	        int len = num.length();
	        for (int i = len - 1; i >= 0; i--) {
	            if (num.charAt(i) == '1')
	                dec_value += base;
	            base = base * 2;
	        }
	 
	        return dec_value;
	    }

	 	public static void connectJDBCToAWSEC2() throws SQLException {

	 	    System.out.println("----MySQL JDBC Connection Testing -------");
	 	    
	 	    try {
	 	        Class.forName("com.mysql.cj.jdbc.Driver");
	 	    } catch (ClassNotFoundException e) {
	 	        System.out.println("Where is your MySQL JDBC Driver?");
	 	        e.printStackTrace();
	 	        return;
	 	    }

	 	    System.out.println("MySQL JDBC Driver Registered!");
	 	    
	 	    Connection connection = null;

	 	    try {
	 	        connection = DriverManager.
	 	                getConnection("jdbc:mysql://" +"samsung.chztkmfznggh.ap-south-1.rds.amazonaws.com" + ":" + "3306" +"/" + "samsung", "admin", "samsungprism");
	 	    } catch (SQLException e) {
	 	        System.out.println("Connection Failed!:\n" + e.getMessage());
	 	    }

	 	    if (connection != null) {
	 	        System.out.println("SUCCESS!!!! You made it, take control     your database now!");
	 	       Calendar calendar = Calendar.getInstance();
		       java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
		 	   String query = " insert into gtp_decoded values(2, 0, 1, 1234567,'2008-11-11 13:23:44',12121212,12121212,122,122 )";
		 	   PreparedStatement preparedStmt = connection.prepareStatement(query);
		 	  preparedStmt.execute();
		 	   } else {
	 	        System.out.println("FAILURE! Failed to make connection!");
	 	    }
	 	   

	 	}
	 	public static HashMap<String, String> decode(String message)
	 	{
	 		HashMap<String, String> gtp = new HashMap<String, String>();
	 		String[] octets = message.split("\\s+");
	 		for(int i=0;i<octets.length;i++)
	 		{octets[i]=hexToBinary(octets[i]);
	 			//System.out.println(octets[i]);
	 		}
	 		Date date = new Date();
	 		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 		gtp.put("hexdom",message);

	 		gtp.put("message_type","");
	 		gtp.put("RequestTimeStamp",formatter.format(date));
	 		gtp.put("IMSI","");
	 		gtp.put("MEI","");
	 		gtp.put("TAI","");
	 		
	 		int version = binaryToDecimal(octets[0].substring(0,3));
	 		int p=binaryToDecimal((octets[0]).substring(3,4));
	 		int t=binaryToDecimal((octets[0]).substring(4,5));
	 		int mp=binaryToDecimal((octets[0]).substring(5,6));
	 		int Message_type=binaryToDecimal((octets[1]));
	 		gtp.put("message_type",String.valueOf(Message_type));
	 		int Message_length=binaryToDecimal(octets[2]+octets[3]);
	 		//System.out.println("*****");
	 		String teid="";
	 		if(t==1)
	 		{int start1,end1;
	 		for(int i=0;i<4;i++)	
	 		{start1=binaryToDecimal(octets[4+i].substring(0,4));
    		 end1=binaryToDecimal(octets[4+i].substring(4,8));	
    		 teid=String.valueOf(start1)+String.valueOf(end1)+teid;
	 		}
	 		}
	 		int ie_start=8+t*4;
	 		int point=ie_start;
	 		//System.out.println(" *******" + (Message_length+6));
	 		while(point<Message_length)
	 		{ //System.out.println(point);
	 	      int type = binaryToDecimal(octets[point]);
	 	     //System.out.println("type ="+type);
	 	      int ie_length = binaryToDecimal(octets[point+1]+octets[point+2]);
	 	      if (type==1)
	 	      {//System.out.println("entered");
	 	    	  String imsi="";
	 	    	  int start;
	 	    	  int end;
	 	    	  for(int i=0;i<ie_length;i++)
	 	    	  { //System.out.println(point+3+i);
	 	    		 start=binaryToDecimal(octets[point+4+i].substring(0,4));
	 	    		 end=binaryToDecimal(octets[point+4+i].substring(4,8));
	 	    		if (start==15)
	 	                {start=0;}
	 	            imsi=String.valueOf(start)+String.valueOf(end)+imsi;
	 	           
	 	    	  }
	 		 	gtp.put("IMSI",imsi);
	 	    	 //System.out.println(imsi);
	 	    	  point=point+4+ie_length;
	 	    	  
	 	    	 //System.out.println(point);

	 	      }

	 	     else if (type==75)
	 	      {//System.out.println(point);
	 	    	 //System.out.println("MEI captured");
	 	    	 String bin="";
	 	    	 for(int i=0;i<ie_length;i++)
	 	    	 {
	 	    		bin=octets[point+4+i]+bin;
	 	    	 }
	 	    	 String mei=bcdToDecimal(bin);
	 	    	gtp.put("MEI",mei);
	 	    	//System.out.println(mei);
	 	    	point=point+4+ie_length;
	 	      }
	 	    else if (type==86)
	 	      {//System.out.println(point);
	 	    	 //System.out.println("ULI CAPTURED");
	 	    	char a=octets[point+4].charAt(7);
	 	    	char b=octets[point+4].charAt(6);
	 	    	char c=octets[point+4].charAt(5);
	 	    	char d=octets[point+4].charAt(4);
	 	    	
	 	    	if(d=='1')
	 	    	{//System.out.println(a+b+c+d);
	 	    		//System.out.println("TAI CAPTURED");
	 	    		int pos=point+5;
	 	    		if(a=='1')
	 	    		{
	 	    			pos=pos+7;
	 	    		}
	 	    		if(b=='1')
	 	    		{
	 	    			pos=pos+7;
	 	    		}
	 	    		if(c=='1')
	 	    		{
	 	    			pos=pos+7;
	 	    		}
	 	    		String MCC=""+binaryToDecimal(octets[pos+1].substring(4,8))+binaryToDecimal(octets[pos].substring(0,4))+binaryToDecimal(octets[pos].substring(4,8));
	 	    		String MNC=""+binaryToDecimal(octets[pos+1].substring(0,4))+binaryToDecimal(octets[pos+2].substring(0,4))+binaryToDecimal(octets[pos+2].substring(4,8));
	 	    		String TAI= octets[pos+3]+octets[pos+4];
	 	    		//System.out.println(MCC);
	 	    		//System.out.println(MNC);
	 	    		//System.out.println(TAI);
	 	    		gtp.put("TAI",TAI);
	 	    		gtp.put("TEID",teid);
	 	    		
	 	    	}
	 	    	point=point+4+ie_length;
	 	      }
	 	      else 
	 	    	 point=point+4+ie_length;
	 		}

	 		
	 		return gtp;
	 	}
	 
	    // Driver Code
//	    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
//	    {   database_manager a = new database_manager();
//	    	
//	    	String gtp_message="48 20 00 40 00 00 00 00 00 00 00 00 01 00 08 00  11 11 11 11 11 11 11 f1 4b 00 08 00  11 11 11 11 11 11 11 f1 56 00 1c 00 0f 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 21 33 21 42 42 ";
//	    	HashMap<String,String> gtp_decoded =decode(gtp_message);
//	    	System.out.println( gtp_decoded);
//	    	a.connectJDBCToAWSEC2(gtp_decoded);
//		 	  }
}
