FROM open-liberty:kernel-slim-java17-openj9

COPY --chown=1001:0 src/main/liberty/config/server.xml /config/

RUN features.sh

COPY --chown=1001:0 target/procurement.war /config/dropins

EXPOSE 9083

RUN configure.sh