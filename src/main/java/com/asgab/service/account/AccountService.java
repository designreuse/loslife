package com.asgab.service.account;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.asgab.entity.Account;
import com.asgab.repository.AccountMapper;
import com.asgab.util.Digests;
import com.asgab.util.Encodes;

@Component
@Transactional
public class AccountService {

  public static final String HASH_ALGORITHM = "MD5";

  @Resource
  private AccountMapper accountMapper;

  public Account get(String username) {
    return accountMapper.get(username);
  }

  public static void main(String[] args) {

    try {
      byte[] hashPassword = Digests.md5(new ByteArrayInputStream("111111".getBytes()));
      System.out.println(Encodes.encodeHex(hashPassword));
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
