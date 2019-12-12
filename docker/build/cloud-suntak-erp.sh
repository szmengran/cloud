#!/bash/bin

function progress() {
	local GREEN CLEAN
    GREEN='\033[0;32m'
    CLEAN='\033[0m'
    printf "\n${GREEN}$@  ${CLEAN}\n" >&2
}

set -e

REGPREFIX=suntak
VERSION=0.0.3

cd ../cloud-suntak/cloud-suntak-erp
mvn -e package
progress "Building cloud-suntak-erp image ..."
docker tag $(docker build -t ${REGPREFIX}/cloud-suntak-erp -q .) ${REGPREFIX}/cloud-suntak-erp:${VERSION}
cd -

docker push ${REGPREFIX}/cloud-suntak-erp:${VERSION}