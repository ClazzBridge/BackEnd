
FROM openjdk:17-oracle
ENV HOME_DIR /BackEnd
RUN mkdir -p $HOME_DIR
WORKDIR $HOME_DIR
COPY build/libs/demo-0.0.1-SNAPSHOT.jar /BackEnd/demo.jar
CMD ["java","-jar","demo.jar"]








