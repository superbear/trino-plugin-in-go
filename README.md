# Trino(PrestoSQL) Plugin in Go

## Requirements
- Java 17.0.4+, 64-bit
- https://github.com/superbear/java-call-go

## How to build

### Build JAR
```bash
mvn clean package -Dcheckstyle.skipExec=true -DskipTests
```
This creates a `target/trino-plugin-in-go-402` directory.

### Test
```bash
mvn clean test -Dcheckstyle.skipExec=true
```

## Running the container
```bash
docker compose up -d

docker exec -ti trino trino

# restart container after rebuild plugin
docker restart trino
```

```sql
trino> SELECT atoi('123');
trino> SELECT toupper('hello world');
```

## References
- [Trino Plugin implementation](https://trino.io/docs/current/develop/functions.html#plugin-implementation)
- [Trino](https://github.com/trinodb/trino/blob/master/core/trino-main/src/main/java/io/trino/operator/scalar/StringFunctions.java)
