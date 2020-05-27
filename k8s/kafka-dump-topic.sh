#!/usr/bin/env bash

$KAFKA_HOME/bin/kafka-console-consumer.sh --bootstrap-server kafka-0.kafka:9092 --topic $1
