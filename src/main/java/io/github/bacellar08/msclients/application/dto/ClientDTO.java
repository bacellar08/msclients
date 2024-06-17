package io.github.bacellar08.msclients.application.dto;

import io.github.bacellar08.msclients.domain.Client;
import lombok.Data;

@Data
public class ClientDTO {

    private String name;
    private String cpf;
    private Integer age;

    public Client toModel() {
        return new Client(name, cpf, age);
    }
}
