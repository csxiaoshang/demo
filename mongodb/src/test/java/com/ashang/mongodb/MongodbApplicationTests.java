package com.ashang.mongodb;


import com.ashang.mongodb.entity.Parklot;
import com.ashang.mongodb.repository.ParklotRepository;
import com.mongodb.BasicDBObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metric;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class MongodbApplicationTests {

    @Autowired
    private ParklotRepository parklotRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    @Transactional
    public void setGeoJson(){
         List<Parklot> parklots = parklotRepository.findAll();
         log.info("车场size {}",parklots.size());
         for (Parklot parklot:parklots){
             try {
                 parklot.setLocation(new GeoJsonPoint(parklot.getLng(),parklot.getLat()));
             }catch (Exception e){
                 log.info("异常车场信息 {}",parklot);
             }

         }
         parklotRepository.save(parklots);

    }

    @Test
    public void setSingleGeoJson(){
        Parklot parklot = parklotRepository.findOneByParklotId(3083);
        parklot.setLng(22.658566);
        parklot.setLocation(new GeoJsonPoint(parklot.getLng(),parklot.getLat()));
        parklotRepository.save(parklot);
        log.info("车场信息 {}",parklot);
    }

    @Test
    public void getNearParklot(){
        double[] coordinates = new double[2];
/*        BasicDBObject basicDBObject = new BasicDBObject("location",
                new BasicDBObject("$nearSphere"))*/
        Point point = new Point(116.3581025252,39.9616046118);
        Distance distance = new Distance(1, Metrics.KILOMETERS);
        List<Parklot> parklots = parklotRepository.findByLocationNear(point, distance);
        parklots.forEach(parklot -> log.info("parklot information : {}",parklot));
    }


}
