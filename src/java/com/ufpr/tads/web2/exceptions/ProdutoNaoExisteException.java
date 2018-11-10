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
public class ProdutoNaoExisteException extends AppException {
    public ProdutoNaoExisteException() {
        super("Produto não existe.");
    }
}
