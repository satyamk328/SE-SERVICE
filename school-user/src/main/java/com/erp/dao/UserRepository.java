package com.erp.dao;

import org.springframework.data.repository.CrudRepository;


import com.erp.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
