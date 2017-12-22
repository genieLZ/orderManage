package com.shop.test;
/*
* 使用spring测试模块提供的测试请求功能，测试curd准确性
* */

import com.github.pagehelper.PageInfo;
import com.shop.bean.Commodity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml","file:src/main/webapp/WEB-INF/DispatcherServlet-servlet.xml"})
public class Mvctest {
    //传入springMVC的ioc
    @Autowired
    WebApplicationContext context;
    //虚拟MVC请求，获取处理结果
    MockMvc mockMvc;

    @Before
    public void initMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    public void testPage() throws Exception {
        //模拟拿到返回值
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/cmdts").param("pn","1")).andReturn();
        //请求成功以后，请求域中会有pageInfo，我们可以取出pageInfo进行验证
        MockHttpServletRequest request = mvcResult.getRequest();
        PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
        System.out.println(mockMvc);
        System.out.println(mvcResult);
        System.out.println(request);
        System.out.println(pageInfo);
        System.out.println("当前页码"+pageInfo.getPageNum());
        System.out.println("总页码"+pageInfo.getPages());
        System.out.println("总记录数"+pageInfo.getTotal());
        System.out.println("在页面需要连续显示的页码：");
        int[] num = pageInfo.getNavigatepageNums();
        for(int i:num){
            System.out.println(""+i);
        }
        //获取员工数据
        List<Commodity> list = pageInfo.getList();
        for(Commodity commodity:list){
            System.out.println("ID:"+commodity.getCmdtId()+"==>Name" +commodity.getCmdtName());
        }

    }
}
