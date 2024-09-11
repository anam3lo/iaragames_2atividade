package com.iaragames.servlets;

import com.iaragames.dao.JogoDAO;
import com.iaragames.models.Jogo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/jogos")
public class JogoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Listar todos os jogos
        List<Jogo> jogos = JogoDAO.getAllJogos();
        request.setAttribute("jogos", jogos);
        request.getRequestDispatcher("jogos.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Incluir um novo jogo
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String desenvolvedor = request.getParameter("desenvolvedor");

        Jogo novoJogo = new Jogo(nome, descricao, desenvolvedor);
        JogoDAO.saveJogo(novoJogo);

        // Redirecionar após a inserção
        response.sendRedirect("jogos");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Atualizar jogo
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String desenvolvedor = request.getParameter("desenvolvedor");

        Jogo jogoAtualizado = new Jogo(id, nome, descricao, desenvolvedor);
        JogoDAO.updateJogo(jogoAtualizado);

        // Responder com sucesso
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Excluir um jogo
        int id = Integer.parseInt(request.getParameter("id"));
        JogoDAO.deleteJogo(id);

        // Responder com sucesso
        response.setStatus(HttpServletResponse.SC_OK);
    }
}