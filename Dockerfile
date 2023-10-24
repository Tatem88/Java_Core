FROM bellsoft/liberica-openjdk-alpine:11.0.20.1-1
COPY ./src ./src
RUN mkdir ./out
RUN javac -sourcepath ./src -d out ./src/Main.java
CMD java -classpath ./out Main
