package io.github.bacellar08.msclients.application;

import io.github.bacellar08.msclients.application.dto.ClientDTO;
import io.github.bacellar08.msclients.domain.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.logging.Logger;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    Logger logger = Logger.getLogger(ClientController.class.getName());

    private final ClientService service;

    @GetMapping
    public String status() {

        logger.info("Get Status");

        return "ok";
    }


    @PostMapping
    public ResponseEntity saveClient(@RequestBody ClientDTO request) {
        Client client = request.toModel();
        service.saveClient(client);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(client.getCpf())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity getClientByCPF(@RequestParam("cpf") String cpf) {
        var client = service.findClientByCPF(cpf);

        if (client.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client);
    }
}
