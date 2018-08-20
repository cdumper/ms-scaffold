FROM openjdk:8-jre-alpine
COPY *.jar /lib/app.jar
EXPOSE 8082

RUN apk update && apk add nodejs nodejs-npm
COPY nodejs /usr/nodejs
WORKDIR /usr/nodejs
RUN npm install
EXPOSE 3000

CMD node index.js & java -jar /lib/app.jar
