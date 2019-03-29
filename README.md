## Vertx test

##Auto-Deploy

```
mvn compile vertx:run
```

##Run in kubernetes cluster
This will build an image, push the image to dockerhub
and deploy on your kubernetes cluster

```
mvn clean fabric8:deploy
```
To reach the container right now (since the pod is running in an private network)
Create a proxy

``` 
kubectl proxy
```

Run the following to set the env variable 

``` 
export POD_NAME=$(kubectl get pods -o go-template --template '{{range .items}}{{.metadata.name}}{{"\n"}}{{end}}')
```
Reach the container's only exposed endpoint

``` 
curl http://localhost:8001/api/v1/namespaces/default/pods/$POD_NAME/proxy/spacex/engines
```

To undeploy pod

``` 
mvn fabric8:undeploy
```