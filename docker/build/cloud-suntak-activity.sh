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

cd ../cloud-suntak/cloud-suntak-activity
mvn -e package
progress "Building cloud-suntak-activity image ..."
docker tag $(docker build -t ${REGPREFIX}/cloud-suntak-activity -q .) ${REGPREFIX}/cloud-suntak-activity:${VERSION}
cd -