FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD email-digest-0.1.jar email-digest.jar
RUN sh -c 'touch /email-digest.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /email-digest.jar" ]