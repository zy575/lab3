package cn.edu.ujn.lab3.dao;

import java.util.List;

import cn.edu.ujn.lab3.dao.Customer;

public interface CustomerMapper {
    int deleteByPrimaryKey(Integer custId);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer custId);
    
    List<Customer> findAllCustomer();
    List<Customer> findCustomerByCondition(Customer con);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
}