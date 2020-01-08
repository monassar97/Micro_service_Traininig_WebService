package com.training.repository.rds.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserJpaRepo extends JpaRepository<UserEntity,String>{
	
}
