#!/usr/bin/env bash

$KAFKA_HOME/bin/kafka-run-class.sh kafka.tools.GetOffsetShell --broker-list kafka-0.kafka:9092 --topic $1 --time -2
$KAFKA_HOME/bin/kafka-run-class.sh kafka.tools.GetOffsetShell --broker-list kafka-0.kafka:9092 --topic $1 --time -1
