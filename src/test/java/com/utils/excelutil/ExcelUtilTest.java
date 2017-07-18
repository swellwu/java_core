package com.utils.excelutil;

import com.vo.Model;
import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * <p>Description:</p>
 *
 * @author xinjian.wu
 * @date 2017-07-18
 */
public class ExcelUtilTest {

    @Test
    public void test() throws IOException {

        //导出excel
        Map<String,String> map1 = new LinkedHashMap<>();
        map1.put("a","姓名");
        map1.put("b","年龄");
        map1.put("c","性别");
        map1.put("d","出生日期");
        Collection<Object> dataset=new ArrayList<Object>();
        dataset.add(new Model("", "", "",null));
        dataset.add(new Model(null, null, null,null));
        dataset.add(new Model("王五", "34", "男",new Date()));
        File f=new File("test.xls");
        OutputStream out =new FileOutputStream(f);

        ExcelUtil.exportExcel(map1, dataset, out);
        out.close();

        //解析excel
        f=new File("test.xls");
        InputStream inputStream= new FileInputStream(f);

        ExcelLogs logs =new ExcelLogs();
        Collection<Model> importExcel = ExcelUtil.importExcel(Model.class, inputStream, "yyyy-MM-dd", logs , 0);

        for(Model m : importExcel){
            System.out.println(m);
        }
    }

    public void test2() throws Exception{
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map =new LinkedHashMap<>();
        map.put("name", "");
        map.put("age", "");
        map.put("birthday","");
        map.put("sex","");
        Map<String,Object> map2 =new LinkedHashMap<String, Object>();
        map2.put("name", null);
        map2.put("age", null);
        map2.put("sex", null);
        map.put("birthday",null);
        Map<String,Object> map3 =new LinkedHashMap<String, Object>();
        map3.put("name", "张三");
        map3.put("age", 12);
        map3.put("sex", "男");
        map3.put("birthday",new Date());
        list.add(map);
        list.add(map2);
        list.add(map3);
        Map<String,String> map1 = new LinkedHashMap<>();
        map1.put("name","姓名");
        map1.put("age","年龄");
        map1.put("birthday","出生日期");
        map1.put("sex","性别");
        File f= new File("c:/test.xls");
        OutputStream out = new FileOutputStream(f);
        ExcelUtil.exportExcel(map1,list, out );
        out.close();
    }
}
