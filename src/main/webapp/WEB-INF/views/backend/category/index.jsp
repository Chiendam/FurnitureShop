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
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/admin/category";
    String para = request.getQueryString();
%>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <base href="<%=basePath%>">
    <title>Quản lý người dùng</title>
    <c:import url="../../layout/header.jsp"/>
    <style>
        tr th{
            color: white !important;
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
                            <h4 class="page-title">Quản lý thể loại</h4>
                            <div class="page-title-right">
                                <ol class="breadcrumb p-0 m-0">
                                    <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
                                    <li class="breadcrumb-item active">Quản lý thể loại</li>
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
                                    <a type="button" id="btnShowAdd" class="btn btn-success" href="<%=basePath%>/create" style="color: white; margin-bottom: 20px;" title="Thêm mới thể loại">Thêm mới</a>
                                </div>
                                <div style="float: right" class="list-unstyled topnav-menu topnav-menu-left m-0">
                                    <div class="d-none d-lg-block">
                                        <form class="app-search" method="get" action="/admin/category/">
                                            <div class="app-search-box">
                                                <div class="input-group">
                                                    <input type="text" name="search" class="form-control" placeholder="Tên thể loại......">
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
                                            <th>Tên thể loại</th>
                                            <th>Mô tả</th>
                                            <th style="text-align: center">Thao tác</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${categories}" var="category" varStatus="s">
                                            <tr>
                                                <td scope="row">${start+s.index+1}</td>
                                                <td>${category.name}</td>
                                                <td>${category.description}</td>
                                                <td style="text-align: center">
                                                    <a type="button" class="btn btn-success" href="<%=basePath%>/edit/${category.id}" style="color: white" title="sửa thông tin thể loại"><i class="ion ion-md-create"></i></a>
                                                    <a type="button" class="btn btn-danger" href="<%=basePath%>/delete/${category.id}" style="color: white" title="Xóa thông tin thể loại" onclick="return confirm('Bạn có chắc chắn muỗn xóa? Dữ liệu đã xóa không thể khôi phục!')"><i class="ion ion-md-trash"></i></a>
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
                                        <a class="page-link" href="<%=basePath%>/${parameter}page=${pageNow + 1}">Trang sau</a>
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

                <!-- end row -->

                <!-- End row -->

                <!-- End row -->

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
<script>

    //create
    $('.btnAdd').click(function (e) {
        e.preventDefault();
        // $('#formAddUser')[0].reset();
        // $('#formAddUser').validate().resetForm();
        $('#modalAdd').modal('show');
    });

    $('#formAddUser').on('click','.btnSave',function(e){
        e.preventDefault();
        // let id_semester = $(this).attr('data-id');
        console.log(123);
        // if(!$('#formAddFaculty').valid()) return false;
        let data = $('#formAddUser').serialize();
        console.log(data)
        $.ajax({
            type:'post',
            url:'/user/store',
            data: {
                fullName: $("#addFullName").val(),
                phone: $("#addPhone").val(),
                email: $("#addEmail").val(),
                role: $("#addRole").val()
            },
            success:function(res){
                if(!res.error){
                    $('#listFaculty').DataTable().ajax.reload();
                    $('#addModalFaculty').modal('hide');
                    toastr.success('Thêm mới thành công');
                }else{
                    toastr.error('Thêm mới thất bại');
                }
            }

        })
    });


    $('#tableUser').on('click','.btnEdit',function (e) {
        e.preventDefault();
        // $('#formEditFaculty')[0].reset();
        // $('#formEditFaculty').validate().resetForm();
        let id_faculty = $(this).attr('data-id');
        console.log(id_faculty);
        // $('#btnSaveformEditFaculty').val(id_faculty);
        $.ajax({
            type: 'post',
            url: '/user/test/'+id_faculty,
            success:function(res){
                if(res != null){
                    console.log(res);

                    //     let faculty = res.faculty;
                    //     $('#edit_faculty_id').val(faculty.faculty_id);
                    //     $('#edit_faculty_name').val(faculty.name);
                    $("#modalEdit").modal('show');
                }
            }
        });
    });


</script>
<!-- Datatables init -->
<%--<script src="/assets/js/pages/datatables.init.js"></script>--%>
<%--<script>--%>
<%--    $(document).ready(function(){--%>
<%--        var table = $('#tableUser').dataTable( {--%>
<%--            "processing": true,--%>
<%--            "serverSide": true,--%>
<%--            "searching": true,--%>
<%--            "destroy": true,--%>
<%--            "responsive": true,--%>
<%--            language: {--%>
<%--                sProcessing:   "Đang xử lý...",--%>
<%--                sLengthMenu:   "Xem _MENU_ mục",--%>
<%--                sZeroRecords:  "Không tìm thấy dòng nào phù hợp",--%>
<%--                sInfo:         "Đang xem _START_ đến _END_ trong tổng số _TOTAL_ mục",--%>
<%--                sInfoEmpty:    "Đang xem 0 đến 0 trong tổng số 0 mục",--%>
<%--                sInfoFiltered: "(được lọc từ _MAX_ mục)",--%>
<%--                sSearch: 'Tìm kiếm',--%>
<%--                lengthMenu: '_MENU_ bản ghi/trang',--%>
<%--                oPaginate: {--%>
<%--                    "sFirst":    "Đầu",--%>
<%--                    "sPrevious": "Trước",--%>
<%--                    "sNext":     "Tiếp",--%>
<%--                    "sLast":     "Cuối"--%>
<%--                }--%>
<%--            },--%>
<%--            "ajax": {--%>
<%--                "url": "/user/getAll",--%>
<%--                "type": "POST",--%>
<%--                "dataType": "json",--%>
<%--                "contentType": "application/json",--%>
<%--                "dataSrc": "",--%>
<%--            },--%>
<%--            "columns": [--%>
<%--                {"data": "id", "width": "20%"},--%>
<%--                {"data": "fullName", "width": "20%"},--%>
<%--                {"data": "phone","width": "20%"},--%>
<%--                {"data": "email", "width": "20%"},--%>
<%--            ]--%>
<%--        });--%>
<%--    });--%>
<%--</script>--%>
</body>
</html>