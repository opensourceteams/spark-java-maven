package com.opensourceteam.bigdata.spark.local.rdd.operation.base;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class BaseJavaSparkContext {

    public static JavaSparkContext initSparkContext(){
        SparkConf sparkConf = new SparkConf();
        sparkConf.set("spark.eventLog.enabled","true") ;
        sparkConf.set("spark.ui.port","4040") ;
        sparkConf.set("spark.history.fs.logDirectory","/opt/module/bigdata/spark-1.6.0-cdh5.15.0/rundata/historyEventLog") ;
        sparkConf.set("spark.eventLog.dir","/opt/log/spark/log/eventLog") ;
        sparkConf.setJars(new String[]{"/opt/n_001_workspaces/bigdata/spark-java-maven/target/spark-java-maven-1.0-SNAPSHOT.jar"}) ;

        sparkConf.setAppName("local");
        sparkConf.setMaster("local[1]");//本地模式:local     standalone:spark://master:7077
        JavaSparkContext sc = new JavaSparkContext (sparkConf);
        return sc;
    }
}
