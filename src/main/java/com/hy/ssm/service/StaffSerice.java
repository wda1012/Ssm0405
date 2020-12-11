package com.hy.ssm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hy.ssm.pojo.StaffBean;

public interface StaffSerice extends IService<StaffBean> {
    public Page<StaffBean> queryAll(Integer pageNum,String search);

    //查询所有，layui
    public Page<StaffBean> queryAll2(Integer pageNum,Integer limit,String search);
}
