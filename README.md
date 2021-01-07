Coverage: 34%
# IMS Project

This application is an inventory management system. It can be used to create, read, update and delete customer, inventory item and order records in a persistent SQL database.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

#### Java
In order to run the included prebuilt application, a Java Virtual Machine is required. The Java development kit is required to build the application.

#### MySQL server
The application connects to a MySQL server. The fat jar is configured to connect to a server running on localhost. To run on another server, edit the DB_URL variable in 'src/main/java/com/qa/ims/utils/DBUtils.java'. The application automatically creates a database called "ims" on the server if one doesn't already exist.

The MySQL server installer for Windows can be downloaded from [here](https://dev.mysql.com/downloads/installer/).

```
Give examples
```

### Installing

A step by step series of examples that tell you how to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system. Break down into which tests and what they do

### Unit Tests 

Explain what these tests test, why and how to run them

```
Give an example
```

### Integration Tests 
Explain what these tests test, why and how to run them

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
