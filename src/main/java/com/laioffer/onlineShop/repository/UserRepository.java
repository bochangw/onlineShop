package com.laioffer.onlineShop.repository;

import com.laioffer.onlineShop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
