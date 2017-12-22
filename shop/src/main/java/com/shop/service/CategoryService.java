package com.shop.service;

import com.shop.bean.Category;
import com.shop.dao.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * @return
     */
    public List<Category> getCategorys() {
        List<Category> list = categoryMapper.selectByExample(null);
        return list;
    }
}
