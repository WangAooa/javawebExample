package com.fantai.dao;

import com.fantai.annotation.MyBatisRepository;
import com.fantai.entity.DeviceInfo;

import java.util.List;

@MyBatisRepository
public interface DeviceInfoMapper {

    List<DeviceInfo> findByThing();

    List<DeviceInfo> findByCode(String code);

}
