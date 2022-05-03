package com.business.kovaibakery.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.business.kovaibakery.entity.AdminUsersEntity;

@Transactional
@Repository
public interface AdminUsersRepository extends JpaRepository<AdminUsersEntity, Long> {
	
	//@Query(value = "select * from admin-users  where userName= :userName" , nativeQuery = true)
	Optional<AdminUsersEntity> findByUserName(String userName);
	

}
