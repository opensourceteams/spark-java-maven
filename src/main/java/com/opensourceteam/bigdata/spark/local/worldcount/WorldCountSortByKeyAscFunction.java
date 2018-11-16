package com.opensourceteam.bigdata.spark.local.worldcount;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Arrays;

public class WorldCountSortByKeyAscFunction {


    public static void main(String[] args) {
        JavaSparkContext sc = initSpark();
        JavaRDD<String> lines = sc.textFile("/home/data/input/a.txt",2);

        JavaRDD<String> linesFlatMap = lines.flatMap(s -> Arrays.asList(s.split(" ")));



        JavaPairRDD<String,Integer> result =linesFlatMap.mapToPair(s -> new Tuple2<>(s,1)).reduceByKey((v1,v2) -> v1+v2);

        System.out.println("结果result:" + result.collect());

        JavaPairRDD<String,Integer> resultSort = result.sortByKey();

        System.out.println("结果按key升序:" + resultSort.collect());
    }

    public static JavaSparkContext initSpark(){
        SparkConf sparkConf = new SparkConf();
        sparkConf.setAppName("local");
        sparkConf.setMaster("local[1]");//本地模式:local     standalone:spark://master:7077
        JavaSparkContext sc = new JavaSparkContext (sparkConf);
        return sc;
    }
}
