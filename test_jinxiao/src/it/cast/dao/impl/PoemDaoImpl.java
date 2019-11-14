package it.cast.dao.impl;

import it.cast.dao.PoemDao;
import it.cast.domain.Poem;
import it.cast.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class PoemDaoImpl implements PoemDao {


    @Override
    public List<Poem> findByCategory(String category) {
        List<Poem> poems = null;
        try {
            //创建sql语句
            String sql = "select * from poem where category = ?";
            //执行sql
            JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
            poems = template.query(sql, new BeanPropertyRowMapper<Poem>(Poem.class),category);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        System.out.println(poems);
        return poems;
    }

    @Override
    public Poem findById(String id) {
        Poem poem = null;
        try {
            //创建sql语句
            String sql = "select * from poem where id = ?";
            //执行sql
            JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
            poem = template.queryForObject(sql, new BeanPropertyRowMapper<Poem>(Poem.class),id);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return poem;
    }
}
