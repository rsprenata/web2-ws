<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="erro.jsp"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Atendimentos - Listar</title>
        
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap-4.1.3-dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/fontawesome-free-5.3.1-web/css/all.css">
    </head>
    <body>
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

            <div class="text-center form-group">
                <a href="AtendimentoServlet?action=efetuarForm" class="btn btn-success">NOVO</a>
            </div>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Data</th>
                        <th>Cliente</th>
                        <th>Produto</th>
                    </tr>
                </thead>
                <tbody>
                    <!--data/hora, produto e nome do cliente. Deve apresentar um botão de detalhes que apresenta a
                              tela atendimentoDetalhes.jsp ; -->
                    <c:forEach items="${atendimentos}" var="atendimento">
                        <tr>
                            <td><fmt:formatDate value="${atendimento.data}" pattern="dd/MM/yyyy HH:mm" /></td>
                            <td>${atendimento.cliente.nome}</td>
                            <td>${atendimento.produto.nome}</td>
                            <td>
                                <a href="AtendimentoServlet?action=detalhes&id=${atendimento.id}" class="btn"><i class="fas fa-eye"></i></a>
                                <c:if test="${atendimento.resolvido == 'N'}">
                                    <button class="btn btn-link btnAtender" onclick="confirmarResolver('${atendimento.id}')"><i class="fas fa-check"></i></button>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
            
        <div class="modal fade" id="modalConfirmarResolver" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle">Confirmar</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Deseja realmente resolver este atendimento?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                        <a id="linkRemover" class="btn btn-primary">Resolver</a>
                    </div>
                </div>
            </div>
        </div>
        
        <script src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/bootstrap-4.1.3-dist/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/jQuery-Mask-Plugin-master/dist/jquery.mask.min.js"></script>
        <script>
            function confirmarResolver(idAtendimento) {
                $('#modalConfirmarResolver #linkRemover').attr("href", "AtendimentoServlet?action=resolver&id=" + idAtendimento);
                $('#modalConfirmarResolver').modal("show");
            }
        </script>
    </body>
</html>
