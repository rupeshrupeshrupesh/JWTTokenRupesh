package com.eazybyte.springsecuritybasic.Repository;

import com.eazybyte.springsecuritybasic.Model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
	
	
}
