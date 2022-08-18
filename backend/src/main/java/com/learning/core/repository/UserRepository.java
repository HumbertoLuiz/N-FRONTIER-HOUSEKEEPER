package com.learning.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.core.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
