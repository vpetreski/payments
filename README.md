# Payments

This is implementation of imaginary Payment API defined in [this document](task.pdf).

## Requirements
- Java 11
- Maven
- Docker / Compose

## Usage
```shell
# Make all shell scripts executable
chmod 775 *.sh

# RUN script will do a Maven build and run Docker containers
./run.sh
# At this point you can open: http://localhost:8080/doc
# And start using the API.

# Also we can now run the client script in another terminal:
# CLIENT script will execute client simulation - creation of 10 payments.
./client.sh 
# In real life scenario we would probably create CLI tool using some of the existing tools, so that customer can use it via command line.

# TEST script will run existing tests via Maven
./test.sh

# LOCAL script will bring necessary Docker services for local development
./local.sh
```

## Architecture
In the real life, production, scenario - system would be architected to first accept incoming request in a queue or streaming broker and return accepted response to the client immediately.

Then, once we have the request safe, we can independently scale worker compute based on the number of messages in the queue / streaming broker.

This approach would both help us to 1) scale properly and 2) to make sure that even if some worker node fails, message will stay in the queue / streaming broker until other worker processes it correctly, with all necessary steps in the pipeline.

DevOps is needed for properly scaling the infrastructure, stress testing, analyzing existing compute, DB and network bottlenecks and limits, monitoring, logging and then based on estimated number of requests scale properly and test it. Details of this process depend on the existing architecture / infra of the system.

Additionally, client is sending the key with each request uniquely representing the transaction. If client sends the same key / request again, before processing - we are first checking existence in the queue and also in the DB. Only if it's not there, we will continue normal workflow processing, otherwise return already existing object. This way we make sure not to process single transaction more than once.

## Notes
- Redis was used instead of Kafka for simplicity purposes
- In real life project we would use migrations with Flyway
- In real life scenario we would better organize requests and data structures, probably in multiple DB tables
- Validation could be improved
