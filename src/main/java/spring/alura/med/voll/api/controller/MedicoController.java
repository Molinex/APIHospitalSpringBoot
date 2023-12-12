package spring.alura.med.voll.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import spring.alura.med.voll.api.domain.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public MedicoCadastroRequest cadastraMedicos(@RequestBody @Valid MedicoCadastroRequest medicoCadastroRequest){
        medicoRepository.save(new Medico(medicoCadastroRequest));
        return medicoCadastroRequest;
    }

    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(sort = "nome", size = 10) Pageable paginacao){
        return medicoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

    @PutMapping
    @Transactional
    public MedicoAlteracaoRequest alterarMedicos(@RequestBody @Valid MedicoAlteracaoRequest medicoAlteracaoRequest){
        Medico medico = medicoRepository.getReferenceById(medicoAlteracaoRequest.id());
        medico.atualizaDadosMedico(medicoAlteracaoRequest);
        return medicoAlteracaoRequest;
    }

    @DeleteMapping("/{id}")
    @Transactional
    public String exluirMedico(@PathVariable Long id){
        //Excluir definitivo de médico
//        Medico medico = medicoRepository.getById(id);
//        medicoRepository.delete(medico);
//        return "Médico de id: " + id +" foi exluído!!!";

        //Desabilita o médico
        Medico medico = medicoRepository.getReferenceById(id);
        medico.excluir();

        return "Médico: " + medico.getNome() + " de id: " + id +" foi desabilitado!!!";
    }

}
