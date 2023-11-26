FROM timbru31/java-node:17-jdk-18
COPY ./Client /app
COPY ./Server/target/ToDoList-0.0.1-SNAPSHOT.jar /app/ToDoList-0.0.1-SNAPSHOT.jar
COPY ./startup.sh app/startup.sh
WORKDIR /app
RUN chmod +x startup.sh
CMD ./startup.sh
EXPOSE 3000