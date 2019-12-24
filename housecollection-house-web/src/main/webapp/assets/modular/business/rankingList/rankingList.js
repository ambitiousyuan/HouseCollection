layui.use(['table', 'element', 'ax', 'layedit', 'laydate', 'form', 'admin'],
function () {
    var layer = layui.layer;
    var table = layui.table;
    var $ax = layui.ax;
    var laydate = layui.laydate;
    var layedit = layui.layedit;
    var form = layui.form;
    var element = layui.element;
    $ = layui.jquery;
    var admin = layui.admin;

    /**
     * 用户列表
     */
    var rankingList = {
        tableId: "rankingList",
        condition: {
            name: ""
        }
    };

    /**
     * 初始化表格的列
     */
    rankingList.initColumn = function () {
        return [[
            { field: 'userId', hide: true, title: 'id' },
            { type: 'numbers', title: '序号', width: 100, fixed: 'left' },
            { field: 'num', title: '集字套数', align: 'center', valign: 'middle',sort:true },    
            { field: 'picUrl', title: '微信头像', align: "center", sort: false, templet: function (d) { return '<div><img width="28px" src=' + d.picUrl + '></div>' } },
            { field: 'nickName', title: '用户昵称', align: 'center', valign: 'middle' },
            { field: 'name', title: '用户名称', align: 'center', valign: 'middle' },
            { field: 'phone', title: '用户手机', align: 'center', valign: 'middle' }
        ]];
    };

    //方法级渲染
    table.render({
        elem: '#' + rankingList.tableId,
        id: rankingList.tableId,
        url: Feng.ctxPath + '/result/taoShu',
        cols: rankingList.initColumn(),
        even: true,
        page: true,//是否显示分页
        height: "full-100",//高度最大化减去差值
        cellMinWidth: 100,
        limit: 50,
        page: {
            curr: 1 //重新从第 1 页开始
        }
    });


    //excel导出
    $("#export").click(function () {
        var param = null;
        var temp_form = document.createElement("form");
        temp_form.action =  "/activity/exportResult";
        temp_form.method = "post";
        document.body.appendChild(temp_form);
        var text = '';
        var input = document.createElement("input");
        // 设置相应参数
        input.type = "text";
        input.name = "export";
        // 将该输入框插入到 form 中
        temp_form.appendChild(input);
        //
        temp_form.submit();
        document.body.removeChild(temp_form);
    });


    /**
     * 点击搜索
     */
    /**
     * 点击查询按钮
     */
    $("#searchAgent").click(function () {
        rankingList.search();
    });
    // 清空用户
    $("#delete").click(function () {
        rankingList.delete();
    });

    rankingList.delete=function(data){
        var ajax = new $ax(Feng.ctxPath + "/user/delete", function (data) {
            if(data.code==200){
                Feng.success("删除成功！")
                table.reload(rankingList.tableId)
            }
        });
        ajax.start();
    };
    rankingList.search = function () {
        var queryData = {};
        queryData['condition'] = $("#name").val();
        table.reload(rankingList.tableId, {
            where: queryData,
            page: {
                curr: 1
            }
        });
    };
    element.init();
    form.render();
});
