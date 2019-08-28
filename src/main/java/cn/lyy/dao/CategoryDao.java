package cn.lyy.dao;

import cn.lyy.domain.Category;

import java.util.List;
import java.util.Locale;

public interface CategoryDao {
    List<Category> findAll();
}
