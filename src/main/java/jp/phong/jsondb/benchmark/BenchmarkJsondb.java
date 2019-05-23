package jp.phong.jsondb.benchmark;

import io.jsondb.JsonDBTemplate;
import io.jsondb.crypto.DefaultAESCBCCipher;
import io.jsondb.crypto.ICipher;
import jp.phong.jsondb.benchmark.model.Instance;

import java.security.GeneralSecurityException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/*
http://hg.openjdk.java.net/code-tools/jmh/file/tip/jmh-samples/src/main/java/org/openjdk/jmh/samples/
*/
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
//@Warmup(iterations = 3)
//@Measurement(iterations = 8)
public class BenchmarkJsondb {
  private JsonDBTemplate jsonDBTemplate;
  public static void main(String[] args) throws RunnerException {

    Options opt = new OptionsBuilder()
            .include(BenchmarkJsondb.class.getSimpleName())
            .forks(1)
            .build();

    new Runner(opt).run();
  }

  public void insert(String id) {
    Instance instance = new Instance();
    instance.setId(id);
    instance.setHostname("ec2-54-191-11"+id);
    instance.setPrivateKey("b87eb02f5dd7e5232d7b0fc30a5015e4"+id);
    instance.setPublicKey("asfsafsfsaf5dd7fe5d7b0fasfsa15as234"+id);
    this.jsonDBTemplate.insert(instance);
  }

  @Benchmark
  public void find() {
    String jxQuery = String.format("/.[hostname='%s']", "ec2-54-191-111000");
    List<Instance> instances = this.jsonDBTemplate.find(jxQuery, Instance.class);
    instances.forEach(instance -> {
      instance.getId();
    });
  }
  // save
  @Benchmark
  public void save() {
    Instance instance = new Instance();
    instance.setId("15");
    this.jsonDBTemplate.save(instance, Instance.class);
  }

  // update
  @Benchmark
  public void upsert() {
    Instance instance = new Instance();
    instance.setId("7");
    instance.setHostname("ec2-54-191-07");
    instance.setPrivateKey("PrivateRyanSaved");
    instance.setPublicKey("TomHanks");
    this.jsonDBTemplate.upsert(instance);
  }

  @Setup
  public void setup() {
    //Actual location on disk for database files, process should have read-write permissions to this folder
    String dbFilesLocation = "db_files";

    //Java package name where POJO's are present
    String baseScanPackage = "jp.phong.jsondb.benchmark.model";

    //Optionally a Cipher object if you need Encryption
    ICipher cipher = null;
    try {
      cipher = new DefaultAESCBCCipher("1r8+24pibarAWgS85/Heeg==");
    } catch (GeneralSecurityException e) {
      e.printStackTrace();
    }

    this.jsonDBTemplate = new JsonDBTemplate(dbFilesLocation, baseScanPackage, cipher);
    Boolean isInstanceExisted = this.jsonDBTemplate.collectionExists(Instance.class);
    if (isInstanceExisted) {
      // Remove this to create new document
      //this.jsonDBTemplate.dropCollection(Instance.class);
    } else {
      this.jsonDBTemplate.createCollection(Instance.class);
      for(int i = 1; i < 10000; i++) {
        insert(Integer.toString(i));
      }
    }
  }

}
