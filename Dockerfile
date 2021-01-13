FROM java:8
EXPOSE 9090
ADD /build/libs/payment-0.0.1-SNAPSHOT.jar payment.jar
ENTRYPOINT ["java", "-jar", "payment.jar"]