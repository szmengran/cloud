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

cd ../cloud-security/cloud-security-oauth
mvn package
progress "Building cloud-security-oauth image ..."
docker tag $(docker build -t ${REGPREFIX}/cloud-security-oauth -q .) ${REGPREFIX}/cloud-security-oauth:${VERSION}
cd -

docker push ${REGPREFIX}/cloud-security-oauth:${VERSION}