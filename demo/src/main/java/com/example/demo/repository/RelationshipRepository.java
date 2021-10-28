package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.repository.CrudRepositoryExtensionsKt;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RelationshipRepository  {

    @Autowired
    private  JdbcTemplate jdbcTemplate;

    public Integer findAddressIdByEmployeeId(int id){

	    String sql_str  = "SELECT address_id from  employee_address WHERE customer_id = ?";

	    return jdbcTemplate.queryForObject(sql_str,  new Object[]{id}, Integer.class);
    }

    public Integer findAddressIdByBranchId(int id){

        String sql_str  = "SELECT address_id from  branch_address WHERE branch_id = ?";

        return jdbcTemplate.queryForObject(sql_str,  new Object[]{id}, Integer.class);
    }

}
