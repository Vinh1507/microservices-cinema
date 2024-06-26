FROM mysql:latest

COPY ./movie_db.sql /docker-entrypoint-initdb.d/

ENV MYSQL_ROOT_PASSWORD 123456789
ENV MYSQL_DATABASE cinema_movie_show_db

EXPOSE 3306