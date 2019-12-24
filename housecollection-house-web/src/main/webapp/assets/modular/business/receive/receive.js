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


        var receiveTable = {
            tableId: "receiveTable",
            condition: {
                name: ""
            }
        };

        /**
         * 初始化表格的列
         */
        receiveTable.initColumn = function () {
            return [[
                { field: 'id', hide: true, title: 'id' },
                { field: 'nick_name', title: '用户昵称', align: 'center', valign: 'middle' },
                { field: 'name', title: '姓名', align: 'center', valign: 'middle' },
                { field: 'phone', title: '手机号', align: 'center', valign: 'middle' },
                { field: 'award_level', title: '奖项', align: 'center', valign: 'middle' },
                { field: 'mark', title: '是否兑奖', align: 'center', valign: 'middle', templet: "#filterA" },
                { fixed: 'right', title: '操作栏', align: 'center', width: 150, valign: 'middle', toolbar: '#toolbarDemo' }
            ]];
        };


        table.render({
            elem: '#' + receiveTable.tableId,
            id: receiveTable.tableId,
            url: Feng.ctxPath + '/receive/list',
            cols: receiveTable.initColumn(),
            even: true,
            page: true,
            height: "full-100",
            cellMinWidth: 100,
            limit: 50,
            page: {
                curr: 1
            }
        });

        //excel导出
        $("#export").click(function () {
            var param = null;
            var temp_form = document.createElement("form");
            temp_form.action = "/receive/export";
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
        //监听行工具事件
        table.on('tool(receiveTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'duijiang') {
                receiveTable.duijiang(data);
            } 
        });
        var password ='888888';
         //结果管理 取消
         receiveTable.duijiang = function (data) {
            layui.use('layer',function(){
                var layer = layui.layer;
                  layer.confirm('你确定核销么？', {
                  btn: ['确定', '取消'],
                  yes: function(index){
                      $.ajax({
                          url:Feng.ctxPath + "/receive/mark",
                          data:data,
                          type:"Post",
                          dataType:"json",
                          success:function(data){
                              layer.msg(data.message);
                              location.reload(); //删除成功后再刷新
                          },
                          error:function(data){
                              $.messager.alert('错误',data.message);
                          }
                      });
                      layer.close(index);
                  }
              });
            });
        };


        /**
         * 点击搜索
         */
        /**
         * 点击查询按钮
         */
        $("#searchReceive").click(function () {
            receiveTable.search();
        });

        receiveTable.search = function () {
            var queryData = {};
            queryData['condition'] = $("#name").val();
            table.reload(receiveTable.tableId, {
                where: queryData,
                page: {
                    curr: 1
                }
            });
        };
        element.init();
        form.render();
    });
