/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lavajato.ponto.view;

import com.lavajato.ponto.BO.FuncionarioBO;
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
@WebServlet(name = "Funcionario_View", urlPatterns = {"/Funcionario_View"})
public class Funcionario_View extends HttpServlet {
 private final FuncionarioBO funcionariobo = new FuncionarioBO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Objects.nonNull(req.getParameter("enviar"))) {
            try{
            funcionariobo.IncluirFuncionario(req.getParameter("nome"),
                                             req.getParameter("cpf"));
            
            req.setAttribute("mensagemSucesso",  "<div id=\"foo\" class=\"alert alert-success\" role=\"alert\"> <strong> Funcionario cadastrado com Sucesso! </strong> </div>"
                    + "<script>$().ready(function() {\n" +
                        "	setTimeout(function () {\n" +
                        "		$('#foo').hide(); // \"foo\" é o id do elemento que seja manipular.\n" +
                        "	}, 10000); // O valor é representado em milisegundos.\n" +
                        "});</script>");
            
            
            }catch(Exception erro){
                req.setAttribute("mensagemErro","<div id=\"foo\" class=\"alert alert-warning\" role=\"alert\"><strong>"+erro.getMessage()+"</strong></div>"
                        + "<script>$().ready(function() {\n" +
                        "	setTimeout(function () {\n" +
                        "		$('#foo').hide(); // \"foo\" é o id do elemento que seja manipular.\n" +
                        "	}, 10000); // O valor é representado em milisegundos.\n" +
                        "});</script>");
            }
        }else if(Objects.nonNull(req.getParameter("excluir"))){
            try{
                funcionariobo.ExcluirFuncionario(req.getParameter("idfuncionario"));
                req.setAttribute("mensagemSucesso", "<div id=\"foo\"  class=\"alert alert-success\" role=\"alert\">Funcionario Desativado com Sucesso</div>"
                        + "<script>\n" +
"                    $().ready(function () {\n" +
"                        setTimeout(function () {\n" +
"                            $('#foo').hide();\n" +
"                        }, 10000);\n" +
"                    });\n" +
"                </script>\n" +
"");
                
            }catch(Exception erro){
                req.setAttribute("mensagemErro", "<div id=\"foo\"  class=\"alert alert-warning\" role=\"alert\">"+erro.getMessage()+"</div>"
                +"<script>\n" +
"                    $().ready(function () {\n" +
"                        setTimeout(function () {\n" +
"                            $('#foo').hide();\n" +
"                        }, 10000);\n" +
"                    });\n" +
"                </script>\n" +
"");
                
            }
            
        }else if(Objects.nonNull(req.getParameter("editar"))){
            try {
                req.setAttribute("funcionarioEditando",funcionariobo.getFuncionario(req.getParameter("idfuncionario")));
            } catch (Exception erro) {
                req.setAttribute("mensagemErro","<div id=\"foo\" class=\"alert alert-warning\" role=\"alert\"><strong>"+erro+"</strong></div>"
                        + "<script>$().ready(function() {\n" +
                        "	setTimeout(function () {\n" +
                        "		$('#foo').hide(); // \"foo\" é o id do elemento que seja manipular.\n" +
                        "	}, 10000); // O valor é representado em milisegundos.\n" +
                        "});</script>");
            }
        }else if(Objects.nonNull(req.getParameter("alterar"))){
            try{
                funcionariobo.EditarFuncionario(req.getParameter("idfuncionario"),
                                                req.getParameter("nome"),
                                                req.getParameter("cpf"));
                req.setAttribute("mensagemSucesso",  "<div id=\"foo\" class=\"alert alert-success\" role=\"alert\"> <strong> Funcionario alterado com Sucesso! </strong> </div>"
                    + "<script>$().ready(function() {\n" +
                        "	setTimeout(function () {\n" +
                        "		$('#foo').hide(); // \"foo\" é o id do elemento que seja manipular.\n" +
                        "	}, 10000); // O valor é representado em milisegundos.\n" +
                        "});</script>");
                
            }catch(Exception erro){
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
        
                req.getRequestDispatcher("funcionario.jsp").forward(req, resp);
        
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
        
                req.getRequestDispatcher("funcionario.jsp").forward(req, resp);
        
    }
    
 
 

}
