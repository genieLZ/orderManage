package com.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shop.bean.Commodity;
import com.shop.bean.Msg;
import com.shop.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CommodityController {

    @Autowired
    CommodityService commodityService;

    //单一批量删除二合一
    @RequestMapping(value="/Cmdt/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteCmdt(@PathVariable("ids")String ids){
        if(ids.contains("-")){
            List<Integer> del_ids = new ArrayList<>();
            String[] str_id = ids.split("-");
            for(String string:str_id){
                del_ids.add(Integer.parseInt(string));
            }
            commodityService.deleteBatch(del_ids);
        }else{
            Integer id = Integer.parseInt(ids);
            commodityService.deleteCmdt(id);
        }
        return Msg.success();
    }

    /*
    * 如果直接发送ajax=PUT的请求
    *封装的数据为空，
    * 请求体中有数据，但是Commodity对象封装不上
    *原因：
    *Tomcat
    *   1、首先将请求体中的数据，封装成一个map。
    *   2、request.getParameter("cmdtName")从map中取值
    *   3、SpringMVC封装POJO对象的时候，把POJO中每个属性的值用request.getParameter()获取
    *因为Tomcat不封装ajax的PUT请求的数据，只有POST才封装
    *通过配置HttpPutFormContentFilter来让PUT请求能被封装
    *    通过重新包装request，重写request.getParameter，从封装的map里面取出数据
    *
    * */
    //更新商品信息
    @RequestMapping(value="/Cmdt/{cmdtId}",method = RequestMethod.PUT)
    @ResponseBody
    public Msg saveCmdt(Commodity commodity){
        commodityService.updateCmdt(commodity);
        return Msg.success();
    }


    //按照ID查找商品信息
    @RequestMapping(value="/Cmdt/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Msg getCmdt(@PathVariable("id") Integer id){
        Commodity commodity = commodityService.getCmdt(id);
        return Msg.success().add("cmdt",commodity);
    }

    //查找商品名称是否重复
    @RequestMapping(value="/checkCmdt")
    @ResponseBody
    public Msg checkCmdt(@RequestParam("cmdtName")String cmdtName){
        String regx = "[\\u4e00-\\u9fa5_a-zA-Z0-9_]{2,20}";
        if(!cmdtName.matches(regx)){
            return Msg.fail().add("vs_msg","商品名称应该是2到20位中文和英文数字！");
        }

        boolean b = commodityService.checkCmdt(cmdtName);
        if(b){
            return Msg.success();
        }else{
            return Msg.fail().add("vs_msg","已有该商品名称");
        }
    }

    //保存商品数据
    @RequestMapping(value="/cmdt",method = RequestMethod.POST)
    @ResponseBody
    public Msg saveCmdts(@Valid Commodity commodity, BindingResult result){
        if(result.hasErrors()){
            Map<String,Object> map = new HashMap<>();
            List<FieldError> error = result.getFieldErrors();
            for(FieldError fieldError:error){
                System.out.println("错误的字段名:"+fieldError.getField());
                System.out.println("错误的信息:"+fieldError.getDefaultMessage());
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields",map);
        }else{
            commodityService.saveCmdt(commodity);
            return Msg.success();
        }
    }

    //返回商品数据列表
    @RequestMapping("/cmdts")
    @ResponseBody
    public Msg getCmdtsWithJson(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model){
        //查询前调用直接插入页码以及每页的页数
        PageHelper.startPage(pn,5);
        List<Commodity> cmdts = commodityService.getAll();
        //pageInfo进行包装,封装了详细的信息，数据，还有连续的页数
        PageInfo page =new PageInfo(cmdts,5);
        return Msg.success().add("pageInfo",page);
    }

    //@RequestMapping("/cmdts")
//    public String getCmdts(@RequestParam(value = "pn",defaultValue = "1")Integer pn,Model model){
//        //查询前调用直接插入页码以及每页的页数
//        PageHelper.startPage(pn,5);
//        List<Commodity> cmdts = commodityService.getAll();
//        //pageInfo进行包装,封装了详细的信息，数据，还有连续的页数
//        PageInfo page =new PageInfo(cmdts,5);
//        model.addAttribute("pageInfo",page);
//        return "list";
//    }
}
