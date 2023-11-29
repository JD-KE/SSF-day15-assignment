package vttp.ssf.day15.repository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import vttp.ssf.day15.model.Item;

@Repository
public class CartRepository {

    @Autowired @Qualifier("myredis")
	private RedisTemplate<String,String> template;

    // private HashOperations<String,String,Integer> opsHash = template.opsForHash();

    public boolean hasCart(String name) {
        return template.hasKey(name);
    }

    public void setCart(List<Item> cart, String name) {  
        HashOperations<String,String,String> opsHash = template.opsForHash();
        Map<String,String> items = new HashMap<>();
        for (Item item : cart) {
            items.put(item.getName(), item.getQuantity().toString());
        }
        opsHash.putAll(name, items);
        
    }

    public List<Item> getCart(String name) {
        HashOperations<String,String,String> opsHash = template.opsForHash();
        Map<String,String> itemsMap = opsHash.entries(name);
        List<Item> items = new LinkedList<>();

        for (Map.Entry<String,String> item: itemsMap.entrySet()) {
            items.add(new Item(item.getKey(), Integer.parseInt(item.getValue())));
        }

        return items;

    }
    
}
