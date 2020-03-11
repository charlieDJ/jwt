package com.example.boot.mapper;

import com.example.boot.JwtApplicationTests;
import com.example.boot.dao.mapper.CustomerMapper;
import com.example.boot.dao.model.Customer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CustomerMapperTest extends JwtApplicationTests {

    @Autowired
    private CustomerMapper customerMapper;

    @Test
    public void getTest() {
        final Optional<Customer> customerOpt = customerMapper.findById("1");
        /*customerOpt.ifPresent(e -> {
            final List<Ticket> tickets = e.getTickets();
            for (Ticket ticket : tickets) {
                System.out.println(ticket);
            }
        });*/
    }

}
