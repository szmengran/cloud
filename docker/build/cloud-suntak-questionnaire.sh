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

cd ../cloud-suntak/cloud-suntak-questionnaire
mvn -e package
progress "Building cloud-suntak-questionnaire image ..."
docker tag $(docker build -t ${REGPREFIX}/cloud-suntak-questionnaire -q .) ${REGPREFIX}/cloud-suntak-questionnaire:${VERSION}
cd -

docker push ${REGPREFIX}/cloud-suntak-questionnaire:${VERSION}