FROM redis

RUN apt-get update && \
    apt-get install -y openjdk-11-jre-headless && \
    apt-get clean;


ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY entrypoint.sh /usr/local/bin/entrypoint.sh
COPY start_server.sh /usr/local/bin/start_server.sh
COPY start_redis.sh /usr/local/bin/start_redis.sh
ENTRYPOINT ["/usr/local/bin/entrypoint.sh"]