package main.karina;

import main.karina.controller.BancoDeDados;
import main.karina.model.Item;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {

        BancoDeDados banco = new BancoDeDados();
        ArrayList<Item> itens = banco.ler();

        Item item = banco.pesquisar(11, itens);

        if(item != null) {
            System.out.println(item.getNome());
        }
     else
            System.out.println("Não tem medicamento cadastrado com este codigo!");
    }
}