#!/bin/bash
rm .env
heroku config:get DATABASE_URL -s >> .env
heroku run echo "'JDBC_DATABASE_URL='\'\$JDBC_DATABASE_URL\'" >> .env 

