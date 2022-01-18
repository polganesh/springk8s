ARG jar_location
FROM openjdk:8
# add jar from target to this location
#ADD target/springk8s-0.0.1-SNAPSHOT.jar springk8s.jar
ADD ${jar_location} springk8s.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Done-jar.silent=true","-Xms768m", "-Xmx768m", "-jar", "springk8s.jar"]

