# Приклад Java-програми ведення екологічних записів з використанням СКБД MongoDB
## Кроки для виконання
1. Завантажте і встановіть Java Development Kit 17 (або новішу версію) для Windows.
1. Завантажте Maven з https://dlcdn.apache.org/maven/maven-3/3.8.9/binaries/apache-maven-3.8.9-bin.zip і розпакуйте його на локальний комп'ютер.
1. В Windows в параметрах системи додайте системну змінну MAVEN_HOME=<шлях до папки з Maven>
1. В Windows в параметрах системи додайте `;<шлях до папки з Maven>\bin` в системну змінну PATH.
1. Встановіть СКБД MongoDB Community Server вибрати варіант установки Complete -> "Run service as Network Service user"
1. Встановіть MongoDB Compass (GUI).
1. Створіть базу даних `ecology-db` і колекцію в ній `ecology`.
1. Зберіть програму використовуючи команду `mvn clean install`.
1. Запустіть програму за допомогою команди `mvn spring-boot:run`.
1. Відкрийте веб-браузер і перейдіть за адресою `http://localhost:8081` для перегляду записів.

## Веб-інтерфейс

Після запуску програми веб-інтерфейс доступний у браузері за адресою `http://localhost:8081`.

Сторінки:
1. **Головна сторінка** (`http://localhost:8081`) — відображає розклад екологічних записів у вигляді таблиці з усіма колонками.
1. **Додати запис** (`http://localhost:8081/add`) — форма для додавання нового рядка до записів.

## Docker

Локальне збирання образу Docker-контейнера:

```bash
docker build -f deploy/Dockerfile -t ecology .
```

Локальний запуск образу Docker-контейнера:

```bash
docker run --rm -p 8081:8081 -e MONGO_URI=mongodb://host.docker.internal:27017/ecology-db ecology
```

Після запуску застосунок буде доступний за адресою `http://localhost:8081`.

Для збірки з основної гілки як тег образу Docker-контейнера використовується значення `version` з `pom.xml`, наприклад `0.3.0-snapshot`.
Тег `latest` публікується лише тоді, коли значення `version` у `pom.xml` не містить `SNAPSHOT`.


Перед тим, як завантажити образ, потрібно залогінитись в GitHub Package використовуючи свій GitHub-токен. Цей токен повинен мати дозвіл `read:packages`, бути створеним для того самого користувача GitHub, ім'я якого вказано в команді `docker login`, і цей користувач повинен мати принаймні read-доступ до пакета.

```cmd
set GITHUB_TOKEN=your-github-token
echo %GITHUB_TOKEN% | docker login ghcr.io -u your-github-username --password-stdin

docker pull ghcr.io/ecology-csbc/ecology:0.3.0-snapshot
```

Запуск контейнера з образу Docker-контейнера, опублікованого у GitHub Packages:

```bash
docker run --rm -p 8081:8081 -e MONGO_URI=mongodb://host.docker.internal:27017/ecology-db ghcr.io/ecology-csbc/ecology:0.3.0-snapshot
```

## Налаштування `MONGO_URI`

За замовчуванням програма використовує адресу `mongodb://localhost:27017/ecology-db` для підключення до локальної бази даних.


### Підключення до MongoDB Atlas

Для підключення MongoDB Atlas потрібно записати connection string кластера в змінну середовища `MONGO_URI` наприклад:

```text
mongodb+srv://<db-username>:<db-password>@<cluster-url>/ecology-db?retryWrites=true&w=majority
```

Порядок налаштування:
1. Створіть кластер у MongoDB Atlas (тариф Free).
1. Створіть користувача бази даних у розділі Database Access і збережіть пароль.
1. Скопіюйте connection string з кнопки Connect -> Drivers і збережіть його.
1. Створіть базу даних `ecology-db` і колекцію `ecology` в розділі Data Explorer.
1. Підставте в URI свої `username`, `password` і назву бази даних `ecology-db` та збережіть у змінній середовища `MONGO_URI`.
1. Визначте зовнішню IP-адресу (`curl -s https://api.ipify.org && echo`) і додайте її в Network Access.

Приклад запуску з MongoDB Atlas.

Windows Command Prompt:

```cmd
set MONGO_URI=mongodb+srv://db-username:db-password@cluster0.abcde.mongodb.net/ecology-db?retryWrites=true^&w=majority
mvn spring-boot:run
```

PowerShell:

```powershell
$env:MONGO_URI="mongodb+srv://db-username:db-password@cluster0.abcde.mongodb.net/ecology-db?retryWrites=true&w=majority"
mvn spring-boot:run
```

Linux/macOS:

```bash
export MONGO_URI="mongodb+srv://db-username:db-password@cluster0.abcde.mongodb.net/ecology-db?retryWrites=true&w=majority"
mvn spring-boot:run
```

Примітка: якщо пароль містить спеціальні символи, їх потрібно URL-кодувати в connection string. Наприклад, символ `@` потрібно замінити на `%40`.

## Запуск тестів

Локальні команди Maven:
1. Лише unit-тести:
`mvn test`
2. Лише integration-тести:
`mvn -DskipUnitTests=true verify`
3. Повна перевірка перед merge або release:
`mvn verify`

Примітка: integration-тести використовують **Testcontainers MongoDB**. Для локального запуску `mvn verify` має бути запущений Docker Desktop.

## Результати виконання програми
```
mvn clean install
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------< com.ecology:ecology >-------------------
[INFO] Building ecology 0.2.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[WARNING] 1 problem was encountered while building the effective model for org.javassist:javassist:jar:3.20.0-GA during dependency collection step for project (use -X to see details)
[INFO]
[INFO] --- clean:3.2.0:clean (default-clean) @ ecology ---
[INFO] Deleting C:\GitHub\ecology\target
[INFO]
[INFO] --- checkstyle:3.3.1:check (checkstyle-validate) @ ecology ---
[INFO] Starting audit...
Audit done.
[INFO] You have 0 Checkstyle violations.
[INFO]
[INFO] --- jacoco:0.8.12:prepare-agent (prepare-agent) @ ecology ---
[INFO] argLine set to -javaagent:C:\\Users\\dmitr\\.m2\\repository\\org\\jacoco\\org.jacoco.agent\\0.8.12\\org.jacoco.agent-0.8.12-runtime.jar=destfile=C:\\GitHub\\ecology\\target\\jacoco.exec
[INFO]
[INFO] --- resources:3.3.1:resources (default-resources) @ ecology ---
[INFO] Copying 4 resources from src\main\resources to target\classes
[INFO]
[INFO] --- compiler:3.13.0:compile (default-compile) @ ecology ---
[INFO] Recompiling the module because of changed source code.
[INFO] Compiling 4 source files with javac [debug target 17] to target\classes
[WARNING] location of system modules is not set in conjunction with -source 17
  not setting the location of system modules may lead to class files that cannot run on JDK 17
    --release 17 is recommended instead of -source 17 -target 17 because it sets the location of system modules automatically
[INFO]
[INFO] --- resources:3.3.1:testResources (default-testResources) @ ecology ---
[INFO] skip non existing resourceDirectory C:\GitHub\ecology\src\test\resources
[INFO]
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ ecology ---
[INFO] Recompiling the module because of changed dependency.
[INFO] Compiling 6 source files with javac [debug target 17] to target\test-classes
[WARNING] location of system modules is not set in conjunction with -source 17
  not setting the location of system modules may lead to class files that cannot run on JDK 17
    --release 17 is recommended instead of -source 17 -target 17 because it sets the location of system modules automatically
[INFO]
[INFO] --- surefire:3.5.1:test (default-test) @ ecology ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO]
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.ecology.CollegeApplicationTest
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 2.664 s -- in com.ecology.CollegeApplicationTest
[INFO] Running com.ecology.EcologyControllerTest
[INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.128 s -- in com.ecology.EcologyControllerTest
[INFO] Running com.ecology.EcologyRepositoryTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.009 s -- in com.ecology.EcologyRepositoryTest
[INFO] Running com.ecology.EcologyTest
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.025 s -- in com.ecology.EcologyTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 15, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO]
[INFO] --- jacoco:0.8.12:report (report) @ ecology ---
[INFO] Loading execution data file C:\GitHub\ecology\target\jacoco.exec
[INFO] Analyzed bundle 'ecology' with 3 classes
[INFO]
[INFO] --- jar:3.4.1:jar (default-jar) @ ecology ---
[INFO] Building jar: C:\GitHub\ecology\target\ecology-0.2.0-SNAPSHOT.jar
[INFO]
[INFO] --- jacoco:0.8.12:check (check) @ ecology ---
[INFO] Loading execution data file C:\GitHub\ecology\target\jacoco.exec
[INFO] Analyzed bundle 'ecology' with 3 classes
[INFO] All coverage checks have been met.
[INFO]
[INFO] --- jacoco:0.8.12:prepare-agent-integration (prepare-agent-integration) @ ecology ---
[INFO] integrationTestsArgLine set to -javaagent:C:\\Users\\dmitr\\.m2\\repository\\org\\jacoco\\org.jacoco.agent\\0.8.12\\org.jacoco.agent-0.8.12-runtime.jar=destfile=C:\\GitHub\\ecology\\target\\jacoco-integration-tests.exec
[INFO]
[INFO] --- failsafe:3.5.1:integration-test (default) @ ecology ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO]
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
11:02:14.719 [main] INFO org.testcontainers.DockerClientFactory - Testcontainers version: 1.20.6
11:02:14.891 [main] DEBUG org.testcontainers.utility.TestcontainersConfiguration - Testcontainers configuration overrides will be loaded from file:/C:/Users/dmitr/.testcontainers.properties
11:02:16.313 [main] INFO org.testcontainers.dockerclient.DockerClientProviderStrategy - Loaded org.testcontainers.dockerclient.NpipeSocketClientProviderStrategy from ~/.testcontainers.properties, will try it first
11:02:16.334 [main] DEBUG org.testcontainers.dockerclient.DockerClientProviderStrategy - Trying out strategy: NpipeSocketClientProviderStrategy
11:02:17.070 [main] DEBUG org.testcontainers.shaded.com.github.dockerjava.core.command.AbstrDockerCmd - Cmd:
11:02:17.237 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.InternalHttpClient - ex-00000001: preparing request execution
11:02:17.253 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAddCookies - Cookie spec selected: strict
11:02:17.289 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.protocol.RequestAuthCache - Auth cache not set in the context
11:02:17.289 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-00000001: target auth state: UNCHALLENGED
11:02:17.292 [main] DEBUG com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.ProtocolExec - ex-00000001: proxy auth state: UNCHALLENGED
...

[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 21.18 s -- in com.ecology.EcologyFlowIntegrationTests
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO]
[INFO] --- failsafe:3.5.1:verify (default) @ ecology ---
[INFO]
[INFO] --- jacoco:0.8.12:report-integration (report-integration) @ ecology ---
[INFO] Loading execution data file C:\GitHub\ecology\target\jacoco-integration-tests.exec
[INFO] Analyzed bundle 'ecology' with 3 classes
[INFO]
[INFO] --- jacoco:0.8.12:check (check-integration) @ ecology ---
[INFO] Loading execution data file C:\GitHub\ecology\target\jacoco-integration-tests.exec
[INFO] Analyzed bundle 'ecology' with 3 classes
[INFO] All coverage checks have been met.
[INFO]
[INFO] --- install:3.1.2:install (default-install) @ ecology ---
[INFO] Installing C:\GitHub\ecology\pom.xml to C:\Users\dmitr\.m2\repository\com\ecology\ecology\0.2.0-SNAPSHOT\ecology-0.2.0-SNAPSHOT.pom
[INFO] Installing C:\GitHub\ecology\target\ecology-0.2.0-SNAPSHOT.jar to C:\Users\dmitr\.m2\repository\com\ecology\ecology\0.2.0-SNAPSHOT\ecology-0.2.0-SNAPSHOT.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  59.085 s
[INFO] Finished at: 2026-04-12T11:02:45+03:00
[INFO] ------------------------------------------------------------------------
```

```
C:\GitHub\ecology>chcp 65001
```

```
mvn spring-boot:run
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------< com.ecology:ecology >-------------------
[INFO] Building ecology 0.2.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] >>> spring-boot:2.5.4:run (default-cli) > test-compile @ ecology >>>
[WARNING] 1 problem was encountered while building the effective model for org.javassist:javassist:jar:3.20.0-GA during dependency collection step for project (use -X to see details)
[INFO]
[INFO] --- checkstyle:3.3.1:check (checkstyle-validate) @ ecology ---
[INFO] Starting audit...
Audit done.
[INFO] You have 0 Checkstyle violations.
[INFO]
[INFO] --- jacoco:0.8.12:prepare-agent (prepare-agent) @ ecology ---
[INFO] argLine set to -javaagent:C:\\Users\\dmitr\\.m2\\repository\\org\\jacoco\\org.jacoco.agent\\0.8.12\\org.jacoco.agent-0.8.12-runtime.jar=destfile=C:\\GitHub\\ecology\\target\\jacoco.exec
[INFO]
[INFO] --- resources:3.3.1:resources (default-resources) @ ecology ---
[INFO] Copying 4 resources from src\main\resources to target\classes
[INFO]
[INFO] --- compiler:3.13.0:compile (default-compile) @ ecology ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] --- resources:3.3.1:testResources (default-testResources) @ ecology ---
[INFO] skip non existing resourceDirectory C:\GitHub\ecology\src\test\resources
[INFO]
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ ecology ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] <<< spring-boot:2.5.4:run (default-cli) < test-compile @ ecology <<<
[INFO]
[INFO]
[INFO] --- spring-boot:2.5.4:run (default-cli) @ ecology ---
[INFO] Attaching agents: []

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.5.4)

2026-04-12 11:23:03.718  INFO 13732 --- [           main] com.ecology.CollegeApplication           : Starting CollegeApplication using Java 23 on DESKTOP-P20K4U1 with PID 13732 (C:\GitHub\ecology\target\classes started by dmitr in C:\GitHub\ecology)
2026-04-12 11:23:03.726  INFO 13732 --- [           main] com.ecology.CollegeApplication           : No active profile set, falling back to default profiles: default
2026-04-12 11:23:04.654  INFO 13732 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data MongoDB repositories in DEFAULT mode.
2026-04-12 11:23:04.722  INFO 13732 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 59 ms. Found 1 MongoDB repository interfaces.
2026-04-12 11:23:05.436  INFO 13732 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8081 (http)
2026-04-12 11:23:05.463  INFO 13732 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2026-04-12 11:23:05.464  INFO 13732 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.52]
2026-04-12 11:23:05.613  INFO 13732 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2026-04-12 11:23:05.613  INFO 13732 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1759 ms
2026-04-12 11:23:05.837  INFO 13732 --- [           main] org.mongodb.driver.cluster               : Cluster created with settings {hosts=[localhost:27017], mode=SINGLE, requiredClusterType=UNKNOWN, serverSelectionTimeout='30000 ms'}
2026-04-12 11:23:05.950  INFO 13732 --- [localhost:27017] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:2, serverValue:7}] to localhost:27017
2026-04-12 11:23:05.950  INFO 13732 --- [localhost:27017] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:1, serverValue:8}] to localhost:27017
2026-04-12 11:23:05.956  INFO 13732 --- [localhost:27017] org.mongodb.driver.cluster               : Monitor thread successfully connected to server with description ServerDescription{address=localhost:27017, type=STANDALONE, state=CONNECTED, ok=true, minWireVersion=0, maxWireVersion=25, maxDocumentSize=16777216, logicalSessionTimeoutMinutes=30, roundTripTimeNanos=42085300}
2026-04-12 11:23:06.768  INFO 13732 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8081 (http) with context path ''
2026-04-12 11:23:06.780  INFO 13732 --- [           main] com.ecology.CollegeApplication           : Started CollegeApplication in 3.934 seconds (JVM running for 4.661)
2026-04-12 11:24:03.335  INFO 13732 --- [nio-8081-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2026-04-12 11:24:03.337  INFO 13732 --- [nio-8081-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2026-04-12 11:24:03.346  INFO 13732 --- [nio-8081-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 1 ms
2026-04-12 11:24:03.443  INFO 13732 --- [nio-8081-exec-1] org.mongodb.driver.connection            : Opened connection [connectionId{localValue:3, serverValue:9}] to localhost:27017
```

## CI (GitHub Actions)

Файл workflow: `.github/workflows/ci.yml`

CI-pipeline запускається автоматично для:
1. Pull request (`opened`, `synchronize`, `reopened`) у гілки `main` та `release/*`
2. Push у гілки `main` та `release/*`

Етапи pipeline:
1. `static-analysis`:
- Запускає Checkstyle (`mvn -B -DskipTests checkstyle:check`)
- Завершує CI з помилкою, якщо правила порушено
2. `unit-tests`:
- Запускає `mvn -B -Dcheckstyle.skip=true -DskipIntegrationTests=true test`
- Публікує `Surefire`-звіти та артефакти покриття unit-тестів
- Додає в `GITHUB_STEP_SUMMARY` підсумок line coverage для unit-тестів
3. `integration-tests`:
- Запускає `mvn -B -Dcheckstyle.skip=true -DskipUnitTests=true verify`
- Піднімає ізольований MongoDB через Testcontainers
- Публікує `Failsafe`-звіти та артефакти покриття integration-тестів
- Додає в `GITHUB_STEP_SUMMARY` окремий підсумок line coverage для `com.ecology.Ecology`
- Додає в `GITHUB_STEP_SUMMARY` окремий підсумок line coverage для `com.ecology.EcologyController`
4. `build`:
- Запускає `mvn -B -DskipUnitTests=true -DskipIntegrationTests=true package`
- Створює JAR після успішного проходження unit та integration тестів
5. `publish`:
- Використовує reusable workflow `.github/workflows/maven-publish.yml`
- Запускається лише після успішного завершення етапу `build`

Артефакти та звіти:
1. Артефакт збірки: `target/*.jar`
2. Звіти тестів: `target/surefire-reports/**`
3. Звіти integration-тестів: `target/failsafe-reports/**`
4. Звіти unit test coverage: `target/site/jacoco/**`, `target/jacoco.exec`
5. Звіти integration test coverage: `target/site/jacoco-integration-tests/**`, `target/jacoco-integration-tests.exec`
6. При падінні static analysis workflow завантажує логи: `target/ci-logs/**`
7. При падінні unit-тестів workflow завантажує логи: `target/ci-logs/**`
8. При падінні integration-тестів workflow завантажує логи `target/ci-logs/**` і додатково звіти `target/failsafe-reports/**` та `target/site/jacoco-integration-tests/**`

## Тестування та покриття

Поточний стан:
1. Unit-тести написані на **JUnit 5**.
2. Integration-тести запускаються через **Failsafe** і використовують **Testcontainers MongoDB**.
3. Для покриття використовується **JaCoCo**.
4. У `pom.xml` налаштовано мінімальне покриття **75% line coverage** для unit-тестів (перевірка на етапі `package`) і **100% line coverage** для інтеграційних тестів для класів `com.ecology.Ecology` та `com.ecology.EcologyController` (перевірка на етапі `verify`).

Локальний запуск:
1. Тільки unit-тести:
`mvn test`
2. Тільки integration-тести:
`mvn -DskipUnitTests=true verify`
3. Повна перевірка (unit + integration + JaCoCo):
`mvn verify`

Звіти:
1. JUnit/Surefire: `target/surefire-reports/`
2. JUnit/Failsafe: `target/failsafe-reports/`
3. Unit JaCoCo HTML: `target/site/jacoco/index.html`
4. Integration JaCoCo HTML: `target/site/jacoco-integration-tests/index.html`
5. Unit JaCoCo CSV: `target/site/jacoco/jacoco.csv`
