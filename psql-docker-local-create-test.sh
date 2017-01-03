docker run -it --rm --link sars-postgres:postgres postgres psql -h postgres -U sars-core -c 'CREATE DATABASE "sars-core-test";'
