FROM openjdk:8-jre-alpine

ENV JAVA_OPTIONS "" \
		LOGS_DIR /var/log/app \
		APP_DIR /usr/share/app \
		PORT 8090 \
		SERVER_PORT $PORT \
		JAR_NAME web-0.0.1.jar

# Create User java for
# Update apk
# Create directories for logs and for our java binaries
RUN adduser -D java && \
    /bin/sh -c "apk add --no-cache bash"
RUN mkdir $LOGS_DIR $APP_DIR
RUN chown -R java $LOGS_DIR $APP_DIR

# Specify that Logs directory can be mounted
VOLUME $LOGS_DIR

# Exposed Ports
EXPOSE $PORT

# Add an entrypoint
COPY docker/docker-entrypoint.sh /
RUN chmod u+x /docker-entrypoint.sh && \
  chown java /docker-entrypoint.sh
ENTRYPOINT ["/docker-entrypoint.sh"]

# All the remaining actions will be performed as user "java"
USER java

# Add generated binaries
COPY web/build/libs/$JAR_NAME $APP_DIR/bin/

# Command to execute
CMD ["java", "${JAVA_OPTIONS}", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-Dspring.profiles.include=${SPRING_PROFILES_INCLUDE}", "-Djava.security.egd=file:/dev/./urandom", "-jar", "${APP_DIR}/bin/${JAR_NAME}"]