package io.github.bacellar08.msclients.application;


import io.github.bacellar08.msclients.domain.Client;
import io.github.bacellar08.msclients.infra.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {


    private final ClientRepository clientRepository;

    @Transactional
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> findClientByCPF(String cpf) {
        return clientRepository.findByCpf(cpf);
    }


}
