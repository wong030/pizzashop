FROM open-liberty:kernel-slim
COPY --chown=1001:0 src/main/liberty/config/server.xml /config/

RUN features.sh

COPY --chown=1001:0 target/user.war /config/dropins

EXPOSE 9082

RUN configure.sh