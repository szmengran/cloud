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
VERSION=0.0.3

cd ../cloud-suntak/cloud-suntak-test
mvn -e clean package
progress "Building cloud-suntak-test image ..."
docker tag $(docker build -t ${REGPREFIX}/cloud-suntak-test -q .) ${REGPREFIX}/cloud-suntak-test:${VERSION}
cd -

docker push ${REGPREFIX}/cloud-suntak-test:${VERSION}