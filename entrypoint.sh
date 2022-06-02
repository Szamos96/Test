#!/bin/bash

echo "Start test app"
eval "start_redis.sh & start_server.sh && fg"