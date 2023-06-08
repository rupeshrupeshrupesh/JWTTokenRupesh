package com.eazybyte.springsecuritybasic.Repository;

import java.util.List;

import com.eazybyte.springsecuritybasic.Model.Cards;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CardsRepository extends CrudRepository<Cards, Long> {
	
	List<Cards> findByCustomerId(int customerId);

}
