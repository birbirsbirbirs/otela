```shell
j24
export JAVA_TOOL_OPTIONS="-javaagent:./opentelemetry-javaagent.jar"
export OTEL_SERVICE_NAME="otela"
java -jar ../build/libs/otela-0.0.1-SNAPSHOT.jar
```

---

## flight recorder

* /home/b/java-projects/otel-projects/otela/profile/jul1st


---
* vm arguments
  OTEL_SERVICE_NAME=otela;JAVA_TOOL_OPTIONS=-javaagent:/home/b/java-projects/otel-projects/otela/profile/opentelemetry-javaagent.jar