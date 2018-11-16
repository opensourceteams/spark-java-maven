package com.opensourceteam.bigdata.spark.local.sample;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

public class LineCountFunction {

    public static void main(String[] args) {

        JavaSparkContext sc = initSpark();
        JavaRDD<String> lines = sc.textFile("/home/data/input/lineData.txt",2);


        JavaPairRDD<String,Integer>  lineCount = lines.mapToPair(x -> new Tuple2<>(x,1)).reduceByKey((a,b) -> a + b);

        System.out.println("===========");
        System.out.println("结果 :" +lineCount.collect().toString());

    }

    public static JavaSparkContext initSpark(){
        SparkConf sparkConf = new SparkConf();
        sparkConf.setAppName("local");
        sparkConf.setMaster("local[1]");//本地模式:local     standalone:spark://master:7077
        JavaSparkContext sc = new JavaSparkContext (sparkConf);
        return sc;
    }
}
