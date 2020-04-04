<%-- 
    Document   : relatorios
    Created on : 02/04/2020, 13:32:37
    Author     : gustavo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Relatórios</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/css/select2.min.css" rel="stylesheet" />
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
            li {
                list-style-type: none;
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
            <c:if test="${empty registroDataHora}">  
            <div class="card bg-info ">                
                <div class="card-body">
                    <div class="card">
                        <h5 class="card-title">Relatorio 1</h5>
                        <div class="card-body">
                            <form action="" method="post">
                                <div class="form-group text-center">
                                    <select name="cpf" id="cpf" class="cpf form-control">
                                        <option value="">Selecione o funcionario...</option>
                                        <c:forEach var="funcionarios" items="${funcionarios}">
                                            <option value="${funcionarios.cpf}">${funcionarios.nome}</option>
                                        </c:forEach>                                       
                                    </select>
                                    <br>
                                    <br>
                                    <input type="date" name="datahora" class="form-control" />
                                    <br>
                                    <div class="form-group">
                                        <input type="submit" name="enviar" value="Gerar Relatorio" class="form-control lg btn btn-info"/>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <br>
                    <div class="card">
                        <div class="card-body">
                            teste
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-body">
                            teste
                        </div>
                    </div>
                    
                </div>                
            </div>
            </c:if>
            <c:if test="${not empty registroDataHora}">
                <div class="row">
                    <div class="md-6">
                        <label>Nome:</label>${registroDataHora[0][0]}<br>
                        
                    </div>
                    <div class="md-6">
                        <labe >CPF:</labe>${registroDataHora[1][1]}
                    </div>
                </div>
                            
                            
                <table border="1" class="table table-hover">
                    <c:forEach var="registroDataHora" items="${registroDataHora}">
                        <tr>                            
                            <td>${registroDataHora[2]}</td>
                            <td></td>
                            <td>${registroDataHora[3]}</td>
                        </tr>
                    </c:forEach>                    
                </table>
            </c:if>
        </div>
                <script src="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/js/select2.min.js"></script>
        <script>
            $(document).ready(function() {
                $('.cpf').select2();
                $('.tipo').select2();
            });
        </script>    
        <!--JS-->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/js/select2.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
   
    </body>
</html>
