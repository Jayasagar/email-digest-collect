#!/usr/bin/env bash
echo "java ${JAVA_FLAGS} -jar /srv/email-digest.jar --spring.config.location=/srv/application.properties"
java ${JAVA_FLAGS} -jar /srv/email-digest.jar --spring.config.location=/srv/application.properties