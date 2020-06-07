# demo-infra
Kubernetes deployments to create our example setup (Jenkins, Kafka, Elasticsearch, Filebeat and java transformations)

## Preconditions


* Having a google cloud account created
  * It is sufficient to use the initial free budget
  * See here for google cloud: https://cloud.google.com/
* gcloud tool installed and authenticated
  * General setup instructions: https://cloud.google.com/sdk/install?hl=de
  * Initializiation: https://cloud.google.com/sdk/docs/initializing?hl=de
  * Authorization: https://cloud.google.com/sdk/docs/authorizing?hl=de 
* kubectl is installed
  * https://kubernetes.io/de/docs/tasks/tools/install-kubectl/
* Cloning this repository to your machine

## Using this repo to create the demonstration infrastructure

* creating the cluster by running `1_createCluster.sh`
  * this needs a while till gcloud (gke) creates your new kubernetes cluster
  * When this succeeds kubectl should be configured and `kubectl get po` should work
* run `2_provisionCluster.sh` to setup some kubernetes roles
* run `3_createKafka.sh` to create minimal zookeeper and kafka instance
* run `4_createConfigMapFilebeat.sh` to create filebeat configmap (that contains the information what to scrape from jenkins folders)
* run `4_createJenkins.sh` to create jenkins instance with customized helm chart (jenkins with spring jobs on it)
* run `5_prepareBuildApp.sh` to compile and upload java app (code form here: https://github.com/logevents/demo-streaming-apps)
* run `6_createELK.sh` create all elasticsearch, logstash, kibana components from log transformation
* run `7_transformationApps.sh` to start the compiled app (step 5) 
  
