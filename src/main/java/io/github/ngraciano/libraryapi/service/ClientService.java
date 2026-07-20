package io.github.ngraciano.libraryapi.service;


import io.github.ngraciano.libraryapi.exceptions.DuplicateEntryException;
import io.github.ngraciano.libraryapi.model.Book;
import io.github.ngraciano.libraryapi.model.Client;
import io.github.ngraciano.libraryapi.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final PasswordEncoder encoder;

    public Client save(Client client){
        if (repository.findByClientId(client.getClientId())!=null){
            throw new DuplicateEntryException("ClientID can't duplicate");
        }
        var passwordEncoded=encoder.encode(client.getClientSecret());
        client.setClientSecret(passwordEncoded);
        return repository.save(client);
    }

    public Client getById(String clientId){
        return repository.findByClientId(clientId);
    }
}
