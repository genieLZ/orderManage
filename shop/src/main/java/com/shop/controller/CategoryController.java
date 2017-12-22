package com.shop.controller;

import com.shop.bean.Category;
import com.shop.bean.Msg;
import com.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/categorys")
    @ResponseBody
    public Msg getCategorysWithJson(){
        //查询前调用直接插入页码以及每页的页数
        List<Category> category = categoryService.getCategorys();
        System.out.println(category);
        return Msg.success().add("category",category);
    }
}
