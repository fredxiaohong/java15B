package com.turing.entity;

import java.io.Serializable;

public class Dept implements Serializable {
    private Integer id;

    private String name;

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

	public Dept(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Dept() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Dept [id=" + id + ", name=" + name + "]";
	}
    
    
}