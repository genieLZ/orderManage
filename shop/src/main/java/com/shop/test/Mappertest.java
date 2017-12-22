package com.shop.test;

import com.shop.bean.Commodity;
import com.shop.dao.CategoryMapper;
import com.shop.dao.CommodityMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.runner.RunWith;

import java.util.UUID;

/*
* 测试dao层的工作
* @linzhe
* 推荐spring的项目都可以使用spring的单元测试，可以自动注入需要的组件
* 1.导入springtest模块
* 2.使用@ContextConfiguration指定spring配置文件的位置
* */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class Mappertest {
/*
*  测试CommodityMapper
//* */
//    @Autowired
//    CategoryMapper categoryMapper;
    @Autowired
    CommodityMapper commodityMapper;
    @Autowired
    SqlSession sqlSession;
    @Test
    public void test(){
//        System.out.println(categoryMapper);
//        System.out.println(commodityMapper);

        //插入商品种类
//        categoryMapper.insertSelective(new Category(null,"进口商品"));
//        categoryMapper.insertSelective(new Category(null,"IT产品"));

        //插入商品信息
        //commodityMapper.insertSelective(new Commodity(null,"神舟K610D",3499l,4));
        //批量插入使用sqlSession
        CommodityMapper mapper = sqlSession.getMapper(CommodityMapper.class);
        for(int i =0;i<100;i++){
            String UID = UUID.randomUUID().toString().substring(0,5)+i;
            mapper.insertSelective(new Commodity(null,UID,999l,4));
        }
        System.out.println("完成");


    }
//    public  static void main(String[] args) throws Exception{
//                /*取得IOC容器*/
//        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
//        //从容器中获取mapper
//        CommodityMapper bean = ioc.getBean(CommodityMapper.class);
//
//        System.out.println(bean);
//    }
}
