package com.zj.laboratory.repository;


import com.zj.laboratory.pojo.SysLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SysLogRepository extends MongoRepository<SysLog, Long> {
}