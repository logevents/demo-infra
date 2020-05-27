#!/usr/bin/env bash

$KAFKA_HOME/bin/kafka-consumer-groups.sh --bootstrap-server kafka-0.kafka:9092 --list
