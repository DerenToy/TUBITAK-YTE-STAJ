package com.javacourse.project.hibernateAndJpa.DataAccess;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session; // dependecny ekledim Alpha'lar çalışmadı

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional; //bunu falan hep dependency olarak ekledim ben

import com.javacourse.project.hibernateAndJpa.Entities.City; 


// JPA -> veri erişim telniği ORM gibi çıkmışken,, javada standart haline geliyor.. spring bunu standart olarak implement ediyo
// ister hibernate ister başka bir şeye geçebilmeyi sağlıyor.. burda session nesnesi JPA'yı kullanarak kendi oluşur.
@Repository // yarın öbür gün başka bir şey geldiğinde bunu burdan silip ona yazıcaz hibernate'e değil de
public class HibernateCityDal implements ICityDal {
	
	private EntityManager entityManager; // bu class sessionFactory'i falan yönetmeye yarayan bir şey -> javax'tan alırız Java persistence APİ
	
	//Session Factory'i falan otomatik enjekte etmek için.. autowired ->(şuan biz hibernate kullandığımız için bize otomatik hibernate injection gerçekleştiricek)
	
	@Autowired
	public HibernateCityDal(EntityManager entityManager) {
		this.entityManager = entityManager;
		
		
	}

	
	// AOP - Aspect  Oriented Programming -> session'ı açıp kapama işini otomatik halletme
	@Override
	@Transactional //spring framework'unu kullanacağız
	public List<City> getAll() {
		//session açıp kapamaya unit of work diyoruz
		Session session = entityManager.unwrap(Session.class); //org.hibernate.Session olmalı çünkü hibernate kullnaıyoruz..
		List<City> cities = session.createQuery("from City", City.class).getResultList();
		return cities;
		
	}

	@Override
	@Transactional
	public void add(City city) {
		// var olan bir id 'yi yeniden eklememesi için add ve update'i ayrı yazıyor yoksa gerek yok
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(city);
		
	}

	@Override
	@Transactional
	public void update(City city) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(city);
		
	}

	@Override
	public void delete(City city) {
		Session session = entityManager.unwrap(Session.class);
		City cityToDelete = session.get(City.class, city.getId()); // city nesnesine city ile map et
		session.delete(cityToDelete);
		
	}
	
	public City getById(int id) {
		Session session = entityManager.unwrap(Session.class);
		City city = session.get(City.class, id); // city nesnesine id ile map et
		return city;
		
		
	}

}
