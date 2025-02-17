# How to setup CosmosDB for RBAC access to the data plane

> Taken in part from:
> https://learn.microsoft.com/en-us/azure/cosmos-db/nosql/security/
> https://learn.microsoft.com/en-us/azure/cosmos-db/nosql/security/how-to-grant-data-plane-role-based-access

To access data in CosmosDB using RBAC means having to add the user or service principal to a specific role.

There are 2 out of the box roles on a CosmosDB users can be added to.

* Cosmos DB Built-in Data Contributor
* Cosmos DB Built-in Data Reader

However users/identities cannot be added to the "data plane" via the portal as this only supports adding users for the "control plane". You must instead add users from the command line.

To add a user to a role using their GUID principal ID:
```
az cosmosdb sql role assignment create --account-name trainingdb99
                                       --resource-group training
                                       --scope "/"
                                       --principal-id 38bea182-37d6-492d-81a7-ca28ad28d6e1
                                       --role-definition-name "Cosmos DB Built-in Data Contributor"
```

List all existing sql cosmodb role defintions:
```
az cosmosdb sql role definition list --account-name trainingdb99 --resource-group training
```

## Create a SQL Role Definition

Neither of the built-in roles have the rights to create databases or containers or add items so if you need this you will need to add a custom role, assign it to the database and assign users to the role.

This has the same DataActions permissions (aka rights) as "Cosmos DB Built-in Data Contributor" PLUS
      - Microsoft.DocumentDB/databaseAccounts/sqlDatabases/write
      - Microsoft.DocumentDB/databaseAccounts/sqlDatabases/containers/write

> Taken from https://learn.microsoft.com/en-us/azure/cosmos-db/nosql/security/how-to-grant-data-plane-role-based-access?tabs=custom-definition


1. Create a file called body.json that contains the following:

```json
{
  "Id": "6a8f1ad5-568c-466f-ab1d-4fca387e6d08",
  "RoleName": "TopAdminDude",
  "Type": "CustomRole",
  "AssignableScopes": ["/"],
  "Permissions": [{
    "DataActions": [
      "Microsoft.DocumentDB/databaseAccounts/readMetadata",
      "Microsoft.DocumentDB/databaseAccounts/sqlDatabases/containers/items/read",
      "Microsoft.DocumentDB/databaseAccounts/sqlDatabases/containers/items/create",
      "Microsoft.DocumentDB/databaseAccounts/sqlDatabases/containers/items/delete",
      "Microsoft.DocumentDB/databaseAccounts/sqlDatabases/containers/executeQuery",
      "Microsoft.DocumentDB/databaseAccounts/sqlDatabases/containers/readChangeFeed",
      "Microsoft.DocumentDB/databaseAccounts/sqlDatabases/write",
      "Microsoft.DocumentDB/databaseAccounts/sqlDatabases/containers/write"

    ]
  }]
}
```

2. Create the role.

# The @ sign will read-in the contents of the body.json file automatically when using PowerShell

az cosmosdb sql role definition create --account-name trainingdb99 --resource-group training --body "@body.json"

3. Add a user to the new role:
```
az cosmosdb sql role assignment create --resource-group training --account-name trainingdb99   --principal-id d4c6ad66-ad36-45c6-87bf-943d48ee7be1 --role-definition-name "TopAdminDude" --scope "/"
```
Instead of using `--role-definition-name` it is possible to also use `--role-definition-id`. The data plane resource path at which this Role Assignment is being granted is defined by `--scope`.

Assignable Scopes can be either fully qualified (ie. /subscriptions/subId/resourceGroups/resourceGroupName/providers/Microsoft.DocumentDB/databaseAccounts/accountName/dbs/dbName) or start with the database name (ie. /dbs/dbName).

This will grant the role access over the entire database account.
```json
"AssignableScopes": ["/"]
```

TODO: CHECK THIS IS TRUE: This will grant the role access over a database called "mydb".
```json
"AssignableScopes": ["/dbs/mydb"]
```

TODO: CHECK THIS IS TRUE: This will grant the role access over a container called "mycontainer".
```json
"AssignableScopes": ["/dbs/mydb/colls/mycontainer"]
```



## Clean up

List all existing sql cosmodb role defintions:
```
az cosmosdb sql role definition list --account-name trainingdb99 --resource-group training
```

Get a list of current role assignments. This shows which principals have what role on the db.
```
az cosmosdb sql role assignment list --account-name trainingdb99 --resource-group training
```

Remove an assignment
```
az cosmosdb sql role assignment delete --account-name trainingdb99 --resource-group training  --role-assignment-id 2cb065b9-38f8-4dcb-8aa1-fb6710b6dc2f --no-wait --yes
```

Remove a custom role
```
az cosmosdb sql role definition delete --account-name trainingdb99 --resource-group training --id <custom-role-id-guid>
```
