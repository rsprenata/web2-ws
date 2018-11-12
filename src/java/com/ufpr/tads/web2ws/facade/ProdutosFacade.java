/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2ws.facade;
import com.ufpr.tads.web2ws.beans.Produto;
import com.ufpr.tads.web2ws.dao.ProdutoDao;
import com.ufpr.tads.web2ws.exceptions.ErroCarregandoProdutoException;
import com.ufpr.tads.web2ws.exceptions.ProdutoNaoExisteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author renata.pereira
 */
public class ProdutosFacade {
    private static final ProdutoDao pDao = new ProdutoDao();
    public static List<Produto> buscarTodos() throws ErroCarregandoProdutoException {
        return pDao.carregarTodos();
    }
    public static Produto buscar(int id) {
        try {
            return pDao.carregarUm(id);
        } catch (ProdutoNaoExisteException ex) {
            Logger.getLogger(ProdutosFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static void remover(int id) {
        pDao.removerUm(id);
    }
    public static void alterar(Produto p){
        pDao.editarUm(p);
    }
    public static void inserir(Produto p) {
        pDao.adicionarUm(p);
    }
}
