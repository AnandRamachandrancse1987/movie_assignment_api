# Movie Assignment

# Modules

### domain

###### This module only contains the classes representing the domain and the logic needed to achieve it.

### domain-implementation

###### This module defines the implementation of the interfaces defined in the domain module.

### finatra-api

###### This modules provides the REST api using finatra.

# How to run

### Flyway migration

###### Run the migration and it should create the schema 

 ```
 sbt flywayMigrate
 ```

### Running the server
```
 sbt run
```
### Testing
```
 sbt test
```
