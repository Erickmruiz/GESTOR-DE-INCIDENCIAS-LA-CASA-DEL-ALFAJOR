# ======================
# FASE 1: COMPILACIÓN
# ======================
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Crear carpeta de trabajo
WORKDIR /app

# Copiar todo el repositorio
COPY . .

# Entrar a la carpeta donde está el pom.xml
WORKDIR /app/demo

# Compilar el proyecto (genera demo-0.0.1-SNAPSHOT.jar)
RUN mvn clean package -DskipTests


# ======================
# FASE 2: EJECUCIÓN
# ======================
FROM eclipse-temurin:17-jdk

# Carpeta de trabajo
WORKDIR /app

# Copiar el JAR generado desde la fase anterior
COPY --from=build /app/demo/target/demo-0.0.1-SNAPSHOT.jar app.jar

# Puerto dinámico que usa Railway
ENV PORT=8080

EXPOSE 8080

# Comando de arranque
CMD ["java", "-jar", "app.jar"]
