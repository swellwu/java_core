package com.framework.redis;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/3/15.
 */
@Ignore
public class RedisTest {

    private Jedis jedis;

    @Before
    public void setup() {
        //连接redis服务器，192.168.0.100:6379
        jedis = new Jedis("127.0.0.1", 6379);
        //权限认证
 //       jedis.auth("admin");
    }

    @After
    public void clear(){
        jedis.flushDB();
    }

    @Test
    public void initTest(){
        assertNotNull(jedis);
    }

    /**
     * redis存储字符串
     */
    @Test
    public void testString() {
        //-----添加数据----------
        jedis.set("name","xinxin");//向key-->name中放入了value-->xinxin
        assertEquals("xinxin",jedis.get("name"));//执行结果：xinxin
        jedis.append("name", " is my lover"); //拼接
        assertEquals("xinxin is my lover",jedis.get("name"));
        jedis.del("name");  //删除某个键
        assertNull(jedis.get("name"));
        //设置多个键值对
        jedis.mset("name","liuling","age","23","qq","476777XXX");
        assertEquals("23",jedis.get("age"));
    }

    /**
     * redis操作Map
     */
    @Test
    public void testMap() {
        //-----添加数据----------
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "xinxin");
        map.put("age", "22");
        map.put("qq", "123456");
        jedis.hmset("user",map);
        //取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List
        //第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数
        List<String> rsmap = jedis.hmget("user", "name", "age", "qq");
        assertArrayEquals(new String[]{"xinxin","22","123456"},rsmap.toArray());
        assertNull(jedis.hget("user","phone"));
        jedis.hset("user","phone","110");
        assertEquals("110",jedis.hget("user","phone"));
        //遍历
        Iterator<String> iter=jedis.hkeys("user").iterator();
        while (iter.hasNext()){
            String key = iter.next();
            System.out.println(key+":"+jedis.hmget("user",key));
        }
        jedis.del("user");
        assertTrue(!jedis.exists("user"));
    }

    /**
     * jedis操作List
     */
    @Test
    public void testList(){
        String key="key";
        jedis.del(key);
        assertEquals(0,jedis.lrange("java framework",0,-1).size());
        //从左边插入
        jedis.lpush(key,"1");
        jedis.lpush(key,"2");
        jedis.lpush(key,"3");
        // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
        assertEquals(Arrays.asList("3","2","1"),jedis.lrange(key,0,-1));
    }

}
