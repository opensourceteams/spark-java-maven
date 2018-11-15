package com.opensourceteam.bigdata.spark.local.sample;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;

public class WorldLengthFunction {

    public static void main(String[] args) {

        JavaSparkContext sc = initSpark();
        JavaRDD<String> lines = sc.textFile("/home/data/input/a.txt",2);
        JavaRDD<Integer> lineLengths = lines.map(s -> s.length());


        Integer totalLength = lineLengths.reduce((a,b) -> a + b );
        System.out.println("===========");
        System.out.println("结果lines:" +lines.collect().toString());
        System.out.println("结果 lineLengths:" +lineLengths.collect().toString());
        System.out.println("结果totalLength:" +totalLength);


    }

    public static JavaSparkContext initSpark(){
        SparkConf sparkConf = new SparkConf();
        sparkConf.setAppName("local");
        sparkConf.setMaster("local[1]");//本地模式:local     standalone:spark://master:7077
        JavaSparkContext sc = new JavaSparkContext (sparkConf);
        return sc;
    }
}
