# mtls-java-tuto

based on https://www.baeldung.com/java-https-client-certificate-authentication

## Generate Certificate

Generate client & server certificate

```shell
cd cert
./generate-server.sh 
./generate-client.sh 
```

Check certificate generated in folder

```shell
ls -l cert/tmp-cert
total 24
-rw-rw-r-- 1 .... client-certificate.pem
-rw-rw-r-- 1 .... clientkeystore.p12
-rw-rw-r-- 1 .... clienttruststore.jks
-rw-rw-r-- 1 .... server-certificate.pem
-rw-rw-r-- 1 .... serverkeystore.p12
-rw-rw-r-- 1 .... servertruststore.jks
```

## Test

Build Java code

```shell
./gradlew clean build
```

Check tasks

```shell
./gradlew tasks
.
.
.
SSL Test tasks
--------------
runClient - run Client
runServer - run Server
.
.
```

Start Server

```shell
./gradlew runServer
.
.
######################
listening for messages...
```

Start Client

```shell
./gradlew runClient
.
.
######################
sending message: Hello World Message
client received 39 bytes: Hello World Message processed by server
.
```

Server terminal will display "__server received 19 bytes: Hello World Message__" after client call and exit.

