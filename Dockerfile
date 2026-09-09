FROM payara/micro:6.2024.6-jdk17

COPY ["dist/SoemOficial_Teriminadisimo.war", "/opt/payara/deployments/ROOT.war"]

EXPOSE 8080

CMD ["--deploy", "/opt/payara/deployments/ROOT.war", "--port", "8080"]
