package com.hello.hello;

import java.util.function.Consumer;
import com.azure.data.tables.TableClient;
import com.azure.data.tables.TableClientBuilder;
import com.azure.data.tables.models.ListEntitiesOptions;
import com.azure.data.tables.models.TableEntity;
import java.io.Serializable;
import java.util.ArrayList;
public class database_reader {
	


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
			System.out.println("RowKey ge '1648397377077' and RowKey le '1648397771287'");
			
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
//			    System.out.println(tableEntity.getPartitionKey() +
//			        " " + tableEntity.getRowKey() +
//			        "\t" + tableEntity.getProperty("Timestamp") +
//			        "\t" + tableEntity.getProperty("Hexdom") +
//			        "\t" + tableEntity.getProperty("MEI") +
//			        "\t" + tableEntity.getProperty("Message_type") +
//			        "\t" + tableEntity.getProperty("TAI"));
			}
		});
		return datapacket;
	}
	public static void main(String args[])  
	{
		ArrayList<entryinfo> ans= read_data(5,"0111111111111111","111111111111111","1648397377077", "1648397771287");
		for(int i=0;i<5;i++)
		{
			System.out.println(ans.get(i).timestamp);
		}
		
	}
}
