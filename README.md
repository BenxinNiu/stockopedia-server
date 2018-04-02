# stockopedia-server

# Project Setup (For developers)

1. Please ensure java enviornment by running java -version
2. Install maven if not already installed by running sudo apt-get install maven
3. mvn -version 
4. Install spring boot (If desire to run locally)
5. git clone this project (Make sure you are on master branch) 
6. navigate to project folder
7. mvn clean install or mvn package to build a shippable Target jar (This should build the project)
8. Run locally mvn spring-boot:run (Please make sure you port 8080 is not used)
9. Open browser http://localhost:8080
10. Enjoy coding

# Running a complete built jar

Jar file can be found under Target folder after a successful mvn package run

java -jar target/stockopedia-server-0.0.1-SNAPSHOT.jar    OR 
java -jar "Your jar location"


# App Engine deploy --Run time Java, env: Flex in app.yaml (For developers only)

Make sure version change in appengine-web.xml and config in app.yaml

1. Install gcloud
2. Configure project setting: gcloud init or gcloud config
3. Delete old target jar
4. mvn package
5. mvn appengine:deploy
6. Grab a cup of coffee
