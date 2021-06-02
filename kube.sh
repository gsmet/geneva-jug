#!/bin/zsh
mvn package -Dquarkus.kubernetes.deploy=true -Dquarkus.profile=kube -DskipTests
oc apply -f target/kubernetes/openshift.yml