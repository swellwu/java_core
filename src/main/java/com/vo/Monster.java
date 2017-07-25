package com.vo;

import java.io.Serializable;

/**
 * 用于测试序列化的类
 *
 * @author swellwu
 * @create 2017-07-25-22:34
 */
public class Monster implements Serializable{
    Integer hp;
    Integer mp;
    String name;
    Integer level;

    public Monster(String name,Integer level){
        this.name=name;
        this.level=level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Monster monster = (Monster) o;

        if (name != null ? !name.equals(monster.name) : monster.name != null) return false;
        return level != null ? level.equals(monster.level) : monster.level == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (level != null ? level.hashCode() : 0);
        return result;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getMp() {
        return mp;
    }

    public void setMp(Integer mp) {
        this.mp = mp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
