package com.hy.ssm.mapper;

import com.hy.ssm.pojo.CityBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CityMapper {
    //根据cid查询
    @Select("select * from citytable where cid=#{cid}")
    public List<CityBean> queryByCid(String cid);

    //查询所有
    @Select("select * from citytable ")
    public List<CityBean> queryAll();

}
