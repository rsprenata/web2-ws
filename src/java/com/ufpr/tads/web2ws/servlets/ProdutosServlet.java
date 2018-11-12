package com.ufpr.tads.web2ws.servlets;

import com.ufpr.tads.web2ws.beans.Produto;
import com.ufpr.tads.web2ws.exceptions.ErroCarregandoProdutoException;
import com.ufpr.tads.web2ws.facade.ProdutosFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ProdutosServlet", urlPatterns = {"/ProdutosServlet"})
public class ProdutosServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("list".equals(action) || null == action || "".equals(action)) {
            List<Produto> produtos;
            try {
                produtos = ProdutosFacade.buscarTodos();

                request.setAttribute("produtos", produtos);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/produtosListar.jsp");
                rd.forward(request, response);
            } catch (ErroCarregandoProdutoException ex) {
                request.setAttribute("msg", ex.getMessage());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/index.jsp");
                rd.forward(request, response);
            }
        } else if ("show".equals(action)) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            Produto produto;
            produto = ProdutosFacade.buscar(id);
            request.setAttribute("produto", produto);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/produtosVisualizar.jsp");
            rd.forward(request, response);
        } else if ("formUpdate".equals(action)) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            Produto produto;
            produto = ProdutosFacade.buscar(id);
            request.setAttribute("produto", produto);
            request.setAttribute("form", "alterar");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/produtosForm.jsp");
            rd.forward(request, response);
            
        } else if ("remove".equals(action)) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            ProdutosFacade.remover(id);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/ProdutosServlet?action=list");
            rd.forward(request, response);
        } else if ("update".equals(action)) {
            Produto produto = new Produto();
            produto.setId(Integer.parseInt(request.getParameter("id")));
            produto.setNome(request.getParameter("nome"));
            ProdutosFacade.alterar(produto);
            response.sendRedirect("ProdutosServlet");
            
        } else if ("formNew".equals(action)) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/produtosForm.jsp");
            rd.forward(request, response);
        } else if ("new".equals(action)) {
            Produto produto = new Produto();
            produto.setNome(request.getParameter("nome"));
            ProdutosFacade.inserir(produto);
            response.sendRedirect("ProdutosServlet");
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
