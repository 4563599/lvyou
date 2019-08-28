package cn.lyy.service;

import cn.lyy.dao.CategoryDao;
import cn.lyy.domain.Category;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao;

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}
