package com.fantai.dao;

import com.fantai.annotation.MyBatisRepository;
import com.fantai.entity.LockInfo;

import java.util.List;

@MyBatisRepository
public interface LockInfoMapper {

    List<LockInfo> findByUser(String uid);

    LockInfo findByCode(String code);

}
