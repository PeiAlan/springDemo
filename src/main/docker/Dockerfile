FROM java:8
VOLUME /tmp
ADD spring-demo.jar app.jar
RUN sh -c 'touch /app.jar'
CMD exec java -Djava.security.egd=file:/dev/./urandom -jar /app.jar