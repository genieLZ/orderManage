<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--<jsp:forward page="/cmdts"></jsp:forward>--%>
<html>
<head>
    <title>商品列表</title>
    <%
        pageContext.setAttribute("APP_PATH",request.getContextPath());
    %>
    <%--引入jquery--%>
    <script type="text/javascript" src="${APP_PATH}/js/jquery-3.2.1.min.js"></script>
    <%--引入样式--%>
    <link href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
<!-- 商品添加 -->
<div class="modal fade" id="cmdtAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">商品添加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="cmdtName_add" class="col-sm-2 control-label">商品名称</label>
                        <div class="col-sm-10">
                            <input type="text" name="cmdtName" class="form-control" id="cmdtName_add" placeholder="伊利谷粒多燕麦牛奶">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="cmdtPrice_add" class="col-sm-2 control-label">价格</label>
                        <div class="col-sm-10">
                            <input type="text" name="cmdtPrice" class="form-control" id="cmdtPrice_add" placeholder="3">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="col-sm-2 control-label">商品类型</label>
                        <div class="col-sm-4">
                            <select class="form-control" name="categoryId" id="category_add_select">
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="cmdt_save_btn">保存</button>
            </div>
        </div>
    </div>
</div>

<%--商品修改模态框--%>
<div class="modal fade" id="cmdtUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >商品修改 </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="cmdtName_Update_static" class="col-sm-2 control-label">商品名称</label>
                        <div class="col-sm-10">
                            <p class="form-control-static" id="cmdtName_Update_static"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="cmdtPrice_Update" class="col-sm-2 control-label">价格</label>
                        <div class="col-sm-10">
                            <input type="text" name="cmdtPrice" class="form-control" id="cmdtPrice_Update" placeholder="3">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="col-sm-2 control-label">商品类型</label>
                        <div class="col-sm-4">
                            <select class="form-control" name="categoryId" id="category_Update_select">
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="cmdt_Update_btn">更新</button>
            </div>
        </div>
    </div>
</div>

<div class="container"/>
<div class="row">
    <div class="col-md-12">
        <%--标题--%>
        <h1>修罗商城</h1>
    </div>
    <%--按钮--%>
    <div class="row">
        <div class="col-md-4 col-md-offset-8">
            <button class="btn btn-primary" id="cmdt_add_modal_btn">新增</button>
            <button class="btn btn-danger" id="cmdt_delete_all_btn">删除</button>
        </div>
    </div>
    <%--表格数据--%>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover" id="cmdts_table">
                <thead>
                    <tr>
                        <th>
                            <input type="checkbox" id="check_all"/>
                        </th>
                        <th>#</th>
                        <th>cmdtName</th>
                        <th>cmdtPrice</th>
                        <th>categoryName</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <%--分页信息--%>
        <div class="col-md-6" id="page_info_area"></div>
        <%--分页条--%>
        <div class="col-md-6" id="page_nav_area"></div>
    </div>
    <script type="text/javascript">
        //全局变量总记录数
        var totalRecord,page;
        //页面加载后，直接发送ajax请求，得到分页数据
        $(function () {
            //去首页
            to_page(1);
        })
        function to_page(pn){
            $.ajax({
            url:"${APP_PATH}/cmdts",
            data:"pn="+pn,
            type:"GET",
            success:function(result){
                //console.log(result);
                //解析并显示员工数据
                build_cmdts_table(result);
                //解析并显示分页信息
                build_page_info(result);
                //解析并显示分页数据
                build_page_nav(result);

            }
        });
        }
        //显示商品数据
        function build_cmdts_table(result){
            //cmdts_table tbody
            //清空表
            $("#cmdts_table tbody").empty();
            var cmdts = result.extend.pageInfo.list;
            $.each(cmdts,function (index,item) {
                var checkBoxTd = $("<td><input type='checkbox' class='check_item'/></td>");
                var cmdtIdTd = $("<td></td>").append(item.cmdtId);
                var cmdtNameTd = $("<td></td>").append(item.cmdtName);
                var cmdtPriceTd = $("<td></td>").append(item.cmdtPrice);
                var categoryNameTd = $("<td></td>").append(item.category.categoryName);
                var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
                    .append("<span/>").addClass("glyphicon glyphicon-pencil ").append("编辑 ");
                //给button添加一个自定义属性ID
                editBtn.attr("edit-id",item.cmdtId);
                var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm del_btn")
                    .append("<span/>").addClass("glyphicon glyphicon-trash").append("删除 ");
                delBtn.attr("del-id",item.cmdtId);
                var btnTd = $("<td></td>").append(editBtn).append("  ").append(delBtn);
                //append方法执行完成后还是返回原来的元素
                $("<tr></tr>").append(checkBoxTd)
                    .append(cmdtIdTd)
                    .append(cmdtNameTd)
                    .append(cmdtPriceTd)
                    .append(categoryNameTd)
                    .append(btnTd)
                    .appendTo("#cmdts_table tbody");
            })
        }
        //分页信息
        function build_page_info(result){
            //page_info_area
            $("#page_info_area").empty();
            $("#page_info_area").append("当前页数："+result.extend.pageInfo.pageNum
                +"页，总共"+result.extend.pageInfo.pages
                +"页，总共"+result.extend.pageInfo.total+"条记录")
            totalRecord = result.extend.pageInfo.total;
            page = result.extend.pageInfo.pageNum;
        }
        //添加分页栏
        function build_page_nav(result) {
            //page_nav_area
            $("#page_nav_area").empty();
            var ul = $("<ul></ul>").addClass("pagination");
            var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
            var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
            if(result.extend.pageInfo.hasPreviousPage==false){
                firstPageLi.addClass("disabled");
                prePageLi.addClass("disabled");
            }else{
                firstPageLi.click(function () {
                    to_page(1);
                });
                prePageLi.click(function () {
                    to_page(result.extend.pageInfo.pageNum-1);
                });
            }
            var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
            var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href","#"));
            if(result.extend.pageInfo.hasNextPage==false){
                nextPageLi.addClass("disabled");
                lastPageLi.addClass("disabled");
            }else{
                nextPageLi.click(function () {
                    to_page(result.extend.pageInfo.pageNum+1);
                });
                lastPageLi.click(function () {
                    to_page(result.extend.pageInfo.pages);
                });
            }
            //添加首页和前一页
            ul.append(firstPageLi).append(prePageLi);
            $.each(result.extend.pageInfo.navigatepageNums,function (index,item) {
                var numLi = $("<li></li>").append($("<a></a>").append(item));
                if(result.extend.pageInfo.pageNum==item){
                    numLi.addClass("active");
                }
                numLi.click(function () {
                    to_page(item);
                })
                //添加页码
                ul.append(numLi);
            })
            //添加下一页和末页
            ul.append(nextPageLi).append(lastPageLi);
            //添加进nav标签
            var navEle = $("<nav></nav>").append(ul);
            navEle.appendTo("#page_nav_area ");
//            $("#page_nav_area").append(navEle);
        }

        //清空模态框
        function reset_form(ele) {
            $(ele)[0].reset();
            $(ele).find("*").removeClass("has-success has-error");
            $(ele).find(".help-block").text("");
        }

        //弹出添加模态框
        $("#cmdt_add_modal_btn").click(function () {
            //清空模态框
            reset_form("#cmdtAddModal form");
            getCategorys("#category_add_select");
            $("#cmdtAddModal").modal({
                backdrop:"static"
            })
        })
        //查询商品种类
        function getCategorys(ele) {
            $(ele).empty();
            $.ajax({
                url:"${APP_PATH}/categorys",
                type:"GET",
                success:function (result) {
//                    console.log(result);
                    $.each(result.extend.category,function () {
                        var optionEle = $("<option></option>").append(this.categoryName).attr("value",this.categoryId);
                        optionEle.appendTo(ele);
                    })
                }
            });
        }

        //校验数据
        function validate_add_form() {
            //使用正则表达式
            var cmdtName = $("#cmdtName_add").val();
            var regName = /[\u4e00-\u9fa5_a-zA-Z0-9_]{2,20}/;
            if(!regName.test(cmdtName)){
//                alert("商品名称应该是2到20位中文和英文数字！")
                show_validate_msg("#cmdtName_add","error","商品名称应该是2到20位中文和英文数字！")
                return false;
            }else{
                show_validate_msg("#cmdtName_add","success","")
            }
            var cmdtPrice = $("#cmdtPrice_add").val();
            var regPrice = /^[0-9]{1,16}$/;
            if(!regPrice.test(cmdtPrice)){
//                alert("商品价格区间应该是1到1000000000！")
                show_validate_msg("#cmdtPrice_add","error","商品价格区间应该是1到1000000000！")
                return false;
            }else{
                show_validate_msg("#cmdtPrice_add","success","")
            }
            return true;
        }
        //显示错误提示类型
        function  show_validate_msg(ele,status,msg) {
            //移除类型
            $(ele).parent().removeClass("has-success has-error");
            $(ele).next().text("");
            if("success"==status){
                $(ele).parent().addClass("has-success");
                $(ele).next().text(msg);
            }else if("error"==status){
                $(ele).parent().addClass("has-error");
                $(ele).next().text(msg);
            }
        }
        
        //校验是否重名
        $("#cmdtName_add").change(function () {
            //发送ajax请求校验商品是否重名
            var cmdtName = this.value;
            $.ajax({
                url:"${APP_PATH}/checkCmdt",
                data:"cmdtName="+cmdtName,
                type:"GET",
                success:function (result) {
                    if(result.code==100){
                        show_validate_msg("#cmdtName_add","success","");
                        $("#cmdt_save_btn").attr("ajax-va","success");
                    }else if(result.code==200){
                        show_validate_msg("#cmdtName_add","error",result.extend.vs_msg);
                        $("#cmdt_save_btn").attr("ajax-va","error");
                    }
                }
            })

        })

        //保存添加信息
        $("#cmdt_save_btn").click(function () {
            //先进行校验
            if(!validate_add_form()){
                return false;
            }
            if($(this).attr("ajax-va")=="error"){
                return false;
            }
            //填写模态框进行保存
            $.ajax({
                url:"${APP_PATH}/cmdt",
                type:"POST",
                data:$("#cmdtAddModal form").serialize(),
                success:function (result) {
                    if(result.code==100){
                        $("#cmdtAddModal").modal('hide');
                        to_page(totalRecord);
                        //关闭模态框并跳到最后一页
                    }else{
                        alert(result.msg);
                        show_validate_msg("#cmdtName_add","error",result.extend.errorFields.getDefaultMessage());
//                        $("#cmdt_save_btn").attr("ajax-va","error");
                    }
                }
            });
        })


        //编辑按钮
        //在按键创建之前无法绑定click
        //创建按键的时候绑定使用live，但新版本jQuery使用on替代了
        $(document).on("click",".edit_btn",function () {
            //清空模态框
            reset_form("#cmdtUpdateModal form");
            getCategorys("#cmdtUpdateModal select");
            getCmdt($(this).attr("edit-id"));
            $("#cmdt_Update_btn").attr("edit-id",$(this).attr("edit-id"));
            $("#cmdtUpdateModal").modal({
                backdrop:"static"
            })
        })
        function getCmdt(id) {
            $.ajax({
                url:"${APP_PATH}/Cmdt/"+id,
                type:"GET",
                success:function (result) {
                    $("#cmdtName_Update_static").text(result.extend.cmdt.cmdtName);
                    $("#cmdtPrice_Update").val(result.extend.cmdt.cmdtPrice);
                    $("#category_Update_select").val([result.extend.cmdt.categoryId]);
                }
            })
        }
        //保存更新
        $("#cmdt_Update_btn").click(function () {
            //验证表单信息
            var cmdtPrice = $("#cmdtPrice_Update").val();
            var regPrice = /^[0-9]{1,16}$/;
            if(!regPrice.test(cmdtPrice)){
                show_validate_msg("#cmdtPrice_Update","error","商品价格区间应该是1到1000000000！")
                return false;
            }else{
                show_validate_msg("#cmdtPrice_Update","success","")
            }
            $.ajax({
                url:"${APP_PATH}/Cmdt/"+$(this).attr("edit-id"),
                type:"PUT",
                data:$("#cmdtUpdateModal form").serialize(),
                success:function (result) {
                    $("#cmdtUpdateModal").modal("hide");
                    to_page(page);
                }
            })
        })

        //单一删除按钮
        $(document).on("click",".del_btn",function () {
            //弹出是否删除提示框
            var cmdtName = $(this).parents("tr").find("td:eq(2)").text();
            var cmdtId = $(this).attr("del-id");
//            alert($(this).parents("tr").find("td:eq(1)").text());
            if(confirm("确认删除【"+cmdtName+"】吗？")){
                $.ajax({
                    url:"${APP_PATH}/Cmdt/"+cmdtId,
                    type:"DELETE",
                    success:function (result) {
                        alert(result.msg);
                        to_page(page);
                    }
                })
            }
        })

        $("#check_all").click(function () {
            //attr多为自定义属性，原生DOM使用prop可获取DOM原生属性
//            $(this).prop("checked");
            $(".check_item").prop("checked",$(this).prop("checked"));
        })
        //判断是否全部选中
        $(document).on("click",".check_item",function () {
            var flag = $(".check_item:checked").length==$(".check_item").length;
            $("#check_all").prop("checked",flag);
        })

        //点击全部删除
        $("#cmdt_delete_all_btn").click(function () {
            //累加
            var cmdtNames = "";
            var cmdtIds = "";
            $.each($(".check_item:checked"), function () {
                cmdtNames += $(this).parents("tr").find("td:eq(2)").text()+"，";
                cmdtIds += $(this).parents("tr").find("td:eq(1)").text()+"-";
            })
            cmdtNames = cmdtNames.substring(0,cmdtNames.length-1);
            cmdtIds = cmdtIds.substring(0,cmdtNames.length-1);
            if(confirm("确定删除【"+cmdtNames+"】吗？")){
                $.ajax({
                    url:"${APP_PATH}/Cmdt/"+cmdtIds,
                    type:"DELETE",
                    success:function (result) {
                        alert(result.msg);
                        to_page(page);
                    }
                })
            }
        });
    </script>
</div>
</body>
</html>