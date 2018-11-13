/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2ws.dao;

import com.ufpr.tads.web2ws.beans.Atendimento;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author renata
 */
public class AtendimentoCliente {
    public static String enderecoServidor = "http://localhost:8080/web2/webresources";
    
    public static List<Atendimento> buscarAtendimentos() {
        Client client = ClientBuilder.newClient();
        Response resp = client
                              .target(enderecoServidor + "/atendimentos")
                              .request(MediaType.APPLICATION_JSON)
                              .get();
        List<Atendimento> lista = 
                            resp.readEntity(
                            new GenericType<List<Atendimento>>() {}
                            );
        return lista;
    }
    
    public static Atendimento buscarUmAtendimento(Integer idAtendimento) {
        Client client = ClientBuilder.newClient();
        
        Atendimento a = client
                          .target(enderecoServidor + "/atendimentos/" + idAtendimento)
                          .request(MediaType.APPLICATION_JSON)
                          .get(Atendimento.class);
        return a;
    }
    
    public static void inserir(Atendimento a) {
        Client client = ClientBuilder.newClient();
        
        client.target(enderecoServidor + "/atendimentos")
                          .request()
                          .post(Entity.json(a));
    }
    
    public static void resolver(Atendimento a) {
        Client client = ClientBuilder.newClient();
        
        client.target(enderecoServidor + "/atendimentos/" + a.getId())
                          .request()
                          .put(Entity.json(a));
    }
}
