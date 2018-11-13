/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2ws.dao;

import com.ufpr.tads.web2ws.beans.Produto;
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
public class ProdutoCliente {
    public static List<Produto> buscarProdutos() {
        Client client = ClientBuilder.newClient();
        Response resp = client
                              .target("http://localhost:43180/web2/webresourses/produtos")
                              .request(MediaType.APPLICATION_JSON)
                              .get();
        List<Produto> lista = 
                            resp.readEntity(
                            new GenericType<List<Produto>>() {}
                            );
        return lista;
    }
    
    public static Produto buscarUmProduto(Integer idProduto) {
        Client client = ClientBuilder.newClient();
        
        Produto p = client
                          .target("http://localhost:43180/web2/webresourses/produtos" + idProduto)
                          .request(MediaType.APPLICATION_JSON)
                          .get(Produto.class);
        return p;
    }
    
    public static void inserir(Produto p) {
        Client client = ClientBuilder.newClient();
        
        client.target("http://localhost:43180/web2/webresourses/produtos")
                          .request()
                          .post(Entity.json(p));
    }
    
    public static void editar(Produto p) {
        Client client = ClientBuilder.newClient();
        
        client.target("http://localhost:43180/web2/webresourses/produtos" + p.getId())
                          .request()
                          .put(Entity.json(p));
    }
    
    public static void excluir(Integer id) {
        Client client = ClientBuilder.newClient();
        
        client.target("http://localhost:43180/web2/webresourses/produtos" + id)
                          .request()
                          .delete();
    }
}
