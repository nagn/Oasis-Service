This project integrates a heroku environment in order to deploy.

In order to connect with a Postgres server, you must define the
JDBC_DATASOURCE_URL environmental variable

To pull from the Heroku Postgres server, you can use set_env.sh.

Otherwise, just set the environmental variables yourself.

Then, to run locally, run heroku local web.

Push to the master branch in order to integrate with the 
Travis CI integration tests. If it passes, it will automatically
be deployed on Heroku.

Note that since the any given URL/credentials are not guarenteed to be stable,
you may need to re-run set_env.sh if you are running locally.
