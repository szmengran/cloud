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
VERSION=0.0.5

cd ../cloud-suntak/cloud-suntak-oa
mvn -e package
progress "Building cloud-suntak-oa image ..."
docker tag $(docker build -t ${REGPREFIX}/cloud-suntak-oa -q .) ${REGPREFIX}/cloud-suntak-oa:${VERSION}
cd -

docker push ${REGPREFIX}/cloud-suntak-oa:${VERSION}