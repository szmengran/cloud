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

cd ../cloud-common/cloud-common-sms
mvn -e package
progress "Building cloud-common-sms image ..."
docker tag $(docker build -t ${REGPREFIX}/cloud-common-sms -q .) ${REGPREFIX}/cloud-common-sms:${VERSION}
cd -