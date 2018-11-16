package com.opensourceteam.bigdata.spark.local.sample;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

public class LineCount {

    public static void main(String[] args) {

        JavaSparkContext sc = initSpark();
        JavaRDD<String> lines = sc.textFile("/home/data/input/lineData.txt",2);
        JavaPairRDD<String, Integer> pairLine = lines.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<String, Integer>(s,1);
            }
        });

        JavaPairRDD<String,Integer>  lineCount = pairLine.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });

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
