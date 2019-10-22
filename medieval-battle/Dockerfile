FROM anapsix/alpine-java
EXPOSE 8080
ARG JAR_FILE=medieval-battle/target/medievalbattle-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} medieval.jar
ENTRYPOINT ["java","-jar","medieval.jar"]

