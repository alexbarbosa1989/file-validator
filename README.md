# file-validator

**Create a new project**
~~~
oc new-project ai-file-validator
~~~

**Deploy the applicaition**
~~~
oc new-app registry.redhat.io/quarkus/mandrel-for-jdk-21-rhel8:latest~https://github.com/alexbarbosa1989/file-validator.git --name=ai-file-validator
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
oc expose svc/quarkus-ai-validator
~~~
