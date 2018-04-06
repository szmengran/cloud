#!/bin/bash

function progress() {
    local GREEN CLEAN
    GREEN='\033[0;32m'
    CLEAN='\033[0m'
    printf "\n${GREEN}$@  ${CLEAN}\n" >&2
}

set -e

# Docker image prefix
REGPREFIX=szmengran
VERSION=$CLOUD_VERSION

cd ../cloud-admin/cloud-admin-user
mvn package
progress "Building cloud-admin-user image ..."
docker tag $(docker build -t ${REGPREFIX}/cloud-admin-user -q .) ${REGPREFIX}/cloud-admin-user:${VERSION}
cd -