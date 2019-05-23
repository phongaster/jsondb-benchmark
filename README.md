# How to run
Build jar

```bash
$ mvn package
```

Run benchmark
```bash
$ java -jar target/benchmarks.jar BenchmarkJsondb
```

# Benchmark result
with 10000 rows Document

```
Benchmark               Mode  Cnt    Score    Error  Units
BenchmarkJsondb.find    avgt   10  592.944 ± 59.731  ms/op
BenchmarkJsondb.save    avgt   10   12.983 ±  2.912  ms/op
BenchmarkJsondb.upsert  avgt   10   11.300 ±  0.755  ms/op
```

with 2000 rows Document

```
Benchmark             Mode  Cnt   Score   Error  Units
BenchmarkJsondb.find  avgt    5  14.278 ± 1.866  ms/op
```

with 1000 rows Document

```
Benchmark             Mode  Cnt  Score   Error  Units
BenchmarkJsondb.find  avgt    5  3.884 ± 0.387  ms/op
```

with 500 rows Document
```
Benchmark             Mode  Cnt  Score   Error  Units
BenchmarkJsondb.find  avgt    5  1.029 ± 0.031  ms/op
```

# Other
Environment info

```
  Model Name:	MacBook Pro
  Model Identifier:	MacBookPro14,3
  Processor Name:	Intel Core i7
  Processor Speed:	2.9 GHz
  Number of Processors:	1
  Total Number of Cores:	4
  L2 Cache (per Core):	256 KB
  L3 Cache:	8 MB
  Hyper-Threading Technology:	Enabled
  Memory:	16 GB
```
