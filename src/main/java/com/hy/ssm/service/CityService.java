package com.hy.ssm.service;

import com.hy.ssm.mapper.CityMapper;
import com.hy.ssm.pojo.CityBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityMapper cityMapper;

    //根据id查询
    public List<CityBean> queryByCid(String cid){
        return cityMapper.queryByCid(cid);
    }

    //查询所有
    public List<CityBean> queryAll(){
        return cityMapper.queryAll();
    }
}
