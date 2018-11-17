package com.opensourceteam.bigdata.spark.local.rdd.operation.action.sortby.n_01_升序;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.ArrayList;
import java.util.List;

public class Run {

    public static void main(String[] args) {

        SparkConf sparkConf = new SparkConf();

        sparkConf.setAppName("local");
        sparkConf.setMaster("local[1]");//本地模式:local     standalone:spark://master:7077

        JavaSparkContext sc = new JavaSparkContext (sparkConf);
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("d");
        list.add("a");
        list.add("g");
        list.add("f");

        JavaRDD<String> rdd = sc.parallelize(list,2) ;
        JavaRDD<String> rddSort = rdd.sortBy(x -> x,true,2);

        System.out.println("===========");
        System.out.println("结果:" +rdd.collect().toString());
        System.out.println("排序后结果:" +rddSort.collect().toString());
    }
}
