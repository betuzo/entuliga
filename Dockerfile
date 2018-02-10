FROM java:8
VOLUME /tmp
ADD web/build/libs/web-0.0.1.jar app.jar
EXPOSE 8090
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-Dspring.profiles.include=${SPRING_PROFILES_INCLUDE}", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]