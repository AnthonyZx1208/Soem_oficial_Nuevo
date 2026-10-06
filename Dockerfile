# Etapa 1: compila los .java y arma el WAR a partir del código fuente.
# No depende de NetBeans/Ant ni de un dist/ generado localmente, así que
# funciona en un build limpio de Railway a partir del repo de GitHub.
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app
COPY web ./web
COPY src/java ./src/java
ADD https://repo1.maven.org/maven2/jakarta/servlet/jakarta.servlet-api/6.0.0/jakarta.servlet-api-6.0.0.jar /opt/jakarta.servlet-api.jar
RUN mkdir -p web/WEB-INF/classes && \
    javac --release 17 -encoding UTF-8 \
        -cp "/opt/jakarta.servlet-api.jar:web/WEB-INF/lib/mysql-connector-java-8.0.12.jar" \
        -d web/WEB-INF/classes \
        $(find src/java -name "*.java") && \
    cd web && jar cf ../app.war .

# Etapa 2: runtime, solo el WAR ya armado sobre Payara Micro.
FROM payara/micro:6.2024.6-jdk17
COPY --from=build /app/app.war /opt/payara/deployments/ROOT.war

EXPOSE 8080

CMD ["--deploy", "/opt/payara/deployments/ROOT.war", "--port", "8080"]
