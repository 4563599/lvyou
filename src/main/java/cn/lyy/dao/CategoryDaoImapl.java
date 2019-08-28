package cn.lyy.dao;

import cn.lyy.domain.Category;
import cn.lyy.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDaoImapl implements CategoryDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Category> findAll() {
        String sql = "select * from tab_category";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
    }
}
