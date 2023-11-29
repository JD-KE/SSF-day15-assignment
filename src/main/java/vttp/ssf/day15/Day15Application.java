package vttp.ssf.day15;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootApplication
public class Day15Application implements CommandLineRunner {

	@Autowired @Qualifier("myredis")
	private RedisTemplate<String,String> template;
	
	public static void main(String[] args) {
		SpringApplication.run(Day15Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.printf(">>> %s\n", template);

		// ValueOperations<String,String> opsValue = template.opsForValue();
		// // opsValue.set("name", "barney");
		// opsValue.set("email", "barney@gmail.com");
		// // opsValue.increment("count", 5);
		// String count = opsValue.get("count");
		// System.out.printf("count = %s\n", count);
		// String name = opsValue.get("name");
		// System.out.printf("name = %s\n", name);
		// opsValue.set("promo_code", "scam", Duration.ofSeconds(30));

		// ListOperations<String,String> opsList = template.opsForList();
		// if (template.hasKey("fred_cart")) {
		// 	template.delete("fred_cart");
		// }
		
		// // lpush
		// opsList.leftPush("fred_cart", "apple");
		// opsList.leftPushAll("fred_cart", "orange", "pear");

		// List<String> list = new LinkedList<>();
		// list.add("durian");
		// list.add("pear");
		// opsList.rightPushAll("fred_cart", list);

		// Long size = opsList.size("fred_cart");
		// List<String> contents = opsList.range("fred_cart", 0, size);
		// if (null != contents) {
		// 	for(String c:contents) {
		// 		System.out.println(c);
		// 	}
		// }

		// opsList.leftPop("fred_cart");
		// opsList.rightPop("fred_cart");

		// String item = opsList.index("fred_cart", 1);
		// System.out.printf(">>>Index 1 %s\n", item);

		// Map
		// HashOperations<String,String,String> opsHash =  template.opsForHash();
		// opsHash.put("c001", "name", "fred");
		// opsHash.put("c001", "email", "fred@gmail.com");
		// opsHash.put("c002", "name", "barney");
		// opsHash.put("c002", "email", "barney@gmail.com");

		// String empName = opsHash.get("c001", "name");
		// System.out.println(empName);



		

	}

}
