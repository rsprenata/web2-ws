/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2ws.dao;

import com.ufpr.tads.web2ws.beans.Usuario;
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
public class UsuarioCliente {
    public static String enderecoServidor = "http://localhost:8080/web2/webresources";
    
    public static List<Usuario> buscarTipos() {
        Client client = ClientBuilder.newClient();
        Response resp = client
                              .target(enderecoServidor + "/usuarios")
                              .request(MediaType.APPLICATION_JSON)
                              .get();
        List<Usuario> lista = 
                            resp.readEntity(
                            new GenericType<List<Usuario>>() {}
                            );
        return lista;
    }
}
