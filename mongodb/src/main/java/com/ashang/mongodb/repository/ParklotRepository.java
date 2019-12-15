package com.ashang.mongodb.repository;

import com.ashang.mongodb.entity.Parklot;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author ashang  970090853@qq.com
 * @Date 19-12-12 下午5:38
 * <p>
 * 类说明：
 */
public interface ParklotRepository extends MongoRepository<Parklot,Integer> {
}
