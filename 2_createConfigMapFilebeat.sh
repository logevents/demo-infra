kubectl create configmap jenkins-filebeat --from-file=k8s/filebeat-cm.yml -o yaml --dry-run | kubectl apply -f -
