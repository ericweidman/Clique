package com.example.services;

import com.example.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ericweidman on 10/31/16.
 */
public interface UserRepository extends CrudRepository<User, Integer>{

    User findByUserName(String userName);


}
