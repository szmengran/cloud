#!/bin/bash

function progress() {
    local GREEN CLEAN
    GREEN='\033[0;32m'
    CLEAN='\033[0m'
    printf "\n${GREEN}$@  ${CLEAN}\n" >&2
}

set -e

# Docker image prefix
REGPREFIX=suntak
VERSION=0.0.7

cd ../cloud-suntak/cloud-suntak-microservices
mvn -e package
progress "Building microservices image ..."
docker tag $(docker build -t ${REGPREFIX}/microservices -q .) ${REGPREFIX}/microservices:${VERSION}
cd -

docker push ${REGPREFIX}/microservices:${VERSION}
