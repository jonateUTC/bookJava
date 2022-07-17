# bookJava

[![Build Status](https://travis-ci.org/codecentric/springboot-sample-app.svg?branch=master)](https://travis-ci.org/codecentric/springboot-sample-app)
[![Coverage Status](https://coveralls.io/repos/github/codecentric/springboot-sample-app/badge.svg?branch=master)](https://coveralls.io/github/codecentric/springboot-sample-app?branch=master)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

Minimal [Spring Boot](http://projects.spring.io/spring-boot/) sample app.

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

##Create Database

We create the database named db_book in sqlserver

The database script is located in the path main\java\com\example\demo\sql
```shell
CREATE TABLE [dbo].[book](
	[id_book] [numeric](19, 0) IDENTITY(1,1) NOT NULL,
	[author_book] [varchar](150) NOT NULL,
	[date_publish_book] [datetime] NULL,
	[description_book] [varchar](300) NULL,
	[name_book] [varchar](150) NOT NULL,
	[number_book] [int] NULL,
	[price_book] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_book] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UK_kaofgybrnjm0lgi2rinmx8jjd] UNIQUE NONCLUSTERED 
(
	[name_book] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

```

## Connect sql server database - spring-boot


Modify the file located in the path main\resources\ whose name is application.properties
```shell
spring.datasource.url=jdbc:sqlserver://localhost:1433;database=nameDB;encrypt=true;trustServerCertificate=true
spring.datasource.username=userdb
spring.datasource.password=passworddb
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.SQLServer2008Dialect

```

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `de.codecentric.springbootsample.Application` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
or 
mvn spring-boot:start
```

