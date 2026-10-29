# challenge-extra/ — the bonus code that needs third-party libraries

Everything in this folder is deliberately **outside `src/`**, so `mvn compile`
and `mvn test` ignore it.

`challenge-extra/Example1` is the only part of Lab 9 that needs libraries this
project does not ship with. The handout says so on purpose: *"Challenge/Example1
needs additional dependencies in pom.xml (gson, google-api-client, org.json).
Add them yourself — working out what a project needs to build is part of the
exercise."*

## To work on the bonus

1. Add the dependencies to `pom.xml`:

```xml
<dependency>
  <groupId>com.google.api-client</groupId>
  <artifactId>google-api-client</artifactId>
  <version>2.2.0</version>
</dependency>
<dependency>
  <groupId>org.json</groupId>
  <artifactId>json</artifactId>
  <version>20240303</version>
</dependency>
<dependency>
  <groupId>com.google.code.gson</groupId>
  <artifactId>gson</artifactId>
  <version>2.10.1</version>
</dependency>
```

2. Move `Example1/Violation/Forecast.java` into
   `src/main/java/edu/arizona/ece373/lab09/challenge/example1/violation/`.

3. Refactor step by step, committing after each individual improvement. Atomic
   commits, one design change per commit, message naming the principle. Attach a
   screenshot of `git log --oneline --graph`. **The commit history is what is
   graded here**, not just the final state.

