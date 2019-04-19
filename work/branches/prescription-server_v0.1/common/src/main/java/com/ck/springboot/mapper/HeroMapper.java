package com.ck.springboot.mapper;

import com.ck.springboot.pojo.Hero;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HeroMapper {
    public int add(Hero hero);
    
    public void delete(int id); 
        
    public Hero get(int id);
      
    public int update(Hero hero);
        
    public List<Hero> list();
}