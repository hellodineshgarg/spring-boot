package com.developer.surena.repository;

import com.developer.surena.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends CrudRepository<User, Long>  {

    @Query("select u from User u where u.userId = :userId")
    User findByUser(@Param("userId") long userId);
    User findByUserId(long userId);
    List<User> findAllBy();
    User findByUsername(String username);
    User findByFirstnameAndLastname(String firstname, String lastname);
    User findByPassword(String password);
    void deleteByUserId(long userId);
    void deleteByUsername(String username);

    @Query("SELECT u FROM User u ORDER BY u.username DESC")
    List<User> getUsersLimit(Pageable pageable);

}
