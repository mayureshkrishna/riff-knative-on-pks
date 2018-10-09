# Knative on Pivotal Container Service using Riff

## Before you begin

This guide walks you through creating a cluster with the correct specifications for Knative on Pivotal Container Service deployed on prem on vSphere.

If you would like to explore Serverless on GKE, you can follow the guide on Project Riff website here: https://projectriff.io/docs/getting-started-with-knative-riff-on-gke/

If you would like to try out locally on Minikube, you can follow the guide on Project Riff website here: https://projectriff.io/docs/getting-started-with-knative-riff-on-minikube/ 

This guide assumes you are using bash in a Mac or Linux environment; some commands will need to be adjusted for use in a Windows environment. This guide also assumes that you have Kubernetes CLI installed.

Knative requires a Kubernetes cluster v1.10 or newer. `kubectl` v1.10 is also
required.  This guide walks you through creating a cluster with the correct
specifications for Knative on Pivotal Container Service.

This guide assumes you are using bash in a Mac or Linux environment; some
commands will need to be adjusted for use in a Windows environment.

**_Note: This section is primarily for Platform Operators and Cluster Admins, and have to setup once_**

### Installing Pivotal Container Service

To install Pivotal Container Service (PKS), follow the documentation at https://docs.pivotal.io/runtimes/pks/1-2/installing-pks.html.

## Creating a Kubernetes cluster

To create a cluster, follow the documentation at https://docs.pivotal.io/runtimes/pks/1-2/create-cluster.html

## Access the cluster

To retrieve your cluster credentials, follow the documentation at https://docs.pivotal.io/runtimes/pks/1-2/cluster-credentials.html.

## Confirm that your kubectl context is pointing to the new cluster

```bash
kubectl config current-context
```

## Create Storage Class Spec for your Cluster on vSphere

You need to use cluster admin context for Storage class and Knative & Serverless riff system installation steps.

```bash
kubectl create -f https://raw.githubusercontent.com/mayureshkrishna/riff-knative-on-pks/master/storage-class-vsphere.yml
```


### Install the riff CLI

The riff CLI is available to download from our GitHub releases page. Once installed, check that the riff CLI version is 0.1.3 or later.

```bash
	riff version
```

At this point it is useful to monitor your cluster using a utility like watch. To install on a Mac

```bash
	brew install watch
```

Watch pods in a separate terminal.

```bash
	watch -n 1 kubectl get pod --all-namespaces
```


### Install Knative and Serverless riff system

Install Knative, watching the pods until everything is running (this could take a couple of minutes). The --node-port option replaces LoadBalancer type services with NodePort.

```bash
	riff system install --node-port
```

You should see pods running in namespaces istio-system, knative-build, knative-serving, and knative-eventing as well as kube-system when the system is fully operational.

```

NAMESPACE          NAME                                         READY     STATUS      RESTARTS   AGE
istio-system       istio-citadel-84fb7985bf-dmc58               1/1       Running     0          12m
istio-system       istio-cleanup-secrets-bnjb9                  0/1       Completed   0          12m
istio-system       istio-egressgateway-bd9fb967d-dr5mp          1/1       Running     1          12m
istio-system       istio-galley-655c4f9ccd-4hr8w                1/1       Running     0          12m
istio-system       istio-ingressgateway-688865c5f7-z9n2d        1/1       Running     1          12m
istio-system       istio-pilot-6cd69dc444-bv9cc                 2/2       Running     0          12m
istio-system       istio-policy-6b9f4697d-hxkqd                 2/2       Running     0          12m
istio-system       istio-sidecar-injector-8975849b4-8mtts       1/1       Running     0          12m
istio-system       istio-statsd-prom-bridge-7f44bb5ddb-r9c8t    1/1       Running     0          12m
istio-system       istio-telemetry-6b5579595f-tdnln             2/2       Running     0          12m
istio-system       knative-ingressgateway-77b757d468-wzh6b      1/1       Running     0          3m
knative-build      build-controller-56f555c8b9-cfrnr            1/1       Running     0          3m
knative-build      build-webhook-868b65dd9-8xddg                1/1       Running     0          3m
knative-eventing   eventing-controller-596c6bc4fd-rshsj         1/1       Running     0          3m
knative-eventing   stub-clusterbus-dispatcher-7b86b64cd-mnssd   2/2       Running     0          56s
knative-eventing   webhook-796b574465-bdv7m                     1/1       Running     0          3m
knative-serving    activator-7ffbdb4f46-lzsrj                   2/2       Running     0          3m
knative-serving    autoscaler-f55c76f7c-pmr2x                   2/2       Running     0          3m
knative-serving    controller-8647f984bf-9vc82                  1/1       Running     0          3m
knative-serving    webhook-896c797cd-lfsfc                      1/1       Running     0          3m
kube-system        etcd-minikube                                1/1       Running     0          12m
kube-system        kube-addon-manager-minikube                  1/1       Running     0          13m
kube-system        kube-apiserver-minikube                      1/1       Running     3          4m
kube-system        kube-controller-manager-minikube             1/1       Running     0          12m
kube-system        kube-dns-86f4d74b45-wxmcd                    3/3       Running     0          13m
kube-system        kube-proxy-mhxdt                             1/1       Running     0          13m
kube-system        kube-scheduler-minikube                      1/1       Running     0          12m
kube-system        kubernetes-dashboard-5498ccf677-bpz7s        1/1       Running     0          13m
kube-system        storage-provisioner                          1/1       Running     0          13m
```

## Initialize the namespace and provide credentials for pushing images to DockerHub

Use the riff CLI to initialize your namespace (if you plan on using a namespace other than default then substitute the name you want to use). This will create a serviceaccount and a secret with the provided credentials and install a buildtemplate. Replace the ??? with your docker username.

```bash
export DOCKER_ID=???
```
```bash
riff namespace init default --dockerhub $DOCKER_ID --manifest https://raw.githubusercontent.com/mayureshkrishna/riff-knative-on-pks/master/manifest/manifest.yaml --secret push-credentials
```

You will be prompted to provide the docker hub password.


## Deploying your function

**_Note: This section is primarily for Developers. And this is all they have to do to get their Function/Service running._**


This step will pull the source code for a function from a GitHub repo, build a container image based on the java function invoker, and push the resulting image to your dockerhub repo.

```bash
riff function create jar ppv --git-repo https://github.com/mayureshkrishna/riff-knative-on-pks.git --artifact ppv.jar --handler "getpayperviewbyid&main=io.pivotal.ppv.PpvApplication"  --image $DOCKER_ID/ppv:latest --verbose  --wait
```

If you’re still watching pods, you should see something like the following

```
NAMESPACE    NAME               READY     STATUS      RESTARTS   AGE
default      ppv-00001-jk9vj    0/1       Init:0/4    0          24s
```

The 4 “Init” containers may take a while to complete the first time a function is built, but eventually that pod should show a status of completed, and a new ppv deployment pod should be running 3/3 containers.


```

NAMESPACE   NAME                                    READY     STATUS      RESTARTS   AGE
default     ppv-00001-deployment-679bffb58c-cpzz8   3/3       Running     0          4m
default     ppv-00001-jk9vj                         0/1       Completed   0          5m
```

### Invoke the function

Since we are using NodePort and don’t have a load balancer, we will invoke the service on one of worker nodes and use knative ingress node port.

```bash
kubectl get -o jsonpath="{.spec.ports[0].nodePort}" services knative-ingressgateway -n istio-system
```

Note down the node port.

```
32380
```

Get Worker Node IP address

```bash
kubectl get nodes -o jsonpath='{ $.items[*].status.addresses[?(@.type=="ExternalIP")].address }'
```
```
10.20.2.8 10.20.2.9 10.20.2.10
```

Invoke function on one of the Worker Nodes

I have an initializer in the code which added some dummy data to H2 db https://raw.githubusercontent.com/mayureshkrishna/riff-knative-on-pks/master/src/main/java/io/pivotal/ppv/Initializer.java

We are going to get data for one of the ids. 
H2 can be replaced by any of database you might have as I'm using spring data jpa to ineract with db.

```java
@Override
	public void run(ApplicationArguments args) throws Exception {
		ppvRepository.save(new PayPerView(null,"UFC-1","UFC: Khabib vs. McGregor","64.99", "10/10/2018"));
		ppvRepository.save(new PayPerView(null,"UFC-2","UFC: Woodley vs. Till","54.99", "10/17/2018"));
		ppvRepository.save(new PayPerView(null,"WWE-1","WWE: Evolution","44.99", "10/13/2018"));
		ppvRepository.save(new PayPerView(null,"SOCCER-1","SOCCER: Peru vs. Chile","24.99", "10/12/2018"));
		ppvRepository.save(new PayPerView(null,"STANDUP-1","STANDUP: Dan Cummins: Don’t Wake The Bear","7.99", "10/15/2018"));
```

```bash
curl http://10.20.2.8:32380/ -H 'Host: ppv.default.example.com' -H 'Content-Type: text/plain' -d 4
```

Host in this curl request is important, and it is composed of 
```
<function name>.<namespace>.<knative domain config (default is example.com - and be changed to your domain in config map)>
```

### Result

```
{"id":4,"name":"SOCCER-1","description":"SOCCER: Peru vs. Chile","price":"24.99","dateOfShowing":"10/12/2018"}
```
## Cleaning up

### Delete the function

```bash
riff service delete ppv
```

### Uninstall Knative and Serverless Riff System

```bash
riff system uninstall
```

To delete the cluster, follow the documentation at https://docs.pivotal.io/runtimes/pks/1-2/delete-cluster.html.