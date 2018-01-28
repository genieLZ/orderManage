package com.orderManage.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mmall.common.ServiceResponse;
import com.mmall.dao.CategoryMapper;
import com.mmall.pojo.Category;
import com.mmall.service.ICategoryService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

/**
 * Create by LZ
 */
@Service("iCategoryService")
public class CategoryServiceImpl implements ICategoryService{

    private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryMapper categoryMapper;

    public ServiceResponse addCategory(String categoryName,Integer parentId){
        if(parentId == null || StringUtils.isBlank(categoryName)){
            return ServiceResponse.createByError("添加品类错误！");
        }

        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(true);//此分类可用

        int rowCount = categoryMapper.insert(category);
        if(rowCount > 0){
            return ServiceResponse.createBySuccess("添加品类成功！");
        }
        return ServiceResponse.createByError("添加品类失败！");
    }

    public ServiceResponse updateCategoryName(Integer categoryId, String categoryName){
        if(categoryId == null || StringUtils.isBlank(categoryName)){
            return ServiceResponse.createByError("更新品类参数错误！");
        }
        Category category = new Category();
        category.setParentId(categoryId);
        category.setName(categoryName);
        int rowCount = categoryMapper.insertSelective(category);
        if(rowCount > 0){
            return ServiceResponse.createBySuccess("更新品类名称成功！");
        }
        return ServiceResponse.createByError("更新品类名称失败！");
    }

    public ServiceResponse<List<Category>> getChildrenParallelCategory(Integer categoryId){
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        if(CollectionUtils.isEmpty(categoryList)){
            logger.info("未找到当前分类的子分类");
        }
        return ServiceResponse.createBySuccess(categoryList);
    }

    /**
     * 递归查询本节点的id及其孩子结点id
     * @param categoryId
     * @return
     */
    public ServiceResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId){
        Set<Category> categorySet = Sets.newHashSet();
        findChildrenCategory(categorySet,categoryId);

        List<Integer> categoryIdList = Lists.newArrayList();
        if(categoryId != null){
            for(Category categoryItem: categorySet){
                categoryIdList.add(categoryItem.getId());
            }
        }
        return ServiceResponse.createBySuccess(categoryIdList);
    }

    //递归算法，算出子节点
    private Set<Category> findChildrenCategory(Set<Category> categorySet, Integer categoryId){
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if(category != null){
            categorySet.add(category);
        }
        //查找子节点，递归算法一定要有一个退出条件
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        for(Category categoryItem: categoryList){
            findChildrenCategory(categorySet,categoryItem.getId());
        }
        return categorySet;
    }
}
