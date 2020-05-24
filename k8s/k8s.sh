#!/usr/bin/env bash

file=$1

export CURRENT_TIME=$(date +%Y-%M-%dT%H-%M-%S)
export KAFKA_SERVERS="kafka-0.kafka:9092"
export RAW_TOPIC_PATTERN=filebeat-log-builds-1
export LOGEVENT_TOPIC=logevent-1
export LOGSTASH_VIEW_PATTERN="filebeat-log-builds-1|filebeat-log-slaves-1|logevent-1"
export LOGSTASH_VIEW_KEY_PATTERN="build-1|duration-1"
export ELASTIC_HOST=elastic

cat $1 | envsubst | kubectl apply -f -
