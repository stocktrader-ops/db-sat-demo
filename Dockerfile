FROM openliberty/open-liberty:21.0.0.9-full-java11-openj9-ubi
# FROM openliberty/open-liberty:kernel-slim-java11-openj9-ubi

# for multi arch build
ARG CREATE_OPENJ9_SCC=false
ENV OPENJ9_SCC=false

COPY --chown=1001:0 /target/liberty/wlp/usr/servers/defaultServer/resources /config/resources/
COPY --chown=1001:0 /src/main/liberty/config/server.xml /config

COPY --chown=1001:0 target/*.war /config/apps

# This script will add the requested XML snippets to enable Liberty features and grow image to be fit-for-purpose using featureUtility. 
# Only available in 'kernel-slim'. The 'full' tag already includes all features for convenience.
# RUN features.sh

RUN configure.sh


# docker build -t guestbook:latest .
# docker tag guestbook:latest quay.io/gas_stocktrader/guestbook:latest
# docker push quay.io/gas_stocktrader/guestbook:latest
# docker run -p 9080:9080 -e PG_HOST=host -e PG_PORT=host -e PG_DBNAME=host -e PG_USER=host -e PG_PASSWORD=host --name guestbook guestbook
# docker run --rm -p 9080:9080 --env-file src\main\liberty\config\server.env  --name guestbook guestbook
