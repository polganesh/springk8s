# Readme

## Introduction
This is simple __spring boot__ java project explain steps for running it on __k8s__ cluster. 

## Important Notes
Althogh applications running on any K8s cluster can run on any k8s platforms e.g. Azure AKS, AWS EKS or other cloud providers like GCP, IBM Bluemix .... or on premises such as minikube but most of the explanation here in the context of deploying it on AWS EKS.

## Prerequisite
- Required
  - Java 8 or above
  - maven
  - docker
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
it will create jar file in the _target_ folder of this project 

### Build Docker image

```
docker build -t $user/poc:dev .
```
it will create docker image based on _Dockerfile_ present in this project.

### 


