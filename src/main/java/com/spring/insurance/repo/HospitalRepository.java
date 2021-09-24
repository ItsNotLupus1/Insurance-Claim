package com.spring.insurance.repo;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.insurance.entity.Hospital;
import com.spring.insurance.entity.Insurance;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

	Hospital findByLatitude(Double Latitude);
	
	//Find All Method with Pagination
	Page<Hospital> findAll(Pageable pageable);
	
	List<Hospital> findByInsurance(Insurance insurance);
	
	//Filter to only search by City
	List<Hospital> findByCityOrderByRatingsDesc(String city);
	
	//Filter to only search by City
	List<Hospital> findByInsuranceAndCityOrderByRatingsDesc(Insurance insurance, String city);
	
	//Filter to only search by 5 Star Ratings
	List<Hospital> findByRatingsEquals(Integer ratings);
	
	//Filter to sort the ratings from high to low
	List<Hospital> findByOrderByRatingsDesc();
	
	//Filter to sort the ratings from low to high
	List<Hospital> findByOrderByRatingsAsc();
	
	//Filter to search by state
	List<Hospital> findByState(String state);
	
	//Filter to search by City and Ratings
	List<Hospital> findByCityAndRatings(String city, Integer ratings);
	
	//Filter tp search by City and State and ratings
	List<Hospital> findByCityAndAndStateAndRatingsOrderByRatingsDesc(String city, String State,Integer ratings);
	
	//Filter to search by State and Ratings
	List<Hospital> findByStateAndRatingsOrderByRatingsDesc(String state, Integer ratings);
	
	

	@Query(value = "SELECT h FROM Hospital h WHERE h.hname  LIKE '%' || :keyword || '%'"
	+ " OR h.state LIKE '%' || :keyword || '%'"
	+ " OR h.country LIKE '%' || :keyword || '%'"
	+ " OR h.state LIKE '%' || :keyword || '%'"
	+ " OR h.address LIKE '%' || :keyword || '%'"

	+ " OR h.pincode LIKE '%' || :keyword || '%'"

	+ " OR h.city LIKE '%' || :keyword || '%'")
	
	public List<Hospital> search(@Param("keyword") String keyword);
	
}
