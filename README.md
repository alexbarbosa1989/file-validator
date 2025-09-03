# file-validator

--
## Deployment alternatives

[Local Deployment](https://github.com/alexbarbosa1989/file-validator?tab=readme-ov-file#local-deployment)

[Openshift Deployment](https://github.com/alexbarbosa1989/file-validator?tab=readme-ov-file#openshift-deployment)

--

## Local Deployment
**Clone the project**
~~~
git clone https://github.com/alexbarbosa1989/file-validator.git
~~~

**move to the project directory**
~~~
cd file-validator
~~~

**Set the OpenAI API KEY as an environment variable**
~~~
OPENAI_API_KEY=sk-ABCDEFGHI...
~~~

**Use the demo YAML file located in the /demo directory to test the service**
~~~
curl -X POST \
  -H "Content-Type: text/plain" \
  --data-binary @demo/test-file.yaml http://localhost:8080/validate
~~~



## Openshift Deployment

**Create a new project**
~~~
oc new-project ai-file-validator
~~~

**Deploy the applicaition**
~~~
oc new-app registry.redhat.io/ubi8/openjdk-21:latest~https://github.com/alexbarbosa1989/file-validator.git --name=ai-file-validator
~~~

**Create secret with OpenAI API KEY**
~~~
oc create secret generic openai-secret --from-literal=OPENAI_API_KEY=sk-ABCDEFGHI...
~~~

**Inject the secret**
~~~
oc set env deployment/ai-file-validator --from=secret/openai-secret
~~~

**Expose the service**
~~~
oc expose svc/ai-file-validator
~~~

### Test the service
**Clone the project**
~~~
git clone https://github.com/alexbarbosa1989/file-validator.git
~~~

**move to the project directory**
~~~
cd file-validator
~~~

**Use the demo YAML file located in the /demo directory to test the service**
~~~
curl -X POST \
  -H "Content-Type: text/plain" \
  --data-binary @demo/test-file.yaml $OCP_ROUTE/validate
~~~
