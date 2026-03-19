# === Etapa 1: Compilación ===
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app


# Copiar el pom.xml y descargar dependencias
COPY pom.xml .
RUN mvn dependency:go-offline


# Copiar el código fuente y compilar el proyecto
COPY src ./src
RUN mvn clean package -DskipTests


# === Etapa 2: Ejecución ===
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Crear un usuario y grupo sin privilegios de administrador por seguridad
RUN addgroup -S shopgroup && adduser -S shopuser -G shopgroup

# Copiar solo el archivo JAR compilado desde la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Cambiar la propiedad del archivo al nuevo usuario
RUN chown shopuser:shopgroup app.jar

# Usar el usuario sin privilegios
USER shopuser:shopgroup

# Exponer el puerto
EXPOSE 8080

# Comando de inicio del microservicio
ENTRYPOINT ["java", "-jar", "app.jar"]