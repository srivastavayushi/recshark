package com.hello.hello;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.time.Instant;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.table.CloudTableClient;



//Include the following imports to use table APIs
import com.azure.data.tables.TableClient;
import com.azure.data.tables.TableClientBuilder;
import com.azure.data.tables.TableServiceClient;
import com.azure.data.tables.TableServiceClientBuilder;
import com.azure.data.tables.models.ListEntitiesOptions;
import com.azure.data.tables.models.TableEntity;
import com.azure.data.tables.models.TableEntityUpdateMode;
import com.azure.data.tables.models.TableTransactionAction;
import com.azure.data.tables.models.TableTransactionActionType;

public class database_manager {
	
	static String connectionString = "";
	database_manager()
	{
 connectionString = "DefaultEndpointsProtocol=https;AccountName=samsungprismtable;AccountKey=sBcM3RESI0tKExgCXCmp9tzwYoOsUZkFGyduCJGzTDrQkIRx6pphwFB1RrX1jb0RFJRf5rAWwbDmdHpy7anQ9g==;TableEndpoint=https://samsungprismtable.table.cosmos.azure.com:443/;";
	}

public static void write_data(HashMap<String,String> gtp_decoded) throws SQLException {
//
// 	    System.out.println("----MySQL JDBC Connection Testing -------");
//
// 	    if (connection != null) {
// 	        System.out.println("SUCCESS!!!! You made it, take control     your database now!");
// 	       Calendar calendar = Calendar.getInstance();
//	       java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
//	 	  String query = " insert into gtp_decoded (hexdom,message_type, IMSI, RequestTimeStamp, MEI, TAI)  values ( '"
//	                                 + gtp_decoded.get("hexdom")+"','" +gtp_decoded.get("message_type")+"','"+ gtp_decoded.get("IMSI")+ "','"+gtp_decoded.get("RequestTimeStamp")+ "','"+gtp_decoded.get("MEI")+"','"+ gtp_decoded.get("TAI")+"')";
//	 	  PreparedStatement preparedStmt = connection.prepareStatement(query);
//	 	  preparedStmt.execute();
//	 	 connection.close();
//	 	   } else {
// 	        System.out.println("FAILURE! Failed to make connection!");
// 	    }
//
// 	   
//
// 	}
try
		{
		    final String tableName = "tab1";
		    String partitionKey, rowKey,imsi,teid;
		    
		    if (gtp_decoded.get("message_type").toString()=="32")
		    {
		    	TableClient tableClient = new TableClientBuilder()
				        .connectionString(connectionString)
				        .tableName("imsi_teid")
				        .buildClient();
		    	partitionKey = gtp_decoded.get("TEID");
		    	rowKey =gtp_decoded.get("IMSI");
		    	teid=partitionKey;
		    	imsi=rowKey;
		    	TableEntity employee = new TableEntity(partitionKey, rowKey);
		    	
		    }
		    if (gtp_decoded.get("message_type").toString()=="33")
		    {
		    	TableClient tableClient = new TableClientBuilder()
				        .connectionString(connectionString)
				        .tableName("imsi_teid")
				        .buildClient();
		    	partitionKey = gtp_decoded.get("TEID");
		    	
		    	ListEntitiesOptions options = null;
		    	String arr[]= {"hello"};
		    	options = new ListEntitiesOptions().setFilter("PartitionKey eq '"+partitionKey+"'");	
		    	tableClient.listEntities(options, null, null).forEach(new Consumer<TableEntity>() {
		    		
		    		public void accept(TableEntity tableEntity) {
		    			
		    			arr[0]=tableEntity.getRowKey();
		    			
//		    		    System.out.println(tableEntity.getPartitionKey() +
//		    		        " " + tableEntity.getRowKey() +
//		    		        "\t" + tableEntity.getProperty("Timestamp") +
//		    		        "\t" + tableEntity.getProperty("Hexdom") +
//		    		        "\t" + tableEntity.getProperty("MEI") +
//		    		        "\t" + tableEntity.getProperty("Message_type") +
//		    		        "\t" + tableEntity.getProperty("TAI"));
		    		}
		    		
		    	});
		    	rowKey=arr[0];
		    	teid=partitionKey;
		    	imsi=rowKey;
		    }
		    if (gtp_decoded.get("message_type").toString()=="34")
		    {
		    	partitionKey = gtp_decoded.get("TEID");
		    	teid=partitionKey;
		    	imsi=gtp_decoded.get("IMSI");
		    }
		    if (gtp_decoded.get("message_type").toString()=="35")
		    {
		    	TableClient tableClient = new TableClientBuilder()
				        .connectionString(connectionString)
				        .tableName("imsi_teid")
				        .buildClient();
		    	partitionKey = gtp_decoded.get("TEID");
		    	
		    	ListEntitiesOptions options = null;
		    	String arr[]= {"hello"};
		    	options = new ListEntitiesOptions().setFilter("PartitionKey eq '"+partitionKey+"'");	
		    	tableClient.listEntities(options, null, null).forEach(new Consumer<TableEntity>() {
		    		
		    		public void accept(TableEntity tableEntity) {
		    			
		    			arr[0]=tableEntity.getRowKey();
		    			
//		    		    System.out.println(tableEntity.getPartitionKey() +
//		    		        " " + tableEntity.getRowKey() +
//		    		        "\t" + tableEntity.getProperty("Timestamp") +
//		    		        "\t" + tableEntity.getProperty("Hexdom") +
//		    		        "\t" + tableEntity.getProperty("MEI") +
//		    		        "\t" + tableEntity.getProperty("Message_type") +
//		    		        "\t" + tableEntity.getProperty("TAI"));
		    		}
		    		
		    	});
		    	rowKey=arr[0];
		    	teid=partitionKey;
		    	imsi=rowKey;
		    	tableClient.deleteEntity(teid, imsi);
		    }
		    // Create a TableClient with a connection string and a table name.
		     TableClient tableClient = new TableClientBuilder()
		        .connectionString(connectionString)
		        .tableName(tableName)
		        .buildClient();
		     String tim = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss").format(Calendar.getInstance().getTime());
		    // Create a new employee TableEntity.
		    partitionKey = gtp_decoded.get("IMSI");
		    
		    rowKey = String.valueOf(Instant.now().toEpochMilli());
		    Map<String, Object> personalInfo= new HashMap<>();
		    personalInfo.put("MEI",gtp_decoded.get("MEI"));
		    personalInfo.put("TAI", gtp_decoded.get("TAI"));
		    personalInfo.put("Hexdom", gtp_decoded.get("hexdom"));
		    personalInfo.put("Message_type", gtp_decoded.get("message_type"));
		    personalInfo.put("TEID", gtp_decoded.get("TEID"));
		    TableEntity employee = new TableEntity(partitionKey, rowKey).setProperties(personalInfo);
		    
		  
		    // Upsert the entity into the table
		    tableClient.upsertEntity(employee);
		    
		}
		catch (Exception e)
		{
		    // Output the stack trace.
		    e.printStackTrace();
		}
 	
}

public static ArrayList<entryinfo> read_data(int queryno, String imsi, String mei,String starttime,String endtime)
{ArrayList<entryinfo> datapacket = new ArrayList<>();
	String connectionString =  "DefaultEndpointsProtocol=https;AccountName=samsungprismtable;AccountKey=sBcM3RESI0tKExgCXCmp9tzwYoOsUZkFGyduCJGzTDrQkIRx6pphwFB1RrX1jb0RFJRf5rAWwbDmdHpy7anQ9g==;TableEndpoint=https://samsungprismtable.table.cosmos.azure.com:443/;";
String tableName = "tab1";
	TableClient tableClient = new TableClientBuilder()
.connectionString(connectionString)
.tableName(tableName)
.buildClient();
	int k=1;
	ListEntitiesOptions options = null;
		
		if (queryno==1)
		{k=0;
		options = new ListEntitiesOptions().setFilter("RowKey ge '"+starttime+"' and RowKey le '"+endtime+"'");	
		}
		else if (queryno==2)
		{k=0;
		options = new ListEntitiesOptions().setFilter("RowKey ge '"+starttime+"' and RowKey le '"+endtime+"' and PartitionKey eq '"+imsi+"'");	
		}
		else if (queryno==3)
		{k=0;
		 options = new ListEntitiesOptions().setFilter("RowKey ge '"+starttime+"' and RowKey le '"+endtime+"' and MEI eq '"+mei+"'");	
		}
		else if (queryno==4)
		{k=0;
		 options = new ListEntitiesOptions().setFilter("RowKey ge '"+starttime+"' and RowKey le '"+endtime+"' and PartitionKey eq '"+imsi+"' and MEI eq '"+mei+"'");	
		}
	if (k==1)
		return datapacket ;
	entryinfo objj =new entryinfo();
	tableClient.listEntities(options, null, null).forEach(new Consumer<TableEntity>() {
		
		public void accept(TableEntity tableEntity) {
			
			objj.imsi=tableEntity.getPartitionKey();
			objj.mei=(String) tableEntity.getProperty("MEI");
			objj.hexdom= (String) tableEntity.getProperty("Hexdom");
			objj.timestamp= tableEntity.getProperty("Timestamp");
			objj.message_type=(String) tableEntity.getProperty("Message_type");
			objj.tai=(String) tableEntity.getProperty("TAI");
			datapacket.add(objj);
//		    System.out.println(tableEntity.getPartitionKey() +
//		        " " + tableEntity.getRowKey() +
//		        "\t" + tableEntity.getProperty("Timestamp") +
//		        "\t" + tableEntity.getProperty("Hexdom") +
//		        "\t" + tableEntity.getProperty("MEI") +
//		        "\t" + tableEntity.getProperty("Message_type") +
//		        "\t" + tableEntity.getProperty("TAI"));
		}
	});
	return datapacket;
}

}

