FROM openjdk:8
ADD target/springk8s-0.0.1-SNAPSHOT.jar springk8s-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Done-jar.silent=true","-Xms768m", "-Xmx768m", "-jar", "springk8s-0.0.1-SNAPSHOT.jar"]

