package com.ashang.mongodb.repository;

import com.ashang.mongodb.entity.Parklot;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author ashang  970090853@qq.com
 * @Date 19-12-12 下午5:38
 * <p>
 * 类说明：
 */
public interface ParklotRepository extends MongoRepository<Parklot,String> {

    Parklot findOneByParklotId(Integer id);

    List<Parklot> findByLocationNear(Point point, Distance distance);
}
