package org.projeto.app.service;

import org.projeto.app.exception.CRMExistenteException;
import org.projeto.app.model.Especialidade;
import org.projeto.app.model.Medico;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class CadastroService {
    private Map<Long, Medico> medicos = new HashMap<>();
    private Long ID = 0L;

    public void cadastrarMedico(Medico medico){
        String CRM = medico.CRM();
        if (verificarCRM(CRM)){
            throw new CRMExistenteException("CRM já cadastrado!");
        }
        incrementarID();
        medicos.put(ID, medico);
    }

    public List<String> buscarMedicoEspecialidade(Especialidade especialidade){
        return medicos.values()
                .stream()
                .filter(m -> Objects.equals(m.especialidade(), especialidade))
                .map(Medico::nome)
                .toList();
    }

    public Map<Long, Medico> listarMedicos(){
        return medicos;
    }

    private boolean verificarCRM(String CRM){
        return medicos.values()
                .stream()
                .anyMatch(m -> Objects.equals(m.CRM(), CRM));
    }

    private void incrementarID(){ID += 1;}
}
