# Verwende das offizielle MySQL-Basisimage
FROM mysql:latest

# Setze Umgebungsvariablen für die MySQL-Datenbank
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=UserDB
ENV MYSQL_USER=myuser
ENV MYSQL_PASSWORD=mypassword

# Kopiere die MySQLConfig in das Docker-Image
COPY ./conf/* /etc/mysql/conf.d

# Kopiere SQL-Initialisierungsskripte in das Docker-Image
COPY sql-scripts/userTable.sql /docker-entrypoint-initdb.d/