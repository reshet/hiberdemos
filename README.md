In order to run the project, do the following:

1. Instantiate the docker container of local postgresql with the test database. Do `docker rm "container-hash"` if necessary.
2. Do post-db install and create the sars-core-test database with a script.
3. Run dbunit tests wich verify your Hibernate-related database code.
4. Enjoy! 
