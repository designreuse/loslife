package com.asgab.repository;

import com.asgab.entity.Account;
import com.asgab.repository.mybatis.MyBatisRepository;

@MyBatisRepository
public interface AccountMapper {

  Account get(String username);

}
