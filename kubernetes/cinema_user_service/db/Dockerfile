FROM mysql:latest

COPY ./user_db.sql /docker-entrypoint-initdb.d/

ENV MYSQL_ROOT_PASSWORD 123456789
ENV MYSQL_DATABASE cinema_user_db

EXPOSE 3306