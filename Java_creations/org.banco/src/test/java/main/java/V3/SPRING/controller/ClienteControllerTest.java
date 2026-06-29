package main.java.V3.SPRING.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.V3.SPRING.model.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCriarClienteComSucesso() throws Exception {
        Cliente cliente = new Cliente("11122233344", "Teste Sucesso", "teste.sucesso", "sucesso@email.com", true);

        mockMvc.perform(post("/api/v3/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").value("Teste Sucesso"))
                .andExpect(jsonPath("$.cpf").value("11122233344"));
    }

    @Test
    void naoDeveCriarClienteComCpfDuplicado() throws Exception {
        // Primeiro, cria um cliente para garantir que o CPF já existe no banco
        Cliente clienteExistente = new Cliente("55566677788", "Cliente Existente", "existente.login", "existente@email.com", true);
        mockMvc.perform(post("/api/v3/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clienteExistente)));

        // Agora, tenta criar outro cliente com o mesmo CPF
        Cliente clienteDuplicado = new Cliente("55566677788", "Cliente Duplicado", "duplicado.login", "duplicado@email.com", true);

        mockMvc.perform(post("/api/v3/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteDuplicado)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").value("CPF já cadastrado"));
    }
}