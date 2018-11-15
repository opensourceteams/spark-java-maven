package com.opensourceteam.bigdata.spark.local.worldcount;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.storage.StorageLevel;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

public class WorldCount {


    public static void main(String[] args) {
        JavaSparkContext sc = initSpark();
        JavaRDD<String> lines = sc.textFile("/home/data/input/a.txt",2);
        System.out.println("结果:" + lines.collect());

        JavaRDD<String> linesFlatMap = lines.flatMap(s -> Arrays.asList(s.split(" ")));
        System.out.println("结果:" + linesFlatMap.collect());
        JavaPairRDD<String,Integer> pair =linesFlatMap.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<>(s,1);
            }
        });
        System.out.println("结果:" + pair.collect());

        JavaPairRDD<String,Integer> pair2 =linesFlatMap.mapToPair(x -> new Tuple2<>(x,1)  );
        System.out.println("结果pair2:" + pair2.collect());

        JavaPairRDD<String,Integer> result =pair2.reduceByKey((a,b) -> a + b );
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
