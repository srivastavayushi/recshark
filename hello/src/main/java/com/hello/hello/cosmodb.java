package com.hello.hello;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
public class cosmodb {
//	cosmodb() throws ClassNotFoundException, InvalidKeyException, URISyntaxException
//	{String connectionString = "DefaultEndpointsProtocol=https;AccountName=samsungprismtable;AccountKey=sBcM3RESI0tKExgCXCmp9tzwYoOsUZkFGyduCJGzTDrQkIRx6pphwFB1RrX1jb0RFJRf5rAWwbDmdHpy7anQ9g==;TableEndpoint=https://samsungprismtable.table.cosmos.azure.com:443/;";
//	    CloudStorageAccount storageAccount = CloudStorageAccount.parse(connectionString);
//	    CloudTableClient cloudTableClient = storageAccount.createCloudTableClient();
//}
	public static void main(String args[]) throws SQLException, InvalidKeyException, URISyntaxException {

		final String connectionString = "DefaultEndpointsProtocol=https;AccountName=samsungprismtable;AccountKey=sBcM3RESI0tKExgCXCmp9tzwYoOsUZkFGyduCJGzTDrQkIRx6pphwFB1RrX1jb0RFJRf5rAWwbDmdHpy7anQ9g==;TableEndpoint=https://samsungprismtable.table.cosmos.azure.com:443/;";
		try
		{
		    final String tableName = "Employees";

		    // Create a TableClient with a connection string and a table name.
		     TableClient tableClient = new TableClientBuilder()
		        .connectionString(connectionString)
		        .tableName(tableName)
		        .buildClient();

		    // Create a new employee TableEntity.
		    String partitionKey = "Sales";
		    String rowKey = "0001";
		    Map<String, Object> personalInfo= new HashMap<>();
		    personalInfo.put("FirstName", "Walter");
		    personalInfo.put("LastName", "Harp");
		    personalInfo.put("Email", "Walter@contoso.com");
		    personalInfo.put("PhoneNumber", "425-555-0101");
		    TableEntity employee = new TableEntity(partitionKey, rowKey).setProperties(personalInfo);
		        
		    // Upsert the entity into the table
		    tableClient.upsertEntity(employee);
		}
		catch (Exception e)
		{
		    // Output the stack trace.
		    e.printStackTrace();
		}
	
  }}
