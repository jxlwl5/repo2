package it.cast.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.cast.dao.PoemDao;
import it.cast.dao.impl.PoemDaoImpl;
import it.cast.domain.Poem;
import it.cast.service.PoemService;
import redis.clients.jedis.Jedis;

import java.util.List;

public class PoemServiceImpl implements PoemService {

    private PoemDao dao = new PoemDaoImpl();

    @Override
    public String findByCategory(String category) {
        Jedis jedis = new Jedis();
        String poem_category = jedis.get("poem_category");
        if(poem_category == null){
            System.out.println(category);
            List<Poem> poems = dao.findByCategory(category);
            try {
                ObjectMapper mapper = new ObjectMapper();
                poem_category = mapper.writeValueAsString(poems);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            jedis.set("poem_category",poem_category);
        }
        return poem_category;
    }

    @Override
    public Poem findById(String id) {
        return dao.findById(id);
    }
}
