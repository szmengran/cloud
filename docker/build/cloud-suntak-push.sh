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

cd ../cloud-suntak/cloud-suntak-push
mvn -e package
progress "Building cloud-suntak-push image ..."
docker tag $(docker build -t ${REGPREFIX}/cloud-suntak-push -q .) ${REGPREFIX}/cloud-suntak-push:${VERSION}
cd -

docker push ${REGPREFIX}/cloud-suntak-push:${VERSION}