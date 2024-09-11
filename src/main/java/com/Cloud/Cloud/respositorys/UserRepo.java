package com.Cloud.Cloud.respositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Cloud.Cloud.entities.User;
import java.util.List;


@Repository
public interface UserRepo extends JpaRepository<User,String>{
//extra methods here
//custom query methods


//custom finder methods
Optional<User> findByEmail(String email);  // es ki implementation spring data apna ap kr daata

Optional<User> findByEmailAndPassword(String email,String password);

Optional<User> findByEmailToken(String id);

}
