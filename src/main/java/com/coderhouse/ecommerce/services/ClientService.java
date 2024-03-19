package com.coderhouse.ecommerce.services;

import java.util.List;
import com.coderhouse.ecommerce.entity.Client;

public interface ClientService {
    List<Client> findAll();
    Client save(Client client);
    Client findById(Long id);
    void delete(Client client);
    Client findByDocnumber(String docnumber);
}
