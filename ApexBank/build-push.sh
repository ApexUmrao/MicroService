#!/bin/bash

set -e

# ============================================
# Validate version argument
# ============================================
if [ -z "$1" ]; then
    echo "Usage:"
    echo "./build-push.sh v10"
    exit 1
fi

DOCKER_USER="apexhunt"
VERSION="$1"

SERVICES=(
    configserver
    eurekaserver
    accounts
    loans
    cards
    message
    apigatewayserver
)

echo "========================================="
echo "Building and Pushing Docker Images"
echo "Version: $VERSION"
echo "========================================="

for SERVICE in "${SERVICES[@]}"; do

    echo ""
    echo "========================================="
    echo "Building $SERVICE:$VERSION"
    echo "========================================="

    cd "$SERVICE"

    mvn clean compile jib:dockerBuild \
        -Djib.to.image=$DOCKER_USER/$SERVICE:$VERSION

    docker push $DOCKER_USER/$SERVICE:$VERSION

    cd ..

done

echo ""
echo "========================================="
echo "All Docker images built and pushed!"
echo "========================================="