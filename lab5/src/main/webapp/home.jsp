<%@ page import="pojo.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.ProductDAO" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <!-- Latest compiled and minified CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

  <!-- jQuery library -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>

  <!-- Latest compiled JavaScript -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
        crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
          crossorigin="anonymous"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <title>Insert title here</title>

</head>
<body>
<div class="container">
  <h1 class="mt-5">Product Management</h1>
  <h3>Xin chao ${username},<a href="/lab5_war_exploded/index.jsp"> Logout</a></h3>
  <div class="mt-5 row gx-5">
    <div class="col-4">
      <form action="./homeServlet" method="POST">
        <div class="mb-3">
          <label for="name" class="form-label" >Name</label>
          <input  type="text" class="form-control" id="name" name ="name" placeholder="Ten san pham">
        </div>
        <div class="mb-3">
          <label for="price" class="form-label" >Price</label>
          <input  type="number" class="form-control" id="price" name ="price" placeholder="Nhap gia san pham">
        </div>
        <div class="form-group">
          <span id="form-message"></span>
        </div>
        <button type="submit" class="btn btn-primary" id="add" onclick="handleCreateFrom()">Them san pham</button>
      </form>
    </div>
    <div class="col-6">
      <table class="table table-striped border" >
        <thead>
        <tr>
          <th scope="col"> Id</th>
          <th scope="col"> Name</th>
          <th scope="col"> Price</th>
          <th scope="col"> Operation</th>
        </tr>
        </thead>
        <tbody>
        <% ProductDAO productDAO = ProductDAO.getInstance();List<Product> product = new ArrayList<Product>();product = productDAO.readAll();if(product ==null){%>
        <div class="alert alert-danger">
          <p>Don't have any products</p>
        </div>
        <%}%>
        <%if(product !=null){ for(Product p : product){%>
          <tr>
            <td><%=p.getId()%></td>
            <td><%=p.getName()%></td>
            <td><%=p.getPrice()%></td>
            <td>
              <input type="button" class="btn btn-danger delete-btn " value="Xóa" id=<%=p.getId()%>  href="http://localhost:8080/lab5_war_exploded/home.jsp?action=delete&id=<%=p.getId()%>" onclick="Delete(<%=p.getId()%>)">
<%--              <input type="button" class="btn btn-danger delete-btn" data-id="<%=p.getId()%>" value="Xóa"  data-toggle="modal" data-target="#myModal">--%>
            </td>
          </tr>

        <%}}%>

<%--        <c:if test="${product == null}">--%>
<%--          <div class="alert alert-danger">--%>
<%--            <p>Don't have any products</p>--%>
<%--          </div>--%>
<%--        </c:if>--%>
<%--        <c:if test="${product != null}">--%>

<%--        <c:forEach item="${product}" var="item">--%>
<%--          <tr>--%>
<%--            <td>--%>
<%--              <c:out value="${item.id}"/>--%>
<%--                ${item.id}--%>
<%--            </td>--%>
<%--            <td>--%>
<%--              <c:out value="${item.name}"/>--%>
<%--              ${item.name}--%>
<%--            </td>--%>
<%--            <td>--%>
<%--              <c:out value="${item.price}"/>--%>
<%--                ${item.price}--%>
<%--            </td>--%>
<%--            <td>--%>
<%--              <input type="button" class="btn btn-danger" value="Xóa" data-toggle="modal" data-target="#myModal" >--%>
<%--            </td>--%>
<%--          </tr>--%>
<%--        </c:forEach>--%>
<%--        </c:if>--%>

        </tbody>
      </table>
    </div>
  </div>
</div>
<div id="myModal" class="modal fade" role="dialog">
  <!-- Confirm delete -->
  <div>
    <div class="modal-dialog">
      <div class="modal-content">

        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Xóa san pham</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>

        <!-- Modal body -->
        <div class="modal-body">
          Bạn có chắc rằng muốn xóa sản phẩm<strong> </strong>?
        </div>

        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-danger"  id="confirmDeletebtn"  >Xóa</button>
          <button type="button" class="btn btn-dark" data-dismiss="modal">Đóng</button>
        </div>

      </div>
    </div>
  </div>
</div>
<script>

  var msg = '${message}';
  var span  = document.getElementById("form-message");
  console.log(span);
  if('${message}'!=''){
    span.parentElement.classList.add("alert-success");
    span.innerText = msg;
  }


  <%--const deletebtn = document.querySelectorAll('.delete-btn');--%>
  <%--Array.prototype.slice.call(deletebtn).forEach((btn)=>{--%>
  <%--  btn.onclick = () => {--%>
  <%--    const id = btn.dataset.id;--%>
  <%--    const tr = btn.parentElement;--%>
  <%--    const name =tr.firstChild.nextSibling.textContent;--%>

  <%--    ${document}.ready(function (){--%>
  <%--      $('myModal').on('show.modal', function (event){--%>
  <%--        // var button = $(event.relatedTarget);--%>
  <%--        // var body = button.data('body');--%>
  <%--        // const tr = document.querySelector('')--%>
  <%--        $(this).find('.modal-body').text(name);--%>
  <%--      })--%>
  <%--    })--%>

  <%--  }--%>
  <%--})--%>
  <%--const confirmDeletebtn = document.querySelector('#confirmDeletebtn');--%>
  <%--confirmDeletebtn.setAttribute('href','http://localhost:8080/lab5_war_exploded/home.jsp?action=delete&id=id');--%>
  <%--confirmDeletebtn.onclick = () => {--%>
  <%--  &lt;%&ndash;href = `?action=delete&id=${id}`;&ndash;%&gt;--%>
  <%--  &lt;%&ndash;const td = document.querySelector(`#${id}`);&ndash;%&gt;--%>
  <%--  const td = document.querySelector('td[data-id=${id}');--%>
  <%--  const tr = td.parentElement;--%>
  <%--  tr.remove();--%>
  <%--}--%>

  function Delete(id){

    const deletebtn = document.getElementById(id);
    console.log(deletebtn);
    const tr = deletebtn.parentElement.parentElement;
    console.log(tr);
    tr.remove();
  }
  function handleCreateFrom(){
      var table = document.querySelector('table');
      var html = `<tr>
            <td>
              <c:out value="${product.id }"/>
            </td>
            <td>
              <c:out value="${product.name}"/>
            </td>
            <td>
              <c:out value="${product.price}"/>
            </td>
            <td>
              <a data-toggle="modal" data-target="#myModal" data-id="${product.id}" href="?action=delete&id=${product.id}">
                Xóa
              </a>
            </td>
          </tr>`;
      table.innerHTML = html.join('');
  }
  // function handleDeleteButton(id){
  //   var tr = document.querySelector('td[innerText=id]').parentElement;
  //   tr.remove();
  // }

</script>
</body>
</html>
