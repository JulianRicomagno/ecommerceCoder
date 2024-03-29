package com.coderhouse.ecommerce.services.implementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.coderhouse.ecommerce.entity.Client;
import com.coderhouse.ecommerce.repository.ClientRepository;
import com.coderhouse.ecommerce.services.ClientService;
import com.coderhouse.ecommerce.exception.ClientException;

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

    @Override
    public Client findByDocnumber(String docnumber) {
        return clientDao.findByDocnumber(docnumber);
    }

    public Client saveClient(Client client) {
        Client existingClient = this.findByDocnumber(client.getDocnumber());
        if (existingClient != null) {
            throw new ClientException.ClientAlreadyExistsException(client.getDocnumber());
        }
        return this.save(client);
    }

    public Client getClientById(Long id) {
        Client client = this.findById(id);
        if (client == null) {
            throw new ClientException.ClientNotFoundException(id);
        }
        return client;
    }

    public Client updateClient(Long id, Client client) {
        Client clientUpdate = this.findById(id);
        if (clientUpdate == null) {
            throw new ClientException.ClientNotFoundException(id);
        }
        clientUpdate.setName(client.getName());
        clientUpdate.setLastname(client.getLastname());
        return this.save(clientUpdate);
    }

    public Client deleteClient(Long id) {
        Client client = this.findById(id);
        if (client == null) {
            throw new ClientException.ClientNotFoundException(id);
        }
        this.delete(client);
        return client;
    }
}

