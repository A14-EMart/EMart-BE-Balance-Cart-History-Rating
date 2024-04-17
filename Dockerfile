FROM gradle:jdk17-alpine
ARG PRODUCTION
ARG JDBC_DATABASE_PASSWORD
ARG JDBC_DATABASE_URL
ARG JDBC_DATABASE_USERNAME

ENV PRODUCTION ${PRODUCTION}
ENV JDBC_DATABASE_PASSWORD ${JDBC_DATABASE_PASSWORD}
ENV JDBC_DATABASE_URL ${JDBC_DATABASE_URL}
ENV JDBC_DATABASE_USERNAME ${JDBC_DATABASE_USERNAME}

WORKDIR /app
COPY ./build/libs/EMart-BE-Balance-Cart-History-Rating-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java","-jar","EMart-BE-Balance-Cart-History-Rating-0.0.1-SNAPSHOT.jar"]
