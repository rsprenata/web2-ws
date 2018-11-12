package com.ufpr.tads.web2ws.servlets;

import com.google.gson.Gson;
import com.ufpr.tads.web2ws.beans.Atendimento;
import com.ufpr.tads.web2ws.beans.Cliente;
import com.ufpr.tads.web2ws.beans.Produto;
import com.ufpr.tads.web2ws.beans.TipoAtendimento;
import com.ufpr.tads.web2ws.beans.Usuario;
import com.ufpr.tads.web2ws.facade.AtendimentoFacade;
import com.ufpr.tads.web2ws.facade.ClientesFacade;
import com.ufpr.tads.web2ws.facade.ProdutosFacade;
import com.ufpr.tads.web2ws.facade.TipoAtendimentoFacade;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

/**
 *
 * @author renata.pereira
 */
@WebServlet(name = "AtendimentoServlet", urlPatterns = {"/AtendimentoServlet"})
public class AtendimentoServlet extends HttpServlet {

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

            if ("mostrar".equals(action) || null == action || "".equals(action)) {
                List<Atendimento> atendimentos = AtendimentoFacade.buscarTodos();
                request.setAttribute("atendimentos", atendimentos);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/atendimentoListar.jsp");
                rd.forward(request, response);
            } else if ("efetuarForm".equals(action)) {
                List<TipoAtendimento> tiposAtendimento = TipoAtendimentoFacade.buscarTodos();
                request.setAttribute("tiposAtendimento", tiposAtendimento);
                
                List<Produto> produtos = ProdutosFacade.buscarTodos();
                request.setAttribute("produtos", produtos);
                
                List<Cliente> clientes;
                clientes = ClientesFacade.buscarTodos();
                request.setAttribute("clientes", clientes);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/atendimento.jsp");
                rd.forward(request, response);
            } else if ("efetuar".equals(action)) {
                
                Atendimento t = new Atendimento();
                
                String dataTela = request.getParameter("dataAtual");
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy HH:mm");
                try{
                    Date data = formato.parse(dataTela);
                    t.setData(data);
                }catch(Exception e){
                    
                }
                
                Integer idTipoAtendimento = Integer.parseInt(request.getParameter("tipoAtendimento"));
                t.setTipoAtendimento(idTipoAtendimento);
                
                Integer produto = Integer.parseInt(request.getParameter("produto"));
                Produto p = new Produto();
                p.setId(produto);
                t.setProduto(p);
                
                Integer cliente = Integer.parseInt(request.getParameter("cliente"));
                Cliente c = new Cliente();
                c.setId(cliente);
                t.setCliente(c);
                t.setDescricao(request.getParameter("descricao"));
                
                if (request.getParameter("resolvido") != null) t.setResolvido("S");
                else t.setResolvido("N");
                
                AtendimentoFacade.atender(t);
                
                
                response.sendRedirect("AtendimentoServlet");
            } else if ("carregarViaAjax".equals(action)) {
                String idAtendimento = request.getParameter("Id");
        
                // Vai no BD buscar todas as cidades deste estado, em uma lista
                Atendimento at = AtendimentoFacade.buscar(Integer.parseInt(idAtendimento));

                // transforma o MAP em JSON
                String json = new Gson().toJson(at);   

                // retorna o JSON
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
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