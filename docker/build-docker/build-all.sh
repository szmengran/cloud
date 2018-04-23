#!/bin/bash
sh ./build-docker/build-config.sh
sh ./build-docker/build-eureka.sh
sh ./build-docker/build-gateway.sh
sh ./build-docker/build-oauth.sh
sh ./build-docker/build-user.sh
sh ./build-docker/build-sms.sh
sh ./build-docker/build-suntak-ehr.sh
sh ./build-docker/build-suntak-sms.sh
sh ./build-docker/build-suntak-questionnaire.sh