package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.web2.beans.Produto;
import com.ufpr.tads.web2.exceptions.ErroCarregandoProdutoException;
import com.ufpr.tads.web2.facade.ProdutosFacade;
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

                request.setAttribute("clientes", produtos);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/clientesListar.jsp");
                rd.forward(request, response);
            } catch (ErroCarregandoProdutoException ex) {
                request.setAttribute("msg", ex.getMessage());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/portal.jsp");
                rd.forward(request, response);
            }
        } else if ("show".equals(action)) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            Cliente cliente;
            try {
                cliente = ClientesFacade.buscar(id);

                request.setAttribute("cliente", cliente);
                request.setAttribute("estado", EstadosFacade.carregarUm(cliente.getCidade().getIdEstado()));
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/clientesVisualizar.jsp");
                rd.forward(request, response);
            } catch (ClienteNaoExisteException | ErroBuscandoClienteException ex) {
                request.setAttribute("msg", ex.getMessage());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/ClientesServlet?action=list");
                rd.forward(request, response);
            }
        } else if ("formUpdate".equals(action)) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            Cliente cliente;
            try {
                cliente = ClientesFacade.buscar(id);

                List<Estado> estados = EstadosFacade.buscarTodos();

                request.setAttribute("estados", estados);
                request.setAttribute("cliente", cliente);
                request.setAttribute("form", "alterar");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/clientesForm.jsp");
                rd.forward(request, response);
            } catch (ClienteNaoExisteException | ErroBuscandoClienteException ex) {
                request.setAttribute("msg", ex.getMessage());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/ClientesServlet?action=list");
                rd.forward(request, response);
            }
        } else if ("remove".equals(action)) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            try {
                ClientesFacade.remover(id);
            } catch (ErroRemovendoClienteException ex) {
                request.setAttribute("msg", ex.getMessage());
            }
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/ClientesServlet?action=list");
            rd.forward(request, response);
        } else if ("update".equals(action)) {
            Cliente cliente = new Cliente();
            cliente.setId(Integer.parseInt(request.getParameter("id")));
            cliente.setCpf(request.getParameter("cpf"));
            cliente.setNome(request.getParameter("nome"));
            cliente.setEmail(request.getParameter("email"));
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            try {
                cliente.setData(formato.parse(request.getParameter("data")));
            } catch (ParseException ex) {
                Logger.getLogger(ProdutosServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            cliente.setRua(request.getParameter("rua"));
            cliente.setNr(Integer.parseInt(request.getParameter("nr")));
            cliente.setCep(request.getParameter("cep"));
            Cidade cidade = new Cidade();
            cidade.setId(Integer.parseInt(request.getParameter("cidade")));
            cliente.setCidade(cidade);

            try {
                ClientesFacade.validar(cliente);

                ClientesFacade.alterar(cliente);
                response.sendRedirect("ClientesServlet");
            } catch (CPFDuplicadoClienteException | ErroBuscandoClienteException | CPFInvalidoClienteException ex) {
                request.setAttribute("msg", ex.getMessage());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/ClientesServlet?action=formUpdate");
                rd.forward(request, response);
            } catch (ErroEditandoClienteException ex) {
                request.setAttribute("msg", ex.getMessage());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/ClientesServlet?action=list");
                rd.forward(request, response);
            }
        } else if ("formNew".equals(action)) {
            List<Estado> estados = EstadosFacade.buscarTodos();

            request.setAttribute("estados", estados);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/clientesForm.jsp");
            rd.forward(request, response);
            //response.sendRedirect("clientesNovo.jsp");
        } else if ("new".equals(action)) {
            Cliente cliente = new Cliente();
            cliente.setCpf(request.getParameter("cpf"));
            cliente.setNome(request.getParameter("nome"));
            cliente.setEmail(request.getParameter("email"));
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            try {
                cliente.setData(formato.parse(request.getParameter("data")));
            } catch (ParseException ex) {
                Logger.getLogger(ProdutosServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            cliente.setRua(request.getParameter("rua"));
            cliente.setNr(Integer.parseInt(request.getParameter("nr")));
            cliente.setCep(request.getParameter("cep"));
            Cidade cidade = new Cidade();
            cidade.setId(Integer.parseInt(request.getParameter("cidade")));
            cliente.setCidade(cidade);

            cliente.setId(0);
            try {
                ClientesFacade.validar(cliente);

                ClientesFacade.inserir(cliente);
                response.sendRedirect("ClientesServlet");
            } catch (CPFDuplicadoClienteException | ErroBuscandoClienteException | CPFInvalidoClienteException ex) {
                request.setAttribute("msg", ex.getMessage());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/ClientesServlet?action=formNew");
                rd.forward(request, response);
            } catch (ErroInserindoClienteException ex) {
                request.setAttribute("msg", ex.getMessage());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/ClientesServlet?action=list");
                rd.forward(request, response);
            }
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
