package com.fantai.dao;

import com.fantai.annotation.MyBatisRepository;
import com.fantai.entity.CarInfo;

import java.util.List;

@MyBatisRepository
public interface CarInfoMapper {

    List<CarInfo> findByUser(String uid);

    CarInfo findByCode(String code);

}
