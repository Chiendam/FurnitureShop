<%--
  Created by IntelliJ IDEA.
  User: Dam Duc Chien
  Date: 6/12/2021
  Time: 4:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <base href="<%=basePath%>">
    <title><%=basePath%></title>
    <c:import url="../../layout/header.jsp"/>
    <style>
        tr th{
            color: white !important;
        }

        .form-group .col-lg-3{
            text-align: center;
        }
        form a{
            color: white !important;
        }

        .error {
            color: red; font-weight: bold;
        }
    </style>
</head>
<body>
<!-- Begin page -->
<div id="wrapper">
    <!-- Topbar Start -->
    <c:import url="../../layout/navbar.jsp"/>
    <!-- end Topbar --> <!-- ========== Left Sidebar Start ========== -->
    <c:import url="../../layout/sidebar.jsp"/>
    <!-- Left Sidebar End -->
    <div class="content-page">
        <div class="content">

            <!-- Start Content-->
            <div class="container-fluid">
                <!-- start page title -->
                <div class="row">
                    <div class="col-12">
                        <div class="page-title-box">
                            <h4 class="page-title">Thêm mới</h4>
                            <div class="page-title-right">
                                <ol class="breadcrumb p-0 m-0">
                                    <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
                                    <li class="breadcrumb-item"><a href="#">Quản lý thể loại</a></li>
                                    <li class="breadcrumb-item active">Thêm mới</li>
                                </ol>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
                <!-- end page title -->
               <c:if test="${null != message}">
                   <div class="alert alert-success" role="alert">
                       ${message}
                   </div>
               </c:if>
                <c:if test="${null != messageError}">
                    <div class="alert alert-danger" role="alert">
                            ${messageError}
                    </div>
                </c:if>
                ${messageError}
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <div style="padding-left: 100px">
                                    <form:form id="formAddCategory" method="post" action="/admin/category/create" modelAttribute="category">
                                        <div class="form-group row">
                                            <label class="col-lg-2 col-form-label" for="name">Tên thể loại<span class="text-danger">*</span></label>
                                            <div class="col-lg-8">
                                                <form:input type="text" path="name" required="true" class="form-control" placeholder="Tên thể loại ....."/>
                                                <form:errors path="name" cssClass="error"/>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-lg-2 col-form-label" for="description">Mô tả<span class="text-danger">*</span></label>
                                            <div class="col-lg-8">
                                                <form:textarea rows="3" path="description" required="true" id="description" name="description" class="form-control" placeholder="Mô tả thể loại ....."/>
                                                <form:errors path="description" cssClass="error"/>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <div class="col-lg-2 " ></div>
                                            <div class="col-lg-8" style="text-align: right">
                                                <button class="btn btn-primary waves-effect waves-light mr-1" type="submit">
                                                    Lưu
                                                </button>
                                                <a type="reset" class="btn btn-secondary waves-effect waves-light" href="<%=basePath%>/admin/category/">
                                                    Trở về
                                                </a>
                                            </div>
                                        </div>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- end container-fluid -->
        </div>
        <!-- end content -->
        <!-- Footer Start -->
        <c:import url="../../layout/footer.jsp"/>
        <!-- end Footer -->
    </div>
    <!-- ============================================================== -->
    <!-- End Page content -->
    <!-- ============================================================== -->
</div>
<!-- Vendor js -->
<c:import url="../../include/filesInclude.jsp"/>
<script src="/assets/libs/sweetalert2/sweetalert2.min.js"></script>

<!-- Sweet alert init js-->
<%--<script src="/assets/js/pages/sweet-alerts.init.js"></script>--%>
<script>
    $(document).ready(function(){
        $("#formAddUser").parsley()
    });
   function load(){
        Swal.fire({
            title:"Good job!",
            text:"You clicked the button!",
            type:"success",
            confirmButtonColor:"#348cd4"})
   }
</script>
</body>
</html>