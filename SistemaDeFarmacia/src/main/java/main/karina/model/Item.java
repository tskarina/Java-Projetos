package main.karina.model;

public class Item {
    private int codigo;
    private String nome;
    private int quatidade;
    private String tipo;

    public Item() {
    }

    public Item(String nome, int quatidade, String tipo) {
        this.nome = nome;
        this.quatidade = quatidade;
        this.tipo = tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuatidade() {
        return quatidade;
    }

    public void setQuatidade(int quatidade) {
        this.quatidade = quatidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
