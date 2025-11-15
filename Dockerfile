# ======================
# FASE 1: BUILD
# ======================
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copiar todo el proyecto
COPY . .

# Compilar el proyecto usando el pom.xml de la raíz
RUN mvn clean package -DskipTests


# ======================
# FASE 2: RUN
# ======================
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copiar el JAR generado
COPY --from=build /app/target/*.jar app.jar

# Puerto de Railway
ENV PORT=8080
EXPOSE 8080

# Arranque
CMD ["java", "-jar", "app.jar"]


