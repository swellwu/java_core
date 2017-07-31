package com.serialize;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.vo.Monster;
import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;
import org.springside.modules.utils.mapper.JsonMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>Description:</p>
 *
 * @author xinjian.wu
 * @date 2017-07-31
 */
public class JsonSerialize {

    static Map<Integer,List<Monster>> map;

    static {
        map = new HashedMap();
        List<Monster> list = Lists.newArrayList(new Monster("monster1",200),new Monster("monster2",200));
        map.put(1,list);
        map.put(2,list);
    }

    /**
     * fastjson 和 jackson 对象json序列化
     * fastjson不符合预期，jackson正常
     */
    @Test
    public void objectToJsonTest(){
        String fastJson = JSON.toJSONString(map);
        String jackson = JsonMapper.INSTANCE.toJson(map);
        System.out.println(fastJson);
        System.out.println(jackson);
    }
}
