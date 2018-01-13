package com.core.reflection;

import org.junit.*;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * Created by wuxinjian on 2017/3/6.
 */
public class ReflectionTest {

    @Rule
    public final SystemOutRule log = new SystemOutRule();

    @Test
    public void reflectionTest() {
        log.enableLog();
        Class clazz = Person.class;
        String fullClassName = getName(clazz);
        assertEquals("packageName = com.core.reflection\n" +
                        "fullClassName = com.core.reflection.Person\n" +
                        "className = Person\n",
                log.getLogWithNormalizedLineSeparator());
        log.clearLog();
        createObject(fullClassName);
        assertTrue(log.getLog().contains("person = Person"));
        log.clearLog();
        getConstructors(clazz);
        assertTrue(log.getLog().contains("Person()"));
        log.clearLog();
        getMethods(clazz);
        assertTrue(log.getLog().contains("public int getAge();"));
        log.clearLog();
        getFields(clazz);
        assertTrue(log.getLog().contains("int age;"));
        log.clearLog();
//        Person person = createPersonByProperties();
//        System.out.println(person);
//        assertEquals("Person{name='小明',age=0}\n",
//                log.getLogWithNormalizedLineSeparator().replace(" ",""));
    }

    //输出类的名称、所在包的名称
    public static String getName(Class clazz) {
        String packageName = clazz.getPackage().getName();
        String fullClassName = clazz.getName();
        String className = fullClassName;
        if (fullClassName.startsWith(packageName + ".")) {
            className = fullClassName.substring(packageName.length() + 1);
        }
        System.out.println("packageName = " + packageName);
        System.out.println("fullClassName = " + fullClassName);
        System.out.println("className = " + className);
        return fullClassName;
    }

    //全限定类名通过反射创建对象
    public static void createObject(String fullClassName) {
        try {
            Class clazz = Class.forName(fullClassName);
            Person person = (Person) clazz.newInstance();
            System.out.println("person = " + person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取构造函数
    public static void getConstructors(Class clazz) {
        Constructor constructors[] = clazz.getConstructors();
        for (int i = 0; i < constructors.length; ++i) {
            Class parameters[] = constructors[i].getParameterTypes();
            System.out.print(Modifier.toString(constructors[i].getModifiers()) + " ");
            System.out.print(constructors[i].getName());
            System.out.print("(");
            for (int j = 0; j < parameters.length; ++j) {
                System.out.print(parameters[j].getName());
                if (j < parameters.length - 1) {
                    System.out.print(" ,");
                }
            }
            System.out.println(");");
        }
    }

    //获取方法
    public static void getMethods(Class clazz) {
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.print(Modifier.toString(method.getModifiers()));
            System.out.print(" " + method.getReturnType() + " " + method.getName() + "(");
            Class[] parameters = method.getParameterTypes();
            for (int j = 0; j < parameters.length; ++j) {
                System.out.print(parameters[j].getName());
                if (j < parameters.length - 1) {
                    System.out.print(" ,");
                }
            }
            System.out.println(");");
        }
    }

    public static void getFields(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.print(Modifier.toString(field.getModifiers()) + " ");
            System.out.println(field.getType() + " " + field.getName() + ";");
        }
    }

    public static Person createPersonByProperties() {
        try {
            Properties properties = new Properties();
            InputStream file = Thread.currentThread().getContextClassLoader().getResourceAsStream("person.properties");
            properties.load(file);
            return Factory.getInstance().createPerson(properties.getProperty("person"));
        } catch (Exception e) {
            return null;
        }
    }

}