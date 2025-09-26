#syntax=docker/dockerfile:1.4
ARG MAVEN_IMAGE=maven:3.9.11-eclipse-temurin-21
ARG RUNTIME_IMAGE=bellsoft/alpaquita-linux-base:glibc

FROM ${MAVEN_IMAGE} AS build

WORKDIR /app

COPY pom.xml ./

RUN --mount=type=cache,id=maven,target=/root/.m2 \
    mvn dependency:go-offline

COPY src ./src

RUN --mount=type=cache,id=maven,target=/root/.m2 \
    mvn -DskipTests clean package

RUN jlink --module-path $JAVA_HOME/jmods --add-modules java.base,java.compiler,\
java.desktop,java.instrument,java.logging,java.management,java.naming,java.net.http,java.prefs,\
java.rmi,java.scripting,java.security.jgss,java.sql,java.xml,jdk.jfr,jdk.unsupported \
--compress=2 --strip-debug --no-header-files --no-man-pages --output /app/jre

FROM ${RUNTIME_IMAGE} AS RUNTIME

RUN addgroup -S app && adduser -S -G app appuser

WORKDIR /app

COPY --chown=appuser:app --from=build /app/jre /jre
COPY --chown=appuser:app --from=build /app/target/app.jar app.jar

EXPOSE 8080

ENTRYPOINT ["/jre/bin/java", "-jar", "app.jar"]
