package com.example.services;

import com.example.entities.Message;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Entity;

/**
 * Created by ericweidman on 10/31/16.
 */
public interface MessageRepository extends CrudRepository<Message, Integer> {

}
