package com.hy.ssm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.ssm.pojo.CityBean;
import com.hy.ssm.pojo.StaffBean;
import com.hy.ssm.service.CityService;
import com.hy.ssm.service.StaffServiceImpl;
import com.hy.ssm.yemian.Layui;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
public class StaffController {
    @Autowired
    private StaffServiceImpl staffService;
    @Autowired
    private CityService cityService;

    //查询所有
    @RequestMapping(value = "/modelAndView.do")
    public ModelAndView modelAndView(@RequestParam(defaultValue = "1") String page,String search,ModelAndView modelAndView) {
        if (StringUtils.isEmpty(search)) {
            search = "";
        }
        Page<StaffBean> iPage=staffService.queryAll(Integer.parseInt(page),search);
        modelAndView.addObject("page", iPage);
        modelAndView.addObject("pages", iPage.getPages());
        modelAndView.setViewName("/Staff.jsp");
        return modelAndView;
    }

    //删除
    @RequestMapping(value = "/delete.do")
    public String delete(String sid) throws ServletException, IOException {
        StaffBean staffBean = new StaffBean();
        staffBean.setSid(sid);
        staffService.delete(sid);
        return "/modelAndView.do";
    }

    //批量删除
    @RequestMapping(value = "delDataFilesByIds.do")
    public String delDataFilesByIds(String[] ids){
        staffService.delDataFilesByIds(ids);
        return "/modelAndView.do";
    }


    //查询所有城市，添加时获取用
    @RequestMapping(value = "/queryAllByCity.do")
    @ResponseBody
    public List<CityBean> queryAllByCity() throws IOException {
        List<CityBean> list = cityService.queryAll();
        return list;
    }

    //添加
    @RequestMapping(value = "/add.do")
    public String add(StaffBean staffBean, HttpServletRequest request,@RequestParam("filename") MultipartFile pictureFile) throws IOException {
        if(!pictureFile.isEmpty()){
            String picName = UUID.randomUUID().toString();
            //获取文件名
            String oriName = pictureFile.getOriginalFilename();
            //获取图片后缀
            String extName = oriName.substring(oriName.lastIndexOf("."));
            String path = request.getServletContext().getRealPath("/");
            //开始上传
            pictureFile.transferTo(new File(path + picName + extName));
            staffBean.setImg(picName + extName);
        }
        staffService.add(staffBean);
        System.out.println(request.getParameter("dates"));
        return "/modelAndView.do";
    }

    //修改用，根据id查询信息
    @RequestMapping(value = "/queryId.do")
    public ModelAndView queryId(HttpServletRequest request) {
        String sid = request.getParameter("sid");
        ModelAndView modelAndView = new ModelAndView();
        StaffBean staffBean = staffService.queryAllBySid(sid);
        System.out.println(staffBean);
        modelAndView.addObject("staffBean", staffBean);
        modelAndView.setViewName("/updatestaff.jsp");
        return modelAndView;
    }

    //修改
    @RequestMapping(value = "/update.do")
    @ResponseBody
    public void update(StaffBean staffBean, HttpServletRequest request, HttpServletResponse response,@RequestParam("filename") MultipartFile pictureFile) throws ServletException, IOException, ServletException {
        if(!pictureFile.isEmpty()){
            String picName = UUID.randomUUID().toString();
            //获取文件名
            String oriName = pictureFile.getOriginalFilename();
            //获取图片后缀
            String extName = oriName.substring(oriName.lastIndexOf("."));
            String path = request.getServletContext().getRealPath("/");
            //开始上传
            pictureFile.transferTo(new File(path + picName + extName));
            staffBean.setImg(picName + extName);
        }
        staffService.update(staffBean);
        request.getRequestDispatcher("/modelAndView.do").forward(request, response);
    }


    //查询所有，layui
    @RequestMapping(value = "/queryAll2.do")
    @ResponseBody
    public Layui queryAll2(Integer page,Integer limit) {
        Layui layui=new Layui();
            Page<StaffBean> page1=staffService.queryAll2(page,limit, "");
            layui.setCode(0);
            layui.setMsg("");
            layui.setCount(page1.getTotal());
            layui.setData(page1.getRecords());
            return layui;
    }

    //批量删除，layui
    @RequestMapping("/batchDel/{ids}.do")
    @ResponseBody
    public String batchDel(@PathVariable("ids")String ids){
        boolean flag =staffService.removeByIds(Arrays.asList(ids.split(",")));
        if(flag){
            return  "1" ;//删除成功
        }else {
            return  "0" ;//删除失败
        }
    }

    /**
     * 删除（layui发异步）
     */
    @RequestMapping("/deleteAll/{sid}")
    @ResponseBody
    public boolean deleteAll(@PathVariable("sid") String sid){
        boolean flag = staffService.removeById(sid);
        return flag;
    }
}
