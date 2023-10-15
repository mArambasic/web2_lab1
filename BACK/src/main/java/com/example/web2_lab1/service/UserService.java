package com.example.web2_lab1.service;

import com.example.web2_lab1.dao.UserRepository;
import com.example.web2_lab1.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;


}
