package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Address;

public interface AddressRepository extends CrudRepository<Address, Integer>{

}