package com.Dao.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.ui.Model;

import com.pojo.Team;

public class CricketDao {
	static SessionFactory sf;
	static {
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		sf = cfg.buildSessionFactory();
	}
	public void registerTeam(Team team) {
		System.out.println("entered into registerEmployee method from EmployeeDaoImpl");
		Session session = sf.openSession();
		session.save(team);
		session.beginTransaction().commit();
		session.close();
		System.out.println("exit from loginEmployee method from EmployeeDaoImpl");
	}
}
