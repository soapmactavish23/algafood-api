FROM openjdk:17.0.1-jdk-slim

WORKDIR /app

ARG JAR_FILE

COPY target/${JAR_FILE} /app/api.jar
COPY wait-for-it.sh /wait-for-it.sh

RUN chmod +x /wait-for-it.sh

EXPOSE 8080

CMD ["java", "-jar", "api.jar"]

# LEVANTAR A IMAGEM LOCAL: docker image build -t algafood-api .
# CRIAR UMA REDE COMUM: docker network create --driver bridge algafood-network
# LEVANTAR A IMAGEM MYSQL: docker container run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 --network algafood-network --name algafood-mysql mysql:8.0
# CONECTAR CONTAINERS: docker container run --rm -p 8080:8080 -e DB_HOST=algafood-mysql --network algafood-network algafood-api