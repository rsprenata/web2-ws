<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="erro.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Página inicial</title>
        
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap-4.1.3-dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    </head>
    <body style="height: 100%;">
        <div class="container" style="min-height: calc(100vh - 60px);">
            <div class="row">
                <div class="col-md-4 offset-md-4">
                    <div class="card login-form">
                        <div class="card-header">
                            <h3 class="card-title">Funcionalidades</h3>
                        </div>
                        <div class="card-body">
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/ProdutosServlet?op=listar">Produtos</a>
                            <a class="btn btn-warning" href="${pageContext.request.contextPath}/AtendimentoServlet?op=mostrar">Atendimentos</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <script src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/bootstrap-4.1.3-dist/js/bootstrap.min.js"></script>
    </body>
    <footer class="footer">
        <div class="footer-copyright text-center">
            Sistema de produtos
        </div>
    </footer>
</html>
