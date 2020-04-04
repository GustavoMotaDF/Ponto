<%-- 
    Document   : funcionario
    Created on : 01/04/2020, 21:06:55
    Author     : gustavo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Funcionarios</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script type="text/javascript">
            function fMasc(objeto, mascara) {
                obj = objeto
                masc = mascara
                setTimeout("fMascEx()", 1)
            }
            function fMascEx() {
                obj.value = masc(obj.value)
            }
            function mTel(tel) {
                tel = tel.replace(/\D/g, "")
                tel = tel.replace(/^(\d)/, "($1")
                tel = tel.replace(/(.{3})(\d)/, "$1)$2")
                if (tel.length == 9) {
                    tel = tel.replace(/(.{1})$/, "-$1")
                } else if (tel.length == 10) {
                    tel = tel.replace(/(.{2})$/, "-$1")
                } else if (tel.length == 11) {
                    tel = tel.replace(/(.{3})$/, "-$1")
                } else if (tel.length == 12) {
                    tel = tel.replace(/(.{4})$/, "-$1")
                } else if (tel.length > 12) {
                    tel = tel.replace(/(.{4})$/, "-$1")
                }
                return tel;
            }
            function mCNPJ(cnpj) {
                cnpj = cnpj.replace(/\D/g, "")
                cnpj = cnpj.replace(/^(\d{2})(\d)/, "$1.$2")
                cnpj = cnpj.replace(/^(\d{2})\.(\d{3})(\d)/, "$1.$2.$3")
                cnpj = cnpj.replace(/\.(\d{3})(\d)/, ".$1/$2")
                cnpj = cnpj.replace(/(\d{4})(\d)/, "$1-$2")
                return cnpj
            }
            function mCPF(cpf) {
                cpf = cpf.replace(/\D/g, "")
                cpf = cpf.replace(/(\d{3})(\d)/, "$1.$2")
                cpf = cpf.replace(/(\d{3})(\d)/, "$1.$2")
                cpf = cpf.replace(/(\d{3})(\d{1,2})$/, "$1-$2")
                return cpf
            }
            function mCEP(cep) {
                cep = cep.replace(/\D/g, "")
                cep = cep.replace(/^(\d{2})(\d)/, "$1.$2")
                cep = cep.replace(/\.(\d{3})(\d)/, ".$1-$2")
                return cep
            }
            function mNum(num) {
                num = num.replace(/\D/g, "")
                return num
            }
        </script>
        <style>
            body{

                background: lightblue url("https://onitel.com.br/static/images/logo.png") no-repeat fixed top; 
            }
        </style>
    </head>
    <body>

        <div class="container">
            <nav class="text-right">
                <ul>
                    <li><a href="${pageContext.request.contextPath}/">Registrar Ponto</a></li>
                    <li><a href="${pageContext.request.contextPath}/Relatorios">Relatórios</a></li>
                </ul>
            </nav>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <h1 class="text-center">Funcionarios</h1>
            <form method="post" action="${pageContext.request.contextPath}/Funcionario_View"> 
                <div class="card">
                    <h5 class="card-title">Cadastrar Novo Funcionario</h5>
                    <div class="card-body">
                        <div class="form-group">
                            Nome: 
                            <input type="text" name="nome" id="nome" class="nome form-control" required/><br>
                            CPF:  
                            <input type="text" name="cpf" id="cpf" class="cpf form-control" required onkeydown="javascript: fMasc(this, mCPF);"><br>
                            <input type="submit" name="enviar" class="btn btn-outline-success" value="Cadastrar Novo"/>

                        </div>
                    </div>                    
                </div>                
            </form>    
                <div class="h2">
                                ${mensagemErro}
                                 ${mensagemSucesso}
                </div>

            <br>
            <br>
            
             <!-- Modal -->
                <div class="modal fade bd-example-modal-lg text-dark" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg modal-dialog-centered">
                        <div class="modal-content">
                            <form action="${pageContext.request.contextPath}/Funcionario-info" method="post"> 
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLongTitle">Lista de Funcionarios</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <div class="container text-left">
                                        <table class="table table-hover ">
                                            <thead>
                                                <tr> 
                                                    <th>CPF</th>                                               
                                                    <th>Nome</th> 
                                                </tr>

                                            </thead>
                                            <tbody>
                                                <c:forEach var="funcionarios" items="${funcionarios}">
                                                    <tr>        
                                                        <th><label for="gridRadios${funcionarios.idfuncionario}">${funcionarios.cpf}</label></th>

                                                        <td><label for="gridRadios${funcionarios.idfuncionario}">${funcionarios.nome}</label></td>
                                                        <td><input type="radio" id="gridRadios${funcionarios.idfuncionario}" required name="idfuncionario" value="${funcionarios.idfuncionario}"></td>
                                                    </tr>
                                                    
                                                </c:forEach>
                                                
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div class="modal-footer" >                                 
                                    <button type="button" class="btn btn-warning  mr-auto" data-dismiss="modal" name="cancelar">Cancelar</button>
                                    <button type="submit" class="btn btn-info float-right"  name="editar">Editar</button>
                                    <button type="submit" class="btn btn-danger float-right" name="excluir">Excluir</button>
                                </div>
                            </form>
                        </div>

                    </div>
                </div>

            </main>
                                <br>
                                <br>
                                <button type="button" class="btn btn-primary form-control" data-toggle="modal" data-target="#exampleModalCenter">
                    Lista de Funcionarios
                </button>
        </div>

        <!--JS-->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script>
                                $(document).ready(function () {
                                    $('.cpf').select2();
                                });
        </script>    

    </body>
</html>
