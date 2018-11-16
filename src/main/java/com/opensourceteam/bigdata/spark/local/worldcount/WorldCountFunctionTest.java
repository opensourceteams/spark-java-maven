package com.opensourceteam.bigdata.spark.local.worldcount;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;


public class WorldCountFunctionTest {


    public static void main(String[] args) {
        JavaSparkContext sc = initSpark();
        JavaRDD<String> lines = sc.textFile("/home/data/input/a.txt",2);

        JavaPairRDD<String,Integer> result = lines.flatMap(s -> Arrays.asList(s.split(" "))).mapToPair(x -> new Tuple2<>(x,1) ).reduceByKey((a,b) -> a + b );
        System.out.println("============");
        result.foreach(x -> System.out.println("foreach "+ x._1+""));
        System.out.println("结果result:" + result.collect());
    }

    public static JavaSparkContext initSpark(){
        SparkConf sparkConf = new SparkConf();
        sparkConf.setAppName("local");
        sparkConf.setMaster("local[1]");//本地模式:local     standalone:spark://master:7077
        JavaSparkContext sc = new JavaSparkContext (sparkConf);
        return sc;
    }
}
