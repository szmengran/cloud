#!/bin/bash

function progress() {
  local GREEN CLEAN
  GREEN='\033[0;32m'
  CLEAN='\033[0m'
  printf "\n${GREEN}$@  ${CLEAN}\n" >&2
}

set -e

PREFIX=suntak
VERSION=0.0.4

cd ../cloud-suntak/cloud-suntak-reserve
mvn clean package
progress "building cloud-suntak-reserve image ..."

docker tag $(docker build -t ${PREFIX}/cloud-suntak-reserve -q .) ${PREFIX}/cloud-suntak-reserve:${VERSION}
cd -

docker push ${PREFIX}/cloud-suntak-reserve:${VERSION}