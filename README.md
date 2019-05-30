# Readme

## Introduction
This is simple __spring boot__ java project highlight steps for running it on __k8s__ cluster. 

## Capabilities of this project
- Spring application deployed as a _K8s Deployment_ in K8s cluster
- Utilize Helm chart for custom application. It Provider facility to 
  - Deploy application as single unit in k8s cluster
  - Rollback to earlier version 
- Integration of other Helm charts as Sub Chart. it helps breaking application in small pieces. In current case we use _mongodb   helm chart_ from helm stable repository 
- Use  k8s configmap and secret in Java Spring Boot application. Please refer _com.poc.springk8s.MongoConfig_ and _deployment.yaml_ present inside helm chart of this project 

## Further Possible Improvements
- Utilise Ingress to save cost
- Enable replication for Mongodb (primary ,secondary).
- Use cron job like MGOB for taking backup of mongodb on secondary.
- Possibilities to read secret from _Hashicorp Vault_/_Azure Vault_ etc

## Important Notes
This documentation is mainly concentrate on how to deploy application on AWS EKS,but similar steps can be taken for other cloud providers like Azure, GCP....

## Prerequisite
- Required
  - Git client :- to clone this project on local development environment.
  - Java 8 or above :- for application development, compilation and build as jar file
  - Maven :- build tool for this project. 
  - Docker :- for creating docker image
  - Helm :- helm client for deploying this application as a helm chart.
  - k8s cluster - for current scenario we are using EKS from Amazon.
- Optional
  - if using AWS EKS 
    - AWS account
    - AWS CLI :- to push docker image to AWS ECR
    - EKS cluster :- AWS k8s   
    - AWS IAM authenticator :- required for connecting AWS EKS cluster from local machine
    
## Steps
### Build java project

```
mvn clean install -DskipTests
```
It will create jar file in the _target_ folder of this project 

### Build Docker image

```
docker build -t $USER/poc:dev .
```
- It will create docker image based on _Dockerfile_ present in this project.
- I have created docker image with name poc and tagged it as dev. you are free to use other names and tags

### Push docker image to AWS ECR (optional)
- This step is optional and required only if you are using AWS ECR to store docker images. 
- In our case we are using AWS EKS for deploying our application hence it is needed for it.
- steps for pushing docker image to ECR are available in AWS web console. for more information [refer](https://docs.aws.amazon.com/AmazonECR/latest/userguide/docker-push-ecr-image.html)

### create default helm chart (optional)
- This step is required if we are using helm chart for deploying our application  as helm chart.
- Go to helm chart folder of this project and execute following command. Please note in following case our chart name is __springk8s__. we can provide any other name if needed.
```
helm create springk8s
```
- it will create default helm chart for any use case.

### override values.yaml in helm (required if we are using helm chart)
- for docker image
  - in following case we are 
	- pointing to our docker image present in AWS ECR
	- for testing purpose we are using pullPolicy as _Always_
```
image:
  repository: 228955010194.dkr.ecr.eu-central-1.amazonaws.com/poc
  tag: latest
  pullPolicy: Always
```
- for service
  - we are using service type as _LoadBalancer_
  - port we are using 8080
```
service:
  type: LoadBalancer
  port: 8080
```
- override port for container in deployment.yaml present in template folder
```
containers:
        - name: {{ .Chart.Name }}
          image: "{{ .Values.image.repository }}:{{ .Values.image.tag }}"
          imagePullPolicy: {{ .Values.image.pullPolicy }}
          ports:
            - name: http
              containerPort: 8080
              protocol: TCP
```
### Add mongodb helm chart as sub chart (required if we are using helm chart)
- Add mongodb helm chart from (https://github.com/helm/charts/tree/master/stable/mongodb) in charts folder (i.e. helm-chart/springk8s/charts). for more information refer charts folder inside helm chart of this project
- Add requirement.yaml file in helm chart (i.e. helm-chart/springk8s) of this project. it is helpful for pointing to location of helm chart of sub chart. for our application we are pointing to local file path

```
dependencies:
- name: mongodb
  version: "5.17.4"
  repository: "file://charts/mongodb"
```

### Prepare Secret for Mongodb
- Create secret file (Please note it is not part of helm chart).
```
apiVersion: v1
kind: Secret
metadata:
  name: springk8s
type: Opaque
data:
  mongodb-password: <64 bit encoded password for mongodb normal user>
  mongodb-root-password: <64 bit encoded password for mongodb root user >
```
- apply this secret in k8s cluster with kubectl apply -f _secret file location_

### Values for sub chart( present in values.yaml of umbrella chart)
```
mongodb:
  tag: 4.0.9 
  ## Enable authentication
  ## ref: https://docs.mongodb.com/manual/tutorial/enable-authentication/
  usePassword: true
  pullPolicy: Always
  existingSecret: springk8s
  mongodbUsername: springk8s
  mongodbDatabase: springk8s
  ## Whether enable/disable IPv6 on MongoDB
  ## ref: https://github.com/bitnami/bitnami-docker-mongodb/blob/master/README.md#enabling/disabling-ipv6
  ##
  mongodbEnableIPv6: true
  resources: 
    limits:
      cpu: 500m
      memory: 512Mi
    requests:
     cpu: 100m
     memory: 256Mi
  persistence:
    enabled: true
    storageClass: gp2
    size: 8Gi
  replicaSet:
    ## Whether to create a MongoDB replica set for high availability or not
    enabled: false
    useHostnames: true
    name: rs0
    replicas:
      secondary: 1
      arbiter: 1
  service:
    type: ClusterIP
```
Important values for  subchart
- mongodb.tag :- We are using mongo db version 4.0.9
- mongodb.usePassword - will enable authentication
- mongodb.mongodbUsername - non admin user name
- mongodbDatabase - db name
- existingSecret - name of secret containing admin and non admin password
- mongodb.replicaSet.enabled - false indicates dont create secondary for DB


