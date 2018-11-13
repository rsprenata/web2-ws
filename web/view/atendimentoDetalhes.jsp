<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="erro.jsp"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Detalhes do Atendimento</title>
        
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap-4.1.3-dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/fontawesome-free-5.3.1-web/css/all.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/jquery-ui-1.12.1.custom/jquery-ui.min.css">
    </head>
    <body>
        <!--menu -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary fixed-top" id="sideNav">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="container navbar-nav">
                <li class="nav-item">
                  <a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/ProdutosServlet?op=listar">Produtos</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/AtendimentoServlet?op=mostrar">Atendimentos</a>
                </li>
              </ul>
            </div>
        </nav>
        <div class="container wrapper">
            <h1>Olá ${logado.nome}</h1>
            
            <div class="row">
                <div class="col-md-6 offset-md-3">
                    <div class="card">
                        <div class="card-header">
                            <h3 class="card-title">Atendimento</h3>
                        </div>
                        <div class="card-body">
                            <fmt:formatDate var="dataAtendimento" value="${atendimento.data}" pattern="dd/MM/yyyy HH:mm" />
                                <div class="form-group">
                                    <input class="form-control" id="dataAtual" name="dataAtual" value="${dataAtendimento}" readonly/>
                                      
                                </div>
                                
                                <div class="form-group">
                                    <input class="form-control" value="Tipo: ${atendimento.tipo.nome}" readonly/>
                                </div>                               
                                <div class="form-group">
                                    <input class="form-control" value="Cliente: ${atendimento.cliente.nome}" readonly/>
                                </div>  
                                <div class="form-group">
                                    <input class="form-control" value="Produto: ${atendimento.produto.nome}" readonly/>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" value="Usuario: ${atendimento.usuario.nome}" readonly/>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" value="Status: ${atendimento.resolvido=='S' ? 'Resolvido' : 'Não resolvido'}" readonly/>
                                </div>
                                <div class="form-group">
                                    <label for="descricao">Descrição</label>
                                    <textarea class="form-control" readonly id="descricao" rows="3" name="descricao">${atendimento.descricao}</textarea>
                                </div>
                                
                                <a href="AtendimentoServlet" class="btn btn-lg btn-light btn-block">Voltar</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <script src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/bootstrap-4.1.3-dist/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/jQuery-Mask-Plugin-master/dist/jquery.mask.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
    </body>
</html>