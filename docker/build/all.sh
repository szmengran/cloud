#!/bin/bash
sh ./build/cloud-config-server.sh
sh ./build/cloud-discovery-eureka.sh
sh ./build/cloud-gateway-zuul.sh
sh ./build/cloud-security-oauth.sh
sh ./build/cloud-admin-user.sh
sh ./build/cloud-common-sms.sh
sh ./build/cloud-suntak-ehr.sh
sh ./build/cloud-suntak-sms.sh
sh ./build/cloud-suntak-questionnaire.sh
sh ./build/cloud-suntak-test.sh
sh ./build/cloud-suntak-activity.sh
sh ./build/cloud-suntak-recruitment.sh
sh ./build/cloud-suntak-wechat.sh
sh ./build/microservices.sh
