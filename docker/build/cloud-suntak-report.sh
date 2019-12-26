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
VERSION=0.0.8

cd ../cloud-suntak/cloud-suntak-report
mvn -e package
progress "Building cloud-suntak-report image ..."
docker tag $(docker build -t ${REGPREFIX}/cloud-suntak-report -q .) ${REGPREFIX}/cloud-suntak-report:${VERSION}
cd -

docker push ${REGPREFIX}/cloud-suntak-report:${VERSION}