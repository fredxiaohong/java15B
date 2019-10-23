package com.turing.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import com.turing.entity.Emp;
import com.turing.entity.EmpExample;
import com.turing.mapper.EmpMapper;
import com.turing.util.MybatisUtil;

public class MybatisTest {
	
	@Test
	public void test1() {//Ctrl+Shift+O 全部导包
		try {
			List<String> warnings = new ArrayList<String>();
			boolean overwrite = true;
			File configFile = new File("mbg.xml");
			ConfigurationParser cp = new ConfigurationParser(warnings);
			Configuration config = cp.parseConfiguration(configFile);
			DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
			myBatisGenerator.generate(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void test4() {
		SqlSession session = MybatisUtil.openSession();
		EmpMapper mapper = session.getMapper(EmpMapper.class);
		EmpExample example = new EmpExample();
		example.createCriteria().andNameLike("%梁%").andIdBetween(1, 10);
		List<Emp> list = mapper.selectByExample(example);
		System.out.println(list);
	}
	
}
