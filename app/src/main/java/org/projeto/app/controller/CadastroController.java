package org.projeto.app.controller;

import jakarta.validation.Valid;
import org.projeto.app.model.Especialidade;
import org.projeto.app.model.Medico;
import org.projeto.app.service.CadastroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/medicos")
public class CadastroController {
    private final CadastroService service;

    public CadastroController(CadastroService service){
        this.service = service;
    }

    @GetMapping("/listarMedicos")
    public ResponseEntity<Map<Long, Medico>> listar(){
        Map<Long, Medico> response = service.listarMedicos();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/buscarEspecialidade")
    public ResponseEntity<List<String>> especialidade(@Valid @RequestParam Especialidade especialidade){
        List<String> respsonse = service.buscarMedicoEspecialidade(especialidade);
        return ResponseEntity.ok().body(respsonse);
    }

    @PostMapping("/cadastrarMedico")
    public ResponseEntity<Map<String, String>> cadastro(@Valid @RequestBody Medico medico){
        service.cadastrarMedico(medico);
        Map<String, String> response = new HashMap<>();
        response.put("mensagem", "médico cadastrado com sucesso!");
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/alterarValorConsulta")
    public ResponseEntity<Map<String, String>> alterar(@RequestParam long ID, @RequestParam BigDecimal valorConsulta){
        service.alterarValorConsulta(ID, valorConsulta);
        Map<String, String> response = new HashMap<>();
        response.put("mensagem", "valor da consulta alterado com sucesso!");
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/removerMedico/{ID}")
    public ResponseEntity<Map<String, String>> remover(@PathVariable long ID){
        service.deletarMedico(ID);
        Map<String, String> response = new HashMap<>();
        response.put("mensagem", "médico retirado com sucesso!");
        return ResponseEntity.ok().body(response);
    }


}
