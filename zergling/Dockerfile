# alpine linux included openjdk 17
FROM openjdk:17-alpine
# project directory
#ENV workdir="camila"
ENV workdir="zergling"
# set timezone
RUN apk add tzdata && ln -snf /usr/share/zoneinfo/Asia/Seoul /etc/localtime
# create project directory
RUN mkdir ${workdir}
COPY build/libs/*.jar /${workdir}/spring-base.jar
# exec jar without env
#ENTRYPOINT ["java", "-jar", "/camila/spring-base.jar"]
ENTRYPOINT ["sh", "-c", "java -jar /${workdir}/spring-base.jar"]