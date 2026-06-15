# ─── Stage 1: Build ───────────────────────────────────────────
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

# Copia o Maven Wrapper primeiro (camada cacheável)
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# Corrige permissão e line endings do mvnw (gerado no Windows)
RUN chmod +x mvnw && sed -i 's/\r$//' mvnw

# Baixa dependências em camada separada (só rebaixa se o pom.xml mudar)
RUN ./mvnw dependency:go-offline -B

# Copia o código-fonte e compila
COPY src ./src
RUN ./mvnw package -DskipTests

# ─── Stage 2: Runtime ─────────────────────────────────────────
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]