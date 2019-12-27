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
VERSION=0.0.18

cd ../cloud-suntak/cloud-suntak-ems
mvn -e package
progress "Building cloud-suntak-ems image ..."
docker tag $(docker build -t ${REGPREFIX}/cloud-suntak-ems -q .) ${REGPREFIX}/cloud-suntak-ems:${VERSION}

docker push ${REGPREFIX}/cloud-suntak-ems:${VERSION}