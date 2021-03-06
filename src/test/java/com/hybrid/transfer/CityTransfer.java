package com.hybrid.transfer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.hybrid.mapper.CityMapper;
import com.hybrid.model.City;

public class CityTransfer {

	static Log log = LogFactory.getLog(CityTransfer.class);
	
	public static void main(String[] args) {
		
		GenericXmlApplicationContext ctx=null;
		ctx = new GenericXmlApplicationContext("spring/beans_mysql.xml", 
											   "spring/beans_oracle.xml");
		
		CityMapper mysqlCityMapper = (CityMapper) ctx.getBean("mysqlCityMapper");
		CityMapper oracleCityMapper = (CityMapper) ctx.getBean("oracleCityMapper");
		
		List<City> list = mysqlCityMapper.selectAll();
		
		log.info("city size = " + list.size());
		
		System.out.println();
		
		list.forEach(new Consumer<City>() {

			@Override
			public void accept(City t) {
				System.out.print(".");
				System.out.flush();
				int rtn = oracleCityMapper.insert(t);
				log.info("rtn = " + rtn);
			}
		});
		
		ctx.close();
		
		List<String> xx;
		

	}

}
