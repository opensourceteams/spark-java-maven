package com.opensourceteam.bigdata.spark.local.rdd.operation.transformation;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class RddCollectRun {

    public static void main(String[] args) {

        SparkConf sparkConf = new SparkConf();
        sparkConf.setAppName("local");
        sparkConf.setMaster("local[1]");//本地模式:local     standalone:spark://master:7077

        JavaSparkContext sc = new JavaSparkContext (sparkConf);
        JavaRDD<String> rdd = sc.textFile("/home/data/input/a.txt",2);
        System.out.println("===========");
        System.out.println("结果:" +rdd.collect().toString());
    }
}
