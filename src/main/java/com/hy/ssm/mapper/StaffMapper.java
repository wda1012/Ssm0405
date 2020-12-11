package com.hy.ssm.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.ssm.pojo.StaffBean;
import org.apache.ibatis.annotations.*;

@Mapper
public interface StaffMapper extends BaseMapper<StaffBean> {
    //查询所有
    public Page<StaffBean> queryAll(Page<StaffBean> page,@Param("search") String search);

    //添加
    @SelectKey(statement ="select uuid()",before = true,resultType =String.class,keyProperty = "sid")
    @Insert("insert into stafftable values(#{sid},#{name},#{sex},#{age},#{cid},#{date},#{img})")
    public void add(StaffBean staffBean);

    //修改
    @Update("update stafftable set name=#{name},sex=#{sex},age=#{age},cid=#{cid},date=#{date},img=#{img} where sid=#{sid}")
    public void update(StaffBean staffBean);

    //删除
    @Delete("delete from stafftable where sid=#{value}")
    public int delete(String sid);

    //根据id查询所有
    @Select("select * from stafftable where sid=#{sid}")
    public StaffBean queryAllBySid(String sid);

    //批量删除
    public void delDataFilesByIds(@Param("ids") String[] ids);
}
