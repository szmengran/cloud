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
VERSION=0.0.6

cd ../cloud-suntak/cloud-suntak-ehr
mvn -e package
progress "Building cloud-suntak-ehr image ..."
docker tag $(docker build -t ${REGPREFIX}/cloud-suntak-ehr -q .) ${REGPREFIX}/cloud-suntak-ehr:${VERSION}
cd -

docker push ${REGPREFIX}/cloud-suntak-ehr:${VERSION}