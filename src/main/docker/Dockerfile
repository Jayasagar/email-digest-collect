FROM frolvlad/alpine-oraclejdk8:slim

ADD build/libs/email-digest-0.1.jar /srv/email-digest.jar

RUN sh -c 'touch /srv/email-digest.jar'

COPY ./docker-entrypoint.sh /

COPY ./application.properties /srv/

ENTRYPOINT ["/docker-entrypoint.sh"]