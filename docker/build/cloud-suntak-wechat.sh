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
VERSION=0.0.8

cd ../cloud-suntak/cloud-suntak-wechat
mvn -e package
progress "Building cloud-suntak-wechat image ..."
docker tag $(docker build -t ${REGPREFIX}/cloud-suntak-wechat -q .) ${REGPREFIX}/cloud-suntak-wechat:${VERSION}
cd -

docker push ${REGPREFIX}/cloud-suntak-wechat:${VERSION}