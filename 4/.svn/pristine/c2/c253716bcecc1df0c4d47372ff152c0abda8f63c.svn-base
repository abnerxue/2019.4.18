package com.ck.springboot.service.impl;

import com.ck.springboot.mapper.HeroMapper;
import com.ck.springboot.pojo.Hero;
import com.ck.springboot.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroServiceImpl implements HeroService{
	
	@Autowired HeroMapper heroMapper;

	@Override
	public int add(Hero hero) {
		return heroMapper.add(hero);
	}

	@Override
	public void delete(int id) {
		heroMapper.delete(id);
	}

	@Override
	public Hero get(int id) {
		return heroMapper.get(id);
	}

	@Override
	public int update(Hero hero) {
		return heroMapper.update(hero);
	}

	@Override
	public List<Hero> list() {
		return heroMapper.list();
	}

}
