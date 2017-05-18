## PreRequisites

To run the project you will need to have maven and java 8 installed on the machine.

Also install the ActiveMQ.

The application will run at: http://localhost:8080

Access ActiveMQ console at: http://localhost:8161

## Compile application

To compile application execute the cli

```
mvn clean install
``` 

## Run ActiveMQ

Go to ActiveMQ bin folder and run the script:
```
sh activemq console
```
 
## Run the application

```
mvn spring-boot:run
```

## Database information

Wmbedded in-memory database H2 was used in the application

And as a consequence of the nature of the in-memory database, each time the application are finalized, the data set is excluded along with the schemas

Login to the H2 console (JDBC URL: 'jdbc:h2:mem:embedded', user = 'h2')
```
http://localhost:8080/h2-console
``` 

## Examples of specified operations

1 - Create one vehicle
```
curl --request POST  --url http://localhost:8080/api/vehicle --header 'content-type: application/json' --data '{"name":"Subaru"}' 
```
2 - Create several vehicles
```
curl --request POST  --url http://localhost:8080/api/vehicle/vehicles --header 'content-type: application/json' --data '[{"name":"Subaru"}, {"name":"Ferrari"}, {"name":"Tesla"}]'
```
3 - Get all vehicles
```
curl --request GET  --url http://localhost:8080/api/vehicle
```
4 - Get a specific vehicle
```
curl --request GET  --url http://localhost:8080/api/vehicle/10
```
5 - Move a vehicle
```
curl --request PUT --url http://localhost:8080/api/movement/vehicle/10?direction=LEFT 
```
```
curl --request PUT --url http://localhost:8080/api/movement/vehicle/10?direction=RIGHT
```
6 - Get the last movement of a specific vehicle
```
curl --request GET --url http://localhost:8080/api/movement/vehicle/10 
```

## Additional comments