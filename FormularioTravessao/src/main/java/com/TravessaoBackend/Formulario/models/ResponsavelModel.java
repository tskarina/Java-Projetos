package com.TravessaoBackend.Formulario.models;

import com.TravessaoBackend.Formulario.enums.EstadoCivil;
import com.TravessaoBackend.Formulario.enums.GuardaMenor;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_RESPONSAVEL")
public class ResponsavelModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_civil", nullable = false)
    private EstadoCivil estadoCivil;

    @Enumerated(EnumType.STRING)
    @Column(name = "guarda_menor", nullable = false)
    private GuardaMenor guardaMenor;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "rg", nullable = false, unique = true)
    private String rg;

    @Column(name = "orgao_emissor", nullable = false)
    private String orgaoEmissor;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "profissao")
    private String profissao;

    @OneToMany(mappedBy = "responsavel")
    private Set<AlunoModel> alunos;

    @OneToMany(mappedBy = "responsavel")
    private Set<MatriculaModel> matriculas;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public GuardaMenor getGuardaMenor() {
        return guardaMenor;
    }

    public void setGuardaMenor(GuardaMenor guardaMenor) {
        this.guardaMenor = guardaMenor;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getOrgaoEmissor() {
        return orgaoEmissor;
    }

    public void setOrgaoEmissor(String orgaoEmissor) {
        this.orgaoEmissor = orgaoEmissor;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public Set<AlunoModel> getAlunos() {
        return alunos;
    }

    public void setAlunos(Set<AlunoModel> alunos) {
        this.alunos = alunos;
    }

    public Set<MatriculaModel> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(Set<MatriculaModel> matriculas) {
        this.matriculas = matriculas;
    }


}
