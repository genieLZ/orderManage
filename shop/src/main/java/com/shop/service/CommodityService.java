package com.shop.service;

import com.shop.bean.Commodity;
import com.shop.bean.CommodityExample;
import org.springframework.beans.factory.annotation.Autowired;
import com.shop.dao.CommodityMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommodityService {

    @Autowired
    CommodityMapper commodityMapper;

    /**
     * @return
     */
    //获取商品列表
    public List<Commodity> getAll() {
        return commodityMapper.selectByExampleWithCategory(null);
    }

    //添加商品数据
    public void saveCmdt(Commodity commodity) {
        commodityMapper.insertSelective(commodity);
    }

    //检验商品名称是否重复
    public boolean checkCmdt(String cmdtName) {
        CommodityExample commodityExample = new CommodityExample();
        CommodityExample.Criteria criteria = commodityExample.createCriteria();
        criteria.andCmdtNameEqualTo(cmdtName);
        long count = commodityMapper.countByExample(commodityExample);
        return count == 0;
    }

    //获取商品信息
    public Commodity getCmdt(Integer id) {
        Commodity commodity = commodityMapper.selectByPrimaryKey(id);
        return commodity;
    }

    //商品更新
    public void updateCmdt(Commodity commodity) {
        commodityMapper.updateByPrimaryKeySelective(commodity);
    }

    public void deleteCmdt(Integer id) {
        commodityMapper.deleteByPrimaryKey(id);
    }

    public void deleteBatch(List<Integer> ids) {
        CommodityExample example = new CommodityExample();
        CommodityExample.Criteria criteria = example.createCriteria();
        //delete from commodity where cmdt_id in(1,2,3)
        criteria.andCmdtIdIn(ids);
        commodityMapper.deleteByExample(example);
    }
}
