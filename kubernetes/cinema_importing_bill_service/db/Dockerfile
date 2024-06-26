FROM mysql:latest

COPY ./importing_bill_db.sql /docker-entrypoint-initdb.d/

ENV MYSQL_ROOT_PASSWORD 123456789
ENV MYSQL_DATABASE cinema_importing_bill_db

EXPOSE 3306