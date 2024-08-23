package com.TravessaoBackend.Formulario.models;

import com.TravessaoBackend.Formulario.enums.FormaPagamento;
import com.TravessaoBackend.Formulario.enums.TipoMatricula;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "TB_MATRICULA")
public class MatriculaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_matricula", nullable = false)
    private TipoMatricula tipoMatricula;

    @OneToOne
    @JoinColumn(name = "aluno_id", nullable = false, unique = true)
    private AlunoModel aluno;

    @ManyToOne
    @JoinColumn(name = "responsavel_id", nullable = false)
    private ResponsavelModel responsavel;

    @ManyToOne
    @JoinColumn(name = "turma_id", nullable = false)
    private TurmaModel turma;

    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pagamento", nullable = false)
    private FormaPagamento formaPagamento;

    @Column(name = "data_agendamento", nullable = false)
    private LocalDate dataAgendamento;

    @Column(name = "hora_agendamento", nullable = false)
    private LocalTime horaAgendamento;

    @Column(name = "confirmacao_termos", nullable = false)
    private Boolean confirmacaoTermos;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TipoMatricula getTipoMatricula() {
        return tipoMatricula;
    }

    public void setTipoMatricula(TipoMatricula tipoMatricula) {
        this.tipoMatricula = tipoMatricula;
    }

    public AlunoModel getAluno() {
        return aluno;
    }

    public void setAluno(AlunoModel aluno) {
        this.aluno = aluno;
    }

    public ResponsavelModel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(ResponsavelModel responsavel) {
        this.responsavel = responsavel;
    }

    public TurmaModel getTurma() {
        return turma;
    }

    public void setTurma(TurmaModel turma) {
        this.turma = turma;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public LocalDate getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDate dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public LocalTime getHoraAgendamento() {
        return horaAgendamento;
    }

    public void setHoraAgendamento(LocalTime horaAgendamento) {
        this.horaAgendamento = horaAgendamento;
    }

    public Boolean getConfirmacaoTermos() {
        return confirmacaoTermos;
    }

    public void setConfirmacaoTermos(Boolean confirmacaoTermos) {
        this.confirmacaoTermos = confirmacaoTermos;
    }


}
