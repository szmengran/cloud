#!/bin/bash

function progress() {
    local GREEN CLEAN
    GREEN='\033[0;32m'
    CLEAN='\033[0m'
    printf "\n${GREEN}$@  ${CLEAN}\n" >&2
}

set -e

# Docker image prefix
PREFIX=suntak
VERSION=$CLOUD_VERSION

cd ../cloud-suntak/cloud-suntak-warning
mvn -e package
progress "building warning image ..."
docker tag $(docker build -t ${PREFIX}/warning -q .) ${PREFIX}/warning:${VERSION}
cd -

docker push ${PREFIX}/warning:${VERSION}