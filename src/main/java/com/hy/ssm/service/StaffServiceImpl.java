package com.hy.ssm.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.ssm.mapper.StaffMapper;
import com.hy.ssm.pojo.StaffBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StaffServiceImpl extends ServiceImpl<StaffMapper, StaffBean> implements StaffSerice {

    @Autowired
    private StaffMapper staffMapper;

    //删除
    public int delete(String sid) {
        return staffMapper.delete(sid);
    }

    //修改
    public void update(StaffBean staffBean) {
        staffMapper.updateById(staffBean);
    }

    //添加
    public void add(StaffBean staffBean) {
        staffMapper.add(staffBean);
    }

    //根据id查询所有
    public StaffBean queryAllBySid(String sid) {
        return staffMapper.queryAllBySid(sid);
    }

    //查询所有，分页
    @Override
    public Page<StaffBean> queryAll(Integer pageNum, String search) {
        if (pageNum == null) {
            pageNum = 1;
        }
        Page<StaffBean> page = new Page<StaffBean>(pageNum, 2);
        return staffMapper.queryAll(page, search);
    }

    //查询所有，分页,layui
    @Override
    public Page<StaffBean> queryAll2(Integer pageNum,Integer limit,String search) {
        if (pageNum == null) {
            pageNum = 1;
        }
        Page<StaffBean> page = new Page<StaffBean>(pageNum, limit);
        return staffMapper.queryAll(page, search);
    }

    //批量删除
    public void delDataFilesByIds(String[] ids){
        staffMapper.delDataFilesByIds(ids);
    }

}
