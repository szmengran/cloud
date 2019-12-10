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
VERSION=0.0.4

cd ../cloud-suntak/cloud-suntak-monitor
mvn -e package
progress "Building cloud-suntak-monitor image ..."
docker tag $(docker build -t ${REGPREFIX}/cloud-suntak-monitor -q .) ${REGPREFIX}/cloud-suntak-monitor:${VERSION}
cd -

docker push ${REGPREFIX}/cloud-suntak-monitor:${VERSION}