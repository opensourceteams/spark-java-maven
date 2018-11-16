package com.opensourceteam.bigdata.spark.local.worldcount;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Arrays;

public class WorldCountSortByKeyAsc {


    public static void main(String[] args) {
        JavaSparkContext sc = initSpark();
        JavaRDD<String> lines = sc.textFile("/home/data/input/a.txt",2);
        System.out.println("结果:" + lines.collect());

        JavaRDD<String> linesFlatMap = lines.flatMap(s -> Arrays.asList(s.split(" ")));
        JavaPairRDD<String,Integer> pair =linesFlatMap.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<>(s,1);
            }
        });


        JavaPairRDD<String,Integer> result =pair.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1+v2;
            }
        });

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
