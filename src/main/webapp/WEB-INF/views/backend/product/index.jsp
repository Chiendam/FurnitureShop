<%--
  Created by IntelliJ IDEA.
  User: Dam Duc Chien
  Date: 6/11/2021
  Time: 1:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String addressImage = "/image-Poster/";
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/admin/product";
%>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <base href="<%=basePath%>">
    <title>Quản lý sản phẩm</title>
    <c:import url="../../layout/header.jsp"/>
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
                            <h4 class="page-title">Quản lý sản phẩm</h4>
                            <div class="page-title-right">
                                <ol class="breadcrumb p-0 m-0">
                                    <li class="breadcrumb-item"><a href="#">Velonic</a></li>
                                    <li class="breadcrumb-item"><a href="#">Dashboard</a></li>
                                    <li class="breadcrumb-item active">Dashboard 1</li>
                                </ol>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
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
                <!-- end page title -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <div style="float: left">
                                    <a type="button" id="btnShowAdd" class="btn btn-success" href="<%=basePath%>/create" style="color: white; margin-bottom: 20px;" title="Thêm mới sản phẩm">Thêm mới</a>
                                </div>
                                <div style="float: right" class="list-unstyled topnav-menu topnav-menu-left m-0">
                                    <div class="d-none d-lg-block">
                                        <form class="app-search" method="get" action="/admin/product/">
                                            <div class="app-search-box">
                                                <div class="input-group">
                                                    <input type="text" name="search" class="form-control" placeholder="Email hoặc tên......">
                                                    <div class="input-group-append">
                                                        <button class="btn" type="submit" style="color: black; background-color: rgba(108,117,125,.09)" >
                                                            <i class="fas fa-search"></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <div class="table-responsive">
                                    <table class="table mb-0" id="tableUser">
                                        <thead class="thead-dark">
                                        <tr>
                                            <th>#</th>
                                            <th>Tên sản phẩm</th>
                                            <th>Ảnh</th>
                                            <th>Loại sản phẩm</th>
                                            <th>Gía bán</th>
                                            <th>Trạng thái</th>
                                            <th style="text-align: center">Thao tác</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${products}" var="product" varStatus="s">
                                            <tr>
                                                <td scope="row">${start + s.index+1}</td>
                                                <td>${product.name}</td>
                                                <td><img src="<%=addressImage%>${product.imgPoster}" style="width: 200px"></td>
                                                <td>
                                                    <c:forEach items="${categories}" var="category">
                                                        <c:if test="${category.id == product.category.id}">
                                                            ${category.name}
                                                        </c:if>
                                                    </c:forEach>
                                                </td>
                                                <td>${product.price} VNĐ</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${product.status == 0}">
                                                            <span class="badge badge-success" style="padding: 10px; border-radius: 3em;">Còn hàng</span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span class="badge badge-danger" style="padding: 10px; border-radius: 3em;">Hết hàng</span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td style="text-align: center">
                                                    <a type="button" class="btn btn-success" href="<%=basePath%>/edit/${product.id}" style="color: white" title="Sửa thông tin sản phẩm"><i class="ion ion-md-create"></i></a>
                                                    <a type="button" class="btn btn-danger" href="<%=basePath%>/delete/${product.id}" style="color: white" title="Xóa thông tin sản phẩm" onclick="return confirm('Bạn có chắc chắn muỗn xóa? Dữ liệu đã xóa không thể khôi phục!')"><i class="ion ion-md-trash"></i></a>
                                                    <a type="button" class="btn btn-info " style="color: white" title="Xem chi tiết"><i class="ion ion-md-eye"></i></a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <nav aria-label="Page navigation example">
                            <ul class="pagination justify-content-center">
                                <c:if test="${pageNow > 1}">
                                    <li class="page-item " >
                                        <a class="page-link" href="<%=basePath%>/${parameter}page=${pageNow-1}">Trang trước</a>
                                    </li>
                                </c:if>
                                <c:if test="${pageNow == 1}">
                                    <li class="page-item disabled" >
                                        <a class="page-link" href="#" >Trang trước</a>
                                    </li>
                                </c:if>
                                <c:forEach var="page" begin="1" end="${numberPage}">
                                    <c:if test="${page < 7}">
                                        <li class="page-item"><a class="page-link" href="<%=basePath%>/${parameter}page=${page}">${page}</a></li>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${numberPage > 7}">
                                    <li class="page-item disabled"><a class="page-link " href="#">...</a></li>
                                    <li class="page-item"><a class="page-link" href="<%=basePath%>/${parameter}page=${numberPage}">${numberPage}</a></li>
                                </c:if>
                                <c:if test="${pageNow < numberPage}">
                                    <li class="page-item " >
                                        <a class="page-link" href="<%=basePath%>/${parameter}page=${pageNow+1}">Trang sau</a>
                                    </li>
                                </c:if>
                                <c:if test="${pageNow == numberPage}">
                                    <li class="page-item disabled" >
                                        <a class="page-link" href="#" >Trang sau</a>
                                    </li>
                                </c:if>
                            </ul>
                        </nav>
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
</body>
</html>