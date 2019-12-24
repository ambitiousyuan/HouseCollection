layui.use([ 'table','admin','ax' ,'layer'], function() {
        var $ax = layui.ax;
        var table = layui.table;
        $ = layui.jquery;
        var admin = layui.admin;
        var layer = layui.layer;
        var wordDeployTable = {
            tableId : "wordDeployTable"
        };


        wordDeployTable.initColumn = function() {
            return [ [ {field : 'wordId', hide : true,  title : 'id'},
                {field : 'wordSort',  title : '福字序号',align: 'center', valign: 'middle'},
                {field : 'wordName',  title : '福字名称',align: 'center', valign: 'middle'},
                {field : 'wordChance',  title : '出现概率',align: 'center', valign: 'middle'},
                /*{field : 'createTime',  title : '创建时间',align: 'center', valign: 'middle'},*/
                {field: 'wordUrl', title: '福字图片',align: 'center', valign: 'middle',templet:'#imgTpl'},
                {toolbar: '#tableBar', title: '操作', minWidth: 280,align: 'center', valign: 'middle'}
            ] ];
        };

        table.render({
            elem : '#' + wordDeployTable.tableId,
            id : wordDeployTable.tableId,
            url : Feng.ctxPath + '/word/list',
            cols : wordDeployTable.initColumn(),
            even : true,
            page : true,//是否显示分页
            height : "full-158",
            cellMinWidth : 100
        });


        $('#addWordDeploy').click(function () {
            wordDeployTable.addWordDeployPage();
        });


        wordDeployTable.addWordDeployPage=function (date) {
            admin.putTempData('formOk', false);
            top.layui.admin.open({
                type : 2,
                title : '新增集字',
                content : Feng.ctxPath + '/word/skipAddWordDeployPage',
                end : function() {
                    admin.getTempData('formOk')
                    && table.reload(wordDeployTable.tableId);
                }
            });
        };

        table.on('tool(' + wordDeployTable.tableId + ')', function (obj) {
            var data = obj.data;
            var layEvent = obj.event;
            if (layEvent === 'updateWordDeploy') {
                wordDeployTable.updateWordDeploy(data);
            }
            if(layEvent==='delete'){
                layer.confirm('你确定要删除吗?',
                    { title: false, btn: ['确定','取消']  },
                    function(index) {
                        wordDeployTable.delete(data);
                        layer.close(index);
                    });
            }
           
        });

        wordDeployTable.updateWordDeploy=function (data) {
            admin.putTempData('formOk', false);
            top.layui.admin.open({
                type: 2,
                title: '修改福字',
                area: ['390px', '400px'],
                content: Feng.ctxPath + '/word/editPage?wordId='+data.wordId,
                end: function () {
                    admin.getTempData('formOk') && table.reload(wordDeployTable.tableId);
                }
            });
            
        };

        wordDeployTable.delete=function(data){
            var ajax = new $ax(Feng.ctxPath + "/word/delete", function (data) {
                if(data.code==200){
                    Feng.success("删除成功！")
                    table.reload(wordDeployTable.tableId)
                }
            });
            ajax.set("wordId",data.wordId);
            ajax.start();
        };







    });