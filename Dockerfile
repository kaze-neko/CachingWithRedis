FROM openjdk
ADD /build/libs/CashingWithRedis-0.0.1-SNAPSHOT.jar CashingWithRedis-0.0.1-SNAPSHOT.jar
ADD notes.json notes.json
EXPOSE 8080
#RUN microdnf update
#RUN microdnf install nano sudo -y
ENTRYPOINT ["java", "-jar", "CashingWithRedis-0.0.1-SNAPSHOT.jar"]
