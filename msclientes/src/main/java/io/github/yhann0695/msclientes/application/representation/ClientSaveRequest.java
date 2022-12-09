package io.github.yhann0695.msclientes.application.representation;

import io.github.yhann0695.msclientes.domain.Client;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientSaveRequest {
    private String cpf;
    private String name;
    private Integer age;

    public static Client of(ClientSaveRequest request) {
        return new Client(request.getCpf(), request.getName(), request.getAge());
    }
}
