# Readme

## Introduction
This is simple __spring boot__ java project explain steps for running it on __k8s__ cluster. 

## Capabilities of this project
- Explain steps about how spring boot application can be deployed as a _K8s Deployment_ is K8s cluster
- Explain how to create and use  Helm chart for custom application.
- Integration of other Helm charts as Sub Chart. it helps breaking application in small pieces. in current case we use _mongodb   helm chart_ from helm stable repository 
- Read  k8s configmap and secret

## Further Possible Improvements
- Utilise Ingress to save cost
- Enable replication for Mongodb (primary ,secondary).
- Use cron job like MGOB for taking backup of mongodb on secondary.

## Important Notes
Althogh applications running on any K8s cluster can run on any k8s platforms e.g. Azure AKS, AWS EKS or other cloud providers like GCP, IBM Bluemix .... or on premises such as minikube but most of the explanation here in the context of deploying it on AWS EKS.

## Prerequisite
- Required
  - Git client :- to clone this project on local development environment.
  - Java 8 or above :- for application development
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
