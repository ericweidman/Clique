package com.example.services;

import com.example.entities.Friend;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ericweidman on 10/31/16.
 */
public interface FriendRepository extends CrudRepository<Friend, Integer> {
}
