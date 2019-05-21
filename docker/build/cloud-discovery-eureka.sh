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
VERSION=$CLOUD_VERSION

cd ../cloud-discovery/cloud-discovery-eureka
mvn package
progress "Building cloud-discovery-eureka image ..."
docker tag $(docker build -t ${REGPREFIX}/cloud-discovery-eureka -q .) ${REGPREFIX}/cloud-discovery-eureka:${VERSION}
cd -

docker push ${REGPREFIX}/cloud-discovery-eureka:${VERSION}