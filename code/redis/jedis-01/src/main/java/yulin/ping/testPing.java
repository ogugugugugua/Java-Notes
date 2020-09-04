package yulin.ping;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class testPing {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.flushAll();

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("name","yulin");
        jsonObject1.put("age",18);
        String jsonString1 = jsonObject1.toJSONString();

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("name","fufu");
        jsonObject2.put("age",10);
        String jsonString2 = jsonObject2.toJSONString();

        Transaction transaction = jedis.multi();

        try {
            transaction.set("user1",jsonString1);
            transaction.set("user2",jsonString2);
            int i= 1/0  ;
            transaction.exec();
            System.out.println("success");
        } catch (Exception e) {
            transaction.discard();
            System.out.println("discard");
            e.printStackTrace();
        } finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            System.out.println(jedis.get("temp"));
            jedis.close();
        }
    }
}
