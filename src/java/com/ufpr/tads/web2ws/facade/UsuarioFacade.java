/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2ws.facade;

import com.ufpr.tads.web2ws.beans.Usuario;
import com.ufpr.tads.web2ws.dao.UsuarioCliente;
import java.util.List;

/**
 *
 * @author renata.pereira
 */
public class UsuarioFacade {
    public static List<Usuario> buscarTodos() {
        return UsuarioCliente.buscarTipos();
    }
}
