/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.exceptions;

/**
 *
 * @author renata
 */
public class ErroCarregandoProdutoException extends AppException {
    public ErroCarregandoProdutoException() {
        super("Erro ao buscar produto, tente novamente mais tarde.");
    }
}
