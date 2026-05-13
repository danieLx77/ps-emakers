package br.com.emakers.psemakers.client;

import br.com.emakers.psemakers.data.dto.response.EnderecoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viaCepClient", url = "https://viacep.com.br/ws")
public interface ViaCepClient {
    @GetMapping("/{cep}/json")
    EnderecoResponse buscarEnderecoPorCep(@PathVariable("cep") String cep);
}
