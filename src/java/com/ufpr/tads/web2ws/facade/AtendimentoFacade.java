/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2ws.facade;

import com.ufpr.tads.web2ws.beans.Atendimento;
import com.ufpr.tads.web2ws.dao.AtendimentoCliente;
import java.util.List;

/**
 *
 * @author renata.pereira
 */
public class AtendimentoFacade {
    public static Atendimento buscar(Integer idAtendimento) {
        return AtendimentoCliente.buscarUmAtendimento(idAtendimento);
    }
    public static void atender(Atendimento at) {
        AtendimentoCliente.inserir(at);
    }
    
    public static List<Atendimento> buscarTodos() {
        return AtendimentoCliente.buscarAtendimentos();
    }
    public static void resolver(Atendimento a){
        AtendimentoCliente.resolver(a);
    }
}
