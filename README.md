## Run databasse
To make the database via docker container run 
` docker run --name postgres-docker -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres:15.0-alpine `