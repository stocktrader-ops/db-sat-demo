# db-sat-demo

App for satellite demo

Required env settings:

- PG_HOST - hostname
- PG_PORT - db port
- PG_DBNAME - name of the database
- PG_USER - pg username
- PG_PASSWORD - pg password

User should have right to create database schema as it is created on first launch.

# Build and deploy

docker build -t guestbook .
docker tag guestbook:latest quay.io/gas_stocktrader/guestbook:latest
docker push quay.io/gas_stocktrader/guestbook:latest

docker run -p 9080:9080 -e PG_HOST=host -e PG_PORT=host -e PG_DBNAME=host -e PG_USER=host -e PG_PASSWORD=host --name guestbook guestbook
docker run --rm -p 9080:9080 --env-file src\main\liberty\config\server.env  --name guestbook guestbook