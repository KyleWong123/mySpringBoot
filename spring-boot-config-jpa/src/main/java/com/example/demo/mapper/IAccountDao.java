package com.example.demo.dao;

import com.example.demo.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAccountDao extends JpaRepository<AccountEntity, Integer> {
}
