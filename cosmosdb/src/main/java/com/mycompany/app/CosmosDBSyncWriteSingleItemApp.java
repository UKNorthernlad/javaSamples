package com.mycompany.app;

import java.util.*;
import com.azure.core.credential.TokenCredential;
import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.CosmosContainer;
import com.azure.cosmos.CosmosDatabase;
import com.azure.cosmos.models.*;
import com.azure.cosmos.GatewayConnectionConfig;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A sample Azure application.
 */
public class CosmosDBSyncWriteSingleItemApp {

    private static CosmosClient client;
    private static CosmosDatabase database;
    private static CosmosContainer container;

    private static String databaseName = "mydatabase";
    private static String containerName = "mycontainer";

    protected static Logger logger = LoggerFactory.getLogger(CosmosDBSyncWriteSingleItemApp.class);

    public static void main(String[] args) throws Exception {
        TokenCredential defaultAzureCredential = new DefaultAzureCredentialBuilder().build();
        GatewayConnectionConfig gatewayConnectionConfig = GatewayConnectionConfig.getDefaultConfig();

        client = new CosmosClientBuilder()
            .credential(defaultAzureCredential)
            .endpoint("https://trainingdb99.documents.azure.com:443/")     
            .gatewayMode(gatewayConnectionConfig)
            .buildClient();

            // To create a new database and container requires the user to be a member of a role that has specific permissions.
            // There is no default data plane role that has these.
            //       "Microsoft.DocumentDB/databaseAccounts/sqlDatabases/write",
            //       "Microsoft.DocumentDB/databaseAccounts/sqlDatabases/containers/write"
            // See RBAC Permissions Setup.md for instructions on how to assign the necessary roles to your user.

            createDatabaseIfNotExists();
            createContainerIfNotExists();

            database = client.getDatabase(databaseName);
            container = database.getContainer(containerName);

            // Create a JSON string to insert
            String jsonString = "{\"id\":\"" + UUID.randomUUID().toString() + "\",\"name\":\"John Smith\",\"documentType\":\"person\"}";

            // Convert JSON string to ObjectNode
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode item = objectMapper.readValue(jsonString, ObjectNode.class);

            logger.info("Item to be created: " + item);
         
            // Create the item.
            // Instead of converting JSON to ObjectNode, you can also provide an object that can be serialized to JSON.
            var response =  container.createItem(item,new PartitionKey("person"),new CosmosItemRequestOptions());
            
            logger.info("Done. Item created: " + response.getItem());
    }


     // Database Create
    private static void createDatabaseIfNotExists() throws Exception {
        logger.info("Create database " + databaseName + " if not exists...");

        CosmosDatabaseResponse databaseResponse = null;
        //  Create database if not exists
        try {
             databaseResponse = client.createDatabaseIfNotExists(databaseName);
            
        } catch (Exception e) {
            throw new Exception("Database create failed: " + e.getMessage());
        }
        

        database = client.getDatabase(databaseResponse.getProperties().getId());

        logger.info("Done.");
    }

    // Container create
    private static void createContainerIfNotExists() throws Exception {
        logger.info("Create container " + containerName + " if not exists.");

        //  Create container if not exists
        CosmosContainerProperties containerProperties =
                new CosmosContainerProperties(containerName, "/documentType");

        // Provision throughput - Required for creating a container on a shared RU database.
        // Not required for serverless databases.
        //ThroughputProperties throughputProperties = ThroughputProperties.createManualThroughput(400);

        //  Create container with 200 RU/s
        //CosmosContainerResponse containerResponse = database.createContainerIfNotExists(containerProperties, throughputProperties);
        CosmosContainerResponse containerResponse = database.createContainerIfNotExists(containerProperties);
        container = database.getContainer(containerResponse.getProperties().getId());

        logger.info("Done.");
    }




}
