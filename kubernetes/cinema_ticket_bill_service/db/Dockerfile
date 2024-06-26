FROM mysql:latest

COPY ./ticket_bill_db.sql /docker-entrypoint-initdb.d/

ENV MYSQL_ROOT_PASSWORD 123456789
ENV MYSQL_DATABASE cinema_ticket_bill_db

EXPOSE 3306