/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lavajato.ponto.view;

import com.lavajato.ponto.BO.FuncionarioBO;
import com.lavajato.ponto.BO.PontoBO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gustavo
 */
@WebServlet(name = "Relatorios", urlPatterns = {"/Relatorios"})
public class Relatorios extends HttpServlet {
    private final FuncionarioBO funcionariobo = new FuncionarioBO();
    private final PontoBO pontobo = new PontoBO();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
         if(Objects.nonNull(req.getParameter("enviar"))){
            try {
                req.setAttribute("registroDataHora",pontobo.getRegistroCpfDataHora(req.getParameter("datahora"), req.getParameter("cpf")));
            } catch (Exception erro) {
                req.setAttribute("mensagemErro","<div id=\"foo\" class=\"alert alert-warning\" role=\"alert\"><strong>"+erro+"</strong></div>"
                        + "<script>$().ready(function() {\n" +
                        "	setTimeout(function () {\n" +
                        "		$('#foo').hide(); // \"foo\" é o id do elemento que seja manipular.\n" +
                        "	}, 10000); // O valor é representado em milisegundos.\n" +
                        "});</script>");
            }
        }
        
        try {   
            req.setAttribute("funcionarios",funcionariobo.getFuncionarios());
        } catch (Exception erro) {
            req.setAttribute("mensagemErro", "<div id=\"foo\" class=\"alert alert-warning\" role=\"alert\">" +erro+"</div>"
                    + "<script>\n" +
"                    $().ready(function () {\n" +
"                        setTimeout(function () {\n" +
"                            $('#foo').hide();\n" +
"                        }, 10000);\n" +
"                    });\n" +
"                </script>\n" +
"");
        }
        req.getRequestDispatcher("relatorios.jsp").forward(req, resp);
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {   
            req.setAttribute("funcionarios",funcionariobo.getFuncionarios());
        } catch (Exception erro) {
            req.setAttribute("mensagemErro", "<div id=\"foo\" class=\"alert alert-warning\" role=\"alert\">" +erro+"</div>"
                    + "<script>\n" +
"                    $().ready(function () {\n" +
"                        setTimeout(function () {\n" +
"                            $('#foo').hide();\n" +
"                        }, 10000);\n" +
"                    });\n" +
"                </script>\n" +
"");
        }
         req.getRequestDispatcher("relatorios.jsp").forward(req, resp);
    }
    
}
