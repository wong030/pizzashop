# Verwende das offizielle MySQL-Basisimage
FROM mysql:latest

# Setze Umgebungsvariablen für die MySQL-Datenbank
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=OrderDB
ENV MYSQL_USER=myuser
ENV MYSQL_PASSWORD=mypassword

# Kopiere die MySQLConfig in das Docker-Image
COPY ./conf/* /etc/mysql/conf.d

# Kopiere alle SQL-Skripte aus dem lokalen Verzeichnis "sql-scripts" in das Docker-Image
COPY ./sql-script/orderTables.sql /docker-entrypoint-initdb.d/