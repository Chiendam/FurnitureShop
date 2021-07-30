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
                                    <li class="breadcrumb-item"><a href="#">Quản lý người dùng</a></li>
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
                                    <form:form id="formAddUser" method="post" action="/admin/user/create" modelAttribute="user">
                                        <div class="form-group row">
                                            <label class="col-lg-2 col-form-label" for="fullName">Họ và tên<span class="text-danger">*</span></label>
                                            <div class="col-lg-8">
                                                <form:input type="text" path="fullName" required="true" class="form-control" placeholder="Họ và tên ....."/>
                                                <form:errors path="fullName" cssClass="error"/>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-lg-2 col-form-label" for="phone">Số điện thoại<span class="text-danger">*</span></label>
                                            <div class="col-lg-8">
                                                <form:input type="text" path="phone" required="true" id="phone" name="phone" class="form-control" placeholder="Số điện thoại ....."/>
                                                <form:errors path="phone" cssClass="error"/>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-lg-2 col-form-label" for="email">Email<span class="text-danger">*</span></label>
                                            <div class="col-lg-8">
                                                <form:input type="text" path="email" name="email" required="true" id="email" class="form-control" placeholder="Email ......"/>
                                                <form:errors path="email" cssClass="error"/>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-lg-2 col-form-label" for="">Nhóm người dùng<span class="text-danger">*</span></label>
                                            <div class="col-lg-8">
                                                <form:select class="js-example-basic-single form-control select2-input" path="role" id="addRole"  name="role">
                                                    <form:option value="1">Khách hàng</form:option>
                                                    <form:option value="2">Quản trị viên</form:option>
                                                </form:select>
                                                <form:errors path="role" cssClass="error"/>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-lg-2 col-form-label"  for="password">Mật khẩu<span class="text-danger">*</span></label>
                                            <div class="col-lg-8">
                                                <form:input type="text" path="password" required="" id="password" name="password" class="form-control" placeholder="Mật khẩu ......"/>
                                                <form:errors path="password" cssClass="error"/>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <div class="col-lg-2 " ></div>
                                            <div class="col-lg-8" style="text-align: right">
                                                <button class="btn btn-primary waves-effect waves-light mr-1" type="submit">
                                                    Lưu
                                                </button>
                                                <a type="reset" class="btn btn-secondary waves-effect waves-light" href="<%=basePath%>/admin/user/">
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