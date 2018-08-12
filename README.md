# Spring Cloud MicroService Scaffold

This project aims to provide a scaffold for building micro-services using Spring cloud. 
It contains essential components (eureka, config server, feign, zuul, hystrix, ribbon etc.). 
Also it is developed to be deployed in a container-based environment in the beginning, therefore every component/service
is containerized and can be built as separate images. Those images can further be deployed by techniques like docker-compose
and kubernetes providing a complete service.

## Project Structure

The project is built with gradle. There are two main modules: *infrastructures* and *services*.

As the name implies, *infrastructures* contains all the underlying non-business infrastructure components 
in a micro-service structure including config-server, eureka, api gateway etc. 

*Services* contains all the fine-grained services each providing slice of actual business values.
The provided sample service projects includes a jvm and a non-jvm project.
    
+ *product-service* is a standard Java project providing RESTful services. 
    Tech stack: Spring boot, Spring MVC, JPA, flyway, MySQL
+ *nodejs-service* is a demo of how non-jvm project works with Spring Cloud.
    Inside of it there's a nodejs project providing RESTful services. 
    Along with that a [sidecar application](https://cloud.spring.io/spring-cloud-netflix/multi/multi__polyglot_support_with_sidecar.html) is implemented and configured to include the nodejs service in Spring Cloud system.
    It works as a proxy for the nodejs app that cooperates with other components like eureka for service registry and discovery.


## Deployment

Currently the services are supported to be deployed in two ways: docker and kubernetes.

### docker
    > run `sh deploy_docker.sh`
  
  The deploy script does two things: 
    
   + build every single service into separate docker images
   + run docker-compose up against predefined docker-compose.yml file
   
### kubernetes
1. run `./gradlew buildDocker -x test` to build images
2. run `kubectl apply -f deployment/` under each service folder
