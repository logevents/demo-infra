#!/usr/bin/env bash

kubectl run -it --image confluentinc/cp-kafkacat --rm kafkacat -- /bin/bash -login
