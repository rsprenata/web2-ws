/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.Atendimento;
import com.ufpr.tads.web2.beans.Cliente;
import com.ufpr.tads.web2.beans.Produto;
import com.ufpr.tads.web2.dao.AtendimentoDao;
import com.ufpr.tads.web2.dao.ClienteDao;
import com.ufpr.tads.web2.dao.ProdutoDao;
import com.ufpr.tads.web2.exceptions.ErroCarregandoProdutoException;
import java.util.List;

/**
 *
 * @author renata.pereira
 */
public class ProdutosFacade {
    private static final ProdutoDao pDao = new ProdutoDao();
    public static List<Produto> buscarTodos() throws ErroCarregandoProdutoException {
        return pDao.carregarTodos();
    }
    
}
