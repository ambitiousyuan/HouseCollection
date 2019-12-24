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
    var userTable = {
        tableId: "userTable",
        condition: {
            name: ""
        }
    };

    /**
     * 初始化表格的列
     */
    userTable.initColumn = function () {
        return [[
            { field: 'userId', hide: true, title: 'id' },
            { type: 'numbers', title: '排名', width: 100, fixed: 'left' },
            { field: 'pic_url', title: '微信头像', align: "center", sort: false,  templet: function (d) { return '<div><img width="28px" src=' + d.pic_url + '></div>' }},
            { field: 'nick_name', title: '用户昵称', align: 'center', valign: 'middle' },
            { field: 'name', title: '用户名称', align: 'center', valign: 'middle' },
            { field: 'phone', title: '用户手机', align: 'center', valign: 'middle' },
            { field: 'awardSum', title: '集字总数', align: 'center', valign: 'middle' },
            { field: 'rate0', title: '集字1总数', align: 'center', valign: 'middle' },
            { field: 'rate1', title: '集字2总数', align: 'center', valign: 'middle' },
            { field: 'rate2', title: '集字3总数', align: 'center', valign: 'middle' },
            { field: 'rate3', title: '集字4总数', align: 'center', valign: 'middle' },
            { field: 'rate4', title: '集字5总数', align: 'center', valign: 'middle' },
            { field: 'rate5', title: '集字6总数', align: 'center', valign: 'middle' },
            { field: 'rate6', title: '集字7总数', align: 'center', valign: 'middle' },
            { field: 'rate7', title: '集字8总数', align: 'center', valign: 'middle' }
        ]];
    };

    //方法级渲染
    table.render({
        elem: '#' + userTable.tableId,
        id: userTable.tableId,
        url: Feng.ctxPath + '/result/list',
        cols: userTable.initColumn(),
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
        temp_form.action =  "/result/export";
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
        userTable.search();
    });

    userTable.search = function () {
        var queryData = {};
        queryData['condition'] = $("#nick_name").val();
        table.reload(userTable.tableId, {
            where: queryData,
            page: {
                curr: 1
            }
        });
    };
    element.init();
    form.render();
});
