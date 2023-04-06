FROM openjdk:11-jre
ARG JAR_FILE=target/*.jar 
COPY ${JAR_FILE} app.jar
VOLUME ['/etc']
ENV TZ=Asia/Seoul
ENTRYPOINT ["java","-jar", "/app.jar"]
