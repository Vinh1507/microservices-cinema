FROM mysql:latest

COPY ./supplier_db.sql /docker-entrypoint-initdb.d/

ENV MYSQL_ROOT_PASSWORD 123456789
ENV MYSQL_DATABASE cinema_supplier_db

EXPOSE 3306