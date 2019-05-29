# Readme

## Introduction
This is simple __spring boot__ java project explain steps for running it on __k8s__ cluster. 

## Important Notes
Althogh applications running on any K8s cluster can run on any k8s platforms e.g. Azure AKS, AWS EKS or other cloud providers like GCP, IBM Bluemix .... or on premises such as minikube but most of the explanation here in the context of deploying it on AWS EKS.

## Prerequisite
- Required
  - Git client :- to clone this project on local development environment.
  - Java 8 or above :- for application development
  - Maven :- build tool for this project. 
  - Docker :- for creating docker image
  - Helm :- helm client for deploying this application as a helm chart.
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
docker build -t $user/poc:dev .
```
- It will create docker image based on _Dockerfile_ present in this project.
- I have created docker image with name poc and tagged it as dev. you are free to use other names and tags

### Push docker image to AWS ECR (optional)
- This step is optional and required only if you are using AWS ECR to store docker images. 
- In our case we are using AWS EKS for deploying our application hence it is needed for it.
- steps for pushing docker image to ECR are available in AWS web console. for more information [refer](https://docs.aws.amazon.com/AmazonECR/latest/userguide/docker-push-ecr-image.html)


