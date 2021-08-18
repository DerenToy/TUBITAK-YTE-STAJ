package com.javacourse.project.hibernateAndJpa.Business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javacourse.project.hibernateAndJpa.DataAccess.ICityDal;
import com.javacourse.project.hibernateAndJpa.Entities.City;

@Service
public class CityManager implements ICityService {
	private ICityDal cityDal;
	
	@Autowired // otomatik olarak git bak, ICityDal'a uygun bir şey varsa ver (şuan hibernate olduğu için onu veriyor olacak)
	public CityManager(ICityDal cityDal) {
		this.cityDal = cityDal;
	}

	@Override
	@Transactional
	public List<City> getAll() {

			return this.cityDal.getAll(); // çağırıyoruz db'deki operasyonu -- burdaki işi autowired gidip hibernate'i çağırıp yapacak
	}

	@Override
	@Transactional
	public void add(City city) {
		// Daha önce veri tabanına böyle bir şehir eklendi mi bunu check ediceksin burda falan
		this.cityDal.add(city);
		
	}

	@Override
	@Transactional
	public void update(City city) {
		this.cityDal.update(city);
		
	}

	@Override
	@Transactional
	public void delete(City city) {
		 this.cityDal.delete(city);
		
	}

	@Override
	@Transactional
	public City getById(int id) {
		return this.cityDal.getById(id);
	}

}
