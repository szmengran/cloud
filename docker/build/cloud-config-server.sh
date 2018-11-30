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

cd ../cloud-config/cloud-config-server
mvn package
progress "Building cloud-config-server image ..."
docker tag $(docker build -t ${REGPREFIX}/cloud-config-server -q .) ${REGPREFIX}/cloud-config-server:${VERSION}
cd -
