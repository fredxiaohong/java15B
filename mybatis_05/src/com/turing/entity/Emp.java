package com.turing.entity;

import java.io.Serializable;

public class Emp implements Serializable {
    private Integer id;

    private String name;

    private Integer deptno;
    
    private Dept dept;
    
    public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

	@Override
	public String toString() {
		return "Emp [id=" + id + ", name=" + name + ", deptno=" + deptno + "]";
	}
    
    
}