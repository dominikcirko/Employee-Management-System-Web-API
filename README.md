# Employee-Management-System-Web-API
To make application runnable, following steps are required:

1. create application.properties file in ./src/main/resources and copy-pase the following contents:

spring.datasource.url=jdbc:postgresql://localhost:5432/ems-db10

spring.datasource.username=postgres

spring.datasource.password=password

spring.liquibase.change-log=classpath:/db/changelog-master.xml

spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=create

spring.liquibase.enabled=true

liquibase.contexts=dev

liquibase.drop-first=false

liquibase.enabled=true

spring.jpa.properties.hibernate.schema_update.unique_constraint_strategy=RECREATE_QUIETLY

2. Sendgrid integration: 
  2.1 Go to SendGrid website and create free account
  2.2 Go to Settings->API keys->Create api key 
  2.3 Create api key with full access 
  2.4 After creating it, copy the key.

3. After you copied the key, in application.properties, create sendgrid.api.key property and paste your key.
4. Download and install PostgreSQL database
  4.1 Create database named "ems-db4" with "postgres" as user and "password" as password
5. I recommend downloading and installing Postman to test API calls

Note: Create departments before creating employees because employee can only be in departments that already exist.

Note 2: When sending email, in "from" field, you have to enter the email you used to create SendGrid account because that email is tied to API key you created.

