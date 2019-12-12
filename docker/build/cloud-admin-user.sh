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

cd ../cloud-admin/cloud-admin-user
mvn -e clean package
progress "Building cloud-admin-user image ..."
docker tag $(docker build -t ${REGPREFIX}/cloud-admin-user -q .) ${REGPREFIX}/cloud-admin-user:${VERSION}
cd -

docker push ${REGPREFIX}/cloud-admin-user:${VERSION}