#!/usr/bin/env bash
mvn install:install-file -DgroupId=com.oracle.ojdbc -DartifactId=ojdbc6 -Dversion=11.2.0.4.0 -Dpackaging=jar -Dfile=ojdbc6.jar
mvn install:install-file -DgroupId=com.oracle.ojdbc -DartifactId=ucp -Dversion=11.2.0.3.0 -Dpackaging=jar -Dfile=ucp.jar