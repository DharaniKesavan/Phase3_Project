package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.bean.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer> {

}
