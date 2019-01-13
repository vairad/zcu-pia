# Stage 1 - build the app
FROM maven:3.6-jdk-8

COPY ./app /build

WORKDIR /build

RUN mvn clean package

#Stage 2 - Final image
FROM tomcat:8

RUN rm -r /usr/local/tomcat/webapps/*

COPY --from=0 /build/target/*.war /usr/local/tomcat/webapps/ROOT.war
