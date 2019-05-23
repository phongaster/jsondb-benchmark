Build jar

```bash
$ mvn package
```

Run benchmark
```bash
$ java -jar target/benchmarks.jar BenchmarkJsondb
```

Benchmark result
with 10000 rows Document

```
Benchmark               Mode  Cnt    Score    Error  Units
BenchmarkJsondb.find    avgt   10  592.944 ± 59.731  ms/op
BenchmarkJsondb.save    avgt   10   12.983 ±  2.912  ms/op
BenchmarkJsondb.upsert  avgt   10   11.300 ±  0.755  ms/op
```
