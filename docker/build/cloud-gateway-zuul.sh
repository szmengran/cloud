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

cd ../cloud-gateway/cloud-gateway-zuul
mvn package
progress "Building cloud-gateway-zuul image ..."
docker tag $(docker build -t ${REGPREFIX}/cloud-gateway-zuul -q .) ${REGPREFIX}/cloud-gateway-zuul:${VERSION}
cd -

docker push ${REGPREFIX}/cloud-gateway-zuul:${VERSION}