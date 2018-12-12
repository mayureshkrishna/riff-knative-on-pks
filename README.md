# Knative on Pivotal Container Service using riff aka Pivotal Function Service (PFS)

By now, you’ve probably heard all the hype about serverless and functions. It’s a key topic at every industry conference. Vendors are rushing in with new products to address this market. But what is the market exactly? How might functions be applicable to your organization? And finally, how can I start playing around with functions? We aim to answer these questions, so you can get your bearings in this exciting field. One quick note: we’ll use the terms “serverless” and “functions” interchangeably in this post.

Let’s start with market data that indicates the wide-ranging interest in serverless tech.

 - According to the [RightScale 2018 State of the Cloud
   report](https://www.rightscale.com/lp/state-of-the-cloud), serverless
   computing is the fastest-growing type of cloud service with a growth
   rate of 75%. (This same report found that 81% of enterprises have a
   multi-cloud strategy.)

<blockquote class="twitter-tweet" data-lang="en"><p lang="en" dir="ltr">In January, we conducted our seventh annual State of the Cloud Survey of the latest <a href="https://twitter.com/hashtag/cloud?src=hash&amp;ref_src=twsrc%5Etfw">#cloud</a> adoption trends. The complete <a href="https://twitter.com/hashtag/RightScale?src=hash&amp;ref_src=twsrc%5Etfw">#RightScale</a> 2018 State of the Cloud Report is now available for free: <a href="https://t.co/abGSWeuGS8">https://t.co/abGSWeuGS8</a> <a href="https://t.co/BOjFEUfCs0">pic.twitter.com/BOjFEUfCs0</a></p>&mdash; RightScale (@rightscale) <a href="https://twitter.com/rightscale/status/1021447132094582785?ref_src=twsrc%5Etfw">July 23, 2018</a></blockquote>
<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>


 - Digital Ocean’s [Currents 2018
   report](https://www.digitalocean.com/currents/june-2018/) asked
   developers about their usage of serverless. 33% said they had
   deployed applications in a serverless environment. (Interestingly,
   half the respondents said they didn’t have a strong understanding of
   serverless.)

<blockquote class="twitter-tweet" data-lang="en"><p lang="en" dir="ltr">Interested in <a href="https://twitter.com/hashtag/serverless?src=hash&amp;ref_src=twsrc%5Etfw">#serverless</a>? 81% of developers who had not explored serverless in 2017 said they would be researching serverless tech this year. <br><br>Get more dev insights on serverless, containers, job prospects, and more from our quarterly survey, Currents: <a href="https://t.co/jUyRxeA7fd">https://t.co/jUyRxeA7fd</a> <a href="https://t.co/57wYvFwlqq">pic.twitter.com/57wYvFwlqq</a></p>&mdash; DigitalOcean (@digitalocean) <a href="https://twitter.com/digitalocean/status/1017436670973698048?ref_src=twsrc%5Etfw">July 12, 2018</a></blockquote>
<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>


 - In “Where PaaS, Containers and Serverless Stand in a Multi-Platform
   World,” a report from the [Cloud Foundry
   Foundation,](https://www.cloudfoundry.org/multi-platform-trend-report-2018/)
   46% of surveyed companies said they use serverless computing. 39% of
   respondents use PaaS, Containers, and serverless.

<blockquote class="twitter-tweet" data-lang="en"><p lang="en" dir="ltr">[ICYMI] I look at the mix of technologies that must be part of IT infrastructure to support modern, cloud-native applications. A recent <a href="https://twitter.com/cloudfoundry?ref_src=twsrc%5Etfw">@cloudfoundry</a> survey highlights the simultaneous use of <a href="https://twitter.com/hashtag/container?src=hash&amp;ref_src=twsrc%5Etfw">#container</a>, <a href="https://twitter.com/hashtag/serverless?src=hash&amp;ref_src=twsrc%5Etfw">#serverless</a> <a href="https://twitter.com/hashtag/FaaS?src=hash&amp;ref_src=twsrc%5Etfw">#FaaS</a> and more.<a href="https://t.co/kxwPXNYNpF">https://t.co/kxwPXNYNpF</a> <a href="https://t.co/cwNCjZGE4m">pic.twitter.com/cwNCjZGE4m</a></p>&mdash; Kurt Marko🤔🇺🇸 (@krmarko) <a href="https://twitter.com/krmarko/status/1017758555070443520?ref_src=twsrc%5Etfw">July 13, 2018</a></blockquote>
<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>

What can we conclude from this data? Serverless tech is really popular, but not well understood. Serverless has a big role to play as enterprises modernize their software. And the majority of big companies are using multiple clouds.

Most of what you’ve read about serverless so far hits on uses cases in the public cloud. (This is a great way to experiment with serverless with little risk - you should try it out!) What you’ve read less about, though, is why serverless computing is also great in your enterprise data center. That’s right, functions make a ton of sense behind the firewall. We wanted to expand on this excellent piece by  [Dan Baskette](https://twitter.com/dbbaskette)about [The Benefits of On-Premise FaaS](https://content.pivotal.io/serverless-computing/faas-local-the-benefits-of-on-premises-faas), and review some of the emerging scenarios where on-prem functions can be useful.

<blockquote class="twitter-tweet" data-lang="en"><p lang="en" dir="ltr">The benefits of On-premises <a href="https://twitter.com/hashtag/FaaS?src=hash&amp;ref_src=twsrc%5Etfw">#FaaS</a> <a href="https://t.co/kDChWZYX0w">https://t.co/kDChWZYX0w</a> via <a href="https://twitter.com/pivotal?ref_src=twsrc%5Etfw">@pivotal</a></p>&mdash; Rodrigo Gazzaneo (@vGazza) <a href="https://twitter.com/vGazza/status/976822142007414785?ref_src=twsrc%5Etfw">March 22, 2018</a></blockquote>
<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>

Functions are so handy in your data center because that’s where many of your most important apps already run. And because of  [data gravity](https://www.techopedia.com/definition/28768/data-gravity), your most important data lives here as well. If you want to improve these apps with functions, you need a way to run functions on-prem. As an added bonus, you can use your IaaS footprint more intelligently.

Let’s run through a few example scenarios that might illuminate why functions in your data center are so useful.

## How Can My Industry Use Functions in the Data Center?

Glad you asked! Let’s take a look.

### Telecommunications

We’ve all heard of “pay-per-view” (PPV) television, right? Here, a subscriber of a cable provider purchases viewing access to an event via private telecast. The broadcaster shows the event at the same time to everyone who ordered it. (This is different from  [video-on-demand](https://en.wikipedia.org/wiki/Video_on_demand)  systems, where viewers stream recorded content at any time.) Access to an event (usually a sporting event, concert, or movie) can be purchased using an on-screen guide, via the web, IVR, or through a live customer service representative.

There are barely any transactions for PPV events on any given day. But when there is a big game or a boxing match, the load on PPV ordering systems spikes. For example, the  [Mayweather vs McGregor fight in 2017](https://www.forbes.com/sites/brianmazique/2017/12/14/floyd-mayweather-vs-conor-mcgregor-ppv-was-the-second-biggest-ppv-of-all-time/)  was the second biggest PPV event of all time: 4.3 million pay per view buys, grossing over $600 million!

How have enterprises traditionally approached these one-time events? Well, they have to keep a big chunk of infrastructure running 24/7, even when transactions are just trickling on a regular day. Or the Ops team had to scramble a week before the event, and beef up the infrastructure. They needed to scale out additional instances of the application serving PPV ordering system. That’s a lot of capital expense for a sporadic load.

Elastic scenarios like this are tailor-made for serverless. Imagine a PPV order as a function which scales down to 0 on a regular day. This reduces the operational overhead for the data center. Clusters allocated to the PPV function can be used for other workloads when the PPV orders are minimal. And when a big fight happens, this function scales up as much as needed and processes the erratic, unpredictable volume of requests from customers.

These spiky traffic patterns are everywhere, so let’s continue on with a look at health insurance sector.

### Insurance

Many of you receive annual enrollment emails from your health insurance provider. This is a similar pattern to the PPV scenario. Providers will see heavy usage of their systems during the enrollment period, especially during business hours. Before and after the enrollment period, usage is minimal.

### Banking

This pattern of occasional peak loads can be applied to the financial sector as well. Let’s take another example of institutional investing during a company’s  [Initial Public Offering (IPO)](https://www.investopedia.com/university/ipo/ipo.asp). The bank may have an “IPO engine” for when new offerings hit the market. During the frenzy of buying, the functions scale up, and on other days, they scale back to 0. Or consider stock trading platforms, where after the market closes, trading functions scale down to 0. From there, another backend/analytics apps use the infrastructure.

The financial services industry has its fair share of batch jobs. Those are a fit for serverless too.

Every bank runs batch jobs that process nightly ledger  [reconciliation](https://www.investopedia.com/terms/r/reconciliation.asp). These tasks tend to require a fair amount of computing power, even though they run for just a few hours of actual work at night. So the hardware that runs these processes is underutilized. Companies often use legacy scheduling systems for these tasks.

Use functions for this scenario, and you can use your hardware more intelligently and perhaps even retire legacy software license costs. A  `cron`  scheduler can invoke the function every night to scale up and start the ledger reconciliation process. When the processing is complete, the function scales back to 0.

Think of the possibilities: this pattern of scheduled jobs is pervasive across many industries.

### Internet of Things

Serverless computing gives you a very efficient way to invoke functions in response to events and to daisy chain functions together. So how does this relate to IoT?

IoT has plenty of event-driven use cases. Consider a home automation device. You can create all sorts of custom rules on these gadgets. If a motion sensor senses motion, it can trigger an event. This event, in turn, can make several function calls - send a notification to a user, turn on the lights, send an alert to live monitoring agent, or even turn on the alarm siren. And all these functions can be daisy-chained into a workflow!

Similarly, think about the temperature sensors in a manufacturing plant. When the temperature threshold is reached for a machine, the system triggers an event, which calls a function to send an alert to the floor manager and also to invoke another function to disable the affected machine.

Let’s finish up the post with an overview of what Pivotal is doing in the world of functions.

## riffing on Functions

Pivotal is working on [Project riff](http://projectriff.io/)  , and has partnered with Google on open source project [Knative](https://pivotal.io/knative). Together riff and Knative form the foundation of now alpha  [Pivotal Function Service (PFS).](https://pivotal.io/platform/pivotal-function-service)

<blockquote class="twitter-tweet" data-lang="en"><p lang="en" dir="ltr">The first open, multi-cloud serverless platform for the enterprise is here.  Announcing Pivotal Function Service, available as an alpha release today:  <a href="https://t.co/7pPa0fCdoW">https://t.co/7pPa0fCdoW</a></p>&mdash; Pivotal Cloud Foundry (@pivotalcf) <a href="https://twitter.com/pivotalcf/status/1071098265322565632?ref_src=twsrc%5Etfw">December 7, 2018</a></blockquote>
<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>

We want to provide you a functions abstraction that works on any cloud, on-prem and in the public cloud. [PFS](https://pivotal.io/platform/pivotal-function-service) is polyglot and supports authoring of functions in your chosen framework.


## Tutorial: How to Run Functions in Your Data Center

To build functions with riff and Knative, we need a rock-solid Kubernetes dial-tone. There’s nothing better than the  [Pivotal Container Service (PKS)](https://pivotal.io/platform/pivotal-container-service), our Kubernetes offering, so we’ll start there. First, a bit of background.

This guide shows you how to create a cluster with the correct specifications for Knative on Pivotal Container Service deployed atop vSphere.

If you would like to explore Serverless on GKE, you can  [follow the guide here](https://projectriff.io/docs/getting-started-with-knative-riff-on-gke/).

If you would like to try out locally on Minikube, you can  [follow the guide here](https://projectriff.io/docs/getting-started-with-knative-riff-on-minikube/).

This guide assumes you are using bash in a Mac or Linux environment; some commands will need to be adjusted for use in a Windows environment. This guide also assumes that you have [Kubernetes CLI](https://kubernetes.io/docs/tasks/tools/install-kubectl/)installed.

[Here’s the repo with our function code](https://github.com/mayureshkrishna/riff-knative-on-pks/blob/master/src/main/java/io/pivotal/ppv/PpvApplication.java). Now, let’s create our environment.

_**Note: This section is primarily for platform operators and cluster admins.**_


### Install Pivotal Container Service

To install Pivotal Container Service (PKS),  [follow the documentation](https://docs.pivotal.io/runtimes/pks/1-2/#installing). As of PKS 1.2 (Kubernetes v1.11.2, you will have to enable privileged container while creating the  [cluster plans](https://docs.pivotal.io/runtimes/pks/1-2/installing-pks-vsphere.html#plans).

### Create a Kubernetes cluster

To create a cluster,  [follow the documentation](https://docs.pivotal.io/runtimes/pks/1-2/create-cluster.html).

### Access the cluster

To retrieve your cluster credentials,  [follow the documentation](https://docs.pivotal.io/runtimes/pks/1-2/cluster-credentials.html).

### Confirm that your kubectl context is pointing to the new cluster

```bash
kubectl config current-context
```

### Create Storage Class Spec for your Cluster on vSphere

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


## Install Knative and Serverless riff system

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

### Initialize the namespace and provide credentials for pushing images to DockerHub

Use the riff CLI to initialize your namespace (if you plan on using a namespace other than default then substitute the name you want to use). This will create a serviceaccount and a secret with the provided credentials and install a buildtemplate. Replace the ??? with your docker username.

```bash
export DOCKER_ID=???
```
```bash
riff namespace init default --dockerhub $DOCKER_ID --manifest https://raw.githubusercontent.com/mayureshkrishna/riff-knative-on-pks/master/manifest/manifest.yaml --secret push-credentials
```

You will be prompted to provide the docker hub password.

_**Note: This section is primarily for Developers. In this section, we’ll explain what you need to get your function running.**_

### Deploying your function

This step will do four things:

-   Pull the source code for a function from a GitHub repo
    
-   Build a container image based on the java function invoker
    
-   Push the resulting image to your dockerhub repo
    
-   Schedule the containers and serve the function via Knative ingress-gateway

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


Let’s invoke our function on one of the Worker Nodes. We have an  [initializer in the code](https://raw.githubusercontent.com/mayureshkrishna/riff-knative-on-pks/master/src/main/java/io/pivotal/ppv/Initializer.java)  which added some dummy data to the H2 database instance.

We are going to get data for one of the pay per view event. H2 can be replaced by any database you might prefer. In our scenario here, we’re using  [Spring Data JPA](https://spring.io/projects/spring-data-jpa)  to interact with the database.

```java
@Override
	public void run(ApplicationArguments args) throws Exception {
		ppvRepository.save(new PayPerView(null,"UFC-1","UFC: Khabib vs. McGregor","64.99", "10/10/2018"));
		ppvRepository.save(new PayPerView(null,"UFC-2","UFC: Woodley vs. Till","54.99", "10/17/2018"));
		ppvRepository.save(new PayPerView(null,"WWE-1","WWE: Evolution","44.99", "10/13/2018"));
		ppvRepository.save(new PayPerView(null,"SOCCER-1","SOCCER: Peru vs. Chile","24.99", "10/12/2018"));
		ppvRepository.save(new PayPerView(null,"STANDUP-1","STANDUP: Dan Cummins: Don’t Wake The Bear","7.99", "10/15/2018"));
```

You can invoke using curl. Replace the ip address with your worker node’s ip address:

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

To delete the cluster, [follow the documentation](https://docs.pivotal.io/runtimes/pks/1-2/delete-cluster.html).

## Take the Next Step

So we’ve cited some market stats that show that your peers are planning to use serverless quite a bit in the years ahead. We’ve also thrown out a few use cases that illustrate why functions are so darn useful. Finally, we’ve shown you how easy it is to get your code running in production with riff, Knative, and PKS.

Now you should spend a little time with  [projectriff on Github](https://github.com/projectriff/riff)  and  [Knative on Github](https://github.com/knative/).