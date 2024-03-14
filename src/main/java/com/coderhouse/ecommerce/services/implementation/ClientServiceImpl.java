package com.coderhouse.ecommerce.services.implementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.coderhouse.ecommerce.entity.Client;
import com.coderhouse.ecommerce.repository.ClientRepository;
import com.coderhouse.ecommerce.services.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientDao;

    @Override
    public List<Client> findAll() {
        return clientDao.findAll();
    }

    @Override
    public Client save(Client client) {
        return clientDao.save(client);
    }

    @Override
    public Client findById(Long id) {
        return clientDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Client client) {
        clientDao.delete(client);
    }
}

