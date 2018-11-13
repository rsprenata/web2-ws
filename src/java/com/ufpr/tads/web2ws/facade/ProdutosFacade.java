/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2ws.facade;
import com.ufpr.tads.web2ws.beans.Produto;
import com.ufpr.tads.web2ws.dao.ProdutoCliente;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author renata.pereira
 */
public class ProdutosFacade {
    public static List<Produto> buscarTodos() {
        return ProdutoCliente.buscarProdutos();
    }
    public static Produto buscar(int id) {
        return ProdutoCliente.buscarUmProduto(id);
    }
    public static void remover(int id) {
        ProdutoCliente.excluir(id);
    }
    public static void alterar(Produto p){
        ProdutoCliente.editar(p);
    }
    public static void inserir(Produto p) {
        ProdutoCliente.inserir(p);
    }
}
