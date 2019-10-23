package com.turing.test;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.turing.entity.Dept;
import com.turing.entity.Emp;
import com.turing.entity.EmpExample;
import com.turing.entity.EmpExample.Criteria;
import com.turing.mapper.DeptMapper;
import com.turing.mapper.EmpMapper;
import com.turing.util.MybatisUtil;

public class MybatisTestGenerator {

	SqlSession session = null;
	EmpMapper mapper = null;
	
	@Before
	public void start() {
		session = MybatisUtil.openSession();
		 mapper = session.getMapper(EmpMapper.class);
	}
	@After
	public void end() {
		session.close();
	}
	//1、根据id查询员工。
	@Test
	public void test1() {
		Emp emp = mapper.selectByPrimaryKey(2);
		System.out.println(emp);
	}
	
	//2、根据名称查询部门。
	@Test
	public void test2() {
		EmpExample example = new EmpExample();
		example.createCriteria().andNameEqualTo("小何");
		List<Emp> list = mapper.selectByExample(example);
		for (Emp emp : list) {
			System.out.println(emp.getDeptno());
		}
	}
	
	//3、查询id为1，并且姓名为“孙悟空”的员工。
	@Test
	public void test3() {
		EmpExample example  = new EmpExample();
		example.createCriteria().andNameEqualTo("小何").andIdEqualTo(4);
		List<Emp> list = mapper.selectByExample(example);
		System.out.println(list);
	}
	
	//4、查询名称中包含某个关键字，且id在10号内的员工。
	@Test
	public void test4() {
		EmpExample example = new EmpExample();
		//关键方法，所有条件的方法都封装在该Criteria对象身上
		//方法都是以andXXX开头
		Criteria criteria = example.createCriteria();
		//设置条件
		criteria.andNameLike("%梁%");
		criteria.andIdBetween(1, 10);
		//执行查询
		List<Emp> list = mapper.selectByExample(example);
		System.out.println(list);     
		
	}
	
	//5、查询所有员工，按员工编号降序排列。
	@Test
	public void test5() {
		EmpExample example = new EmpExample();
		example.setOrderByClause("id desc");
		List<Emp> list = mapper.selectByExample(example);
		for (Emp emp : list) {
			System.out.println(emp);
		}
	}
	
	//6、分页查询，每页显示5条，查询第二页员工信息。
	@Test
	public void test6() {
		int pagerNow = 2;
		int pagerSize = 5;
		pagerNow = (pagerNow-1)*pagerSize;
		EmpExample example = new EmpExample();
		RowBounds rowBounds = new RowBounds(pagerNow,pagerSize);
		List<Emp> list = mapper.selectByExampleWithRowbounds(example, rowBounds);
		for (Emp emp : list) {
			System.out.println(emp);
		}
	}
	
	//7、查询(姓名包含"王"，并且id在3~7之间)，或者 (部门不在2号部门)。
	@Test
	public void test7() {
		EmpExample example = new EmpExample();
		Criteria criteria1 = example.createCriteria();
		criteria1.andNameLike("%梁%").andIdBetween(3, 7);
		Criteria criteria2 = example.createCriteria();
		criteria2.andDeptnoNotEqualTo(2);
		 //融合两个example
		example.or(criteria2);
		List<Emp> list = mapper.selectByExample(example);
		for (Emp emp : list) {
			System.out.println(emp);
		}
	}
	
	//8、根据id删除员工。
	@Test
	public void test8() {
		int key = mapper.deleteByPrimaryKey(10);
		System.out.println(key);
	}
	
	//9、把2号员工姓名改为“万人迷”,未修改的字段不变。
	@Test
	public void test9() {
		Emp emp = new Emp();
		emp.setId(2);
		emp.setName("万人迷");
		int keySelective = mapper.updateByPrimaryKeySelective(emp);
		System.out.println(keySelective);
	}
	//10、添加“开发部”部门。
	@Test
	public void test10() {
		SqlSession session2 = MybatisUtil.openSession();
		DeptMapper mapper2 = session2.getMapper(DeptMapper.class);
		Dept dept = new Dept(null, "开发部");
		int insert = mapper2.insert(dept);
		System.out.println(insert);
	}
}
