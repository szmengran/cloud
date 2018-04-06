#!/bin/bash
sh ./build/build-config.sh
sh ./build/build-eureka.sh
sh ./build/build-gateway.sh
sh ./build/build-oauth.sh
sh ./build/build-user.sh