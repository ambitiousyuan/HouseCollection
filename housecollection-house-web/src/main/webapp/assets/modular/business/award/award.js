layui.use([ 'table','admin','ax' ,'layer'], function() {
    var $ax = layui.ax;
    var table = layui.table;
    $ = layui.jquery;
    var admin = layui.admin;
    var layer = layui.layer;

    var awardTable = {
        tableId : "awardTable"
    };

    awardTable.initColumn = function() {
        return [ [ {field : 'awardId', hide : true,  title : 'id'},
            {field : 'awardLevel',  title : '奖品等级',align: 'center', valign: 'middle'},
            {field : 'awardName',  title : '奖品名称',align: 'center', valign: 'middle'},
            {field : 'awardRequire',  title : '获奖规则',align: 'center', valign: 'middle'},
            {field : 'awardNum',  title : '奖品数量',align: 'center', valign: 'middle'},
            /*{field : 'createTime',  title : '创建时间',align: 'center', valign: 'middle'},*/
            {field: 'awardUrl', title: '中奖显示图',align: 'center', valign: 'middle',templet:'#imgTpl'},
            {toolbar: '#tableBar', title: '操作', minWidth: 280,align: 'center', valign: 'middle'}
        ] ];
    };


    table.render({
        elem : '#' + awardTable.tableId,
        id : awardTable.tableId,
        url : Feng.ctxPath + '/award/list',
        cols : awardTable.initColumn(),
        even : true,
        page : true,
        height : "full-158",
        cellMinWidth : 100
    });

    $('#addAward').click(function () {
        awardTable.addAward();
    });


    awardTable.addAward=function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加奖品',
            content: Feng.ctxPath + '/award/addAwardPage',
            end: function () {
                admin.getTempData('formOk') && table.reload(awardTable.tableId);
            }
        });
    };


    table.on('tool(' + awardTable.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'updateAward') {
            awardTable.updateAward(data);
        }
        if(layEvent==='delete'){
            layer.confirm('你确定要删除吗?',
                { title: false, btn: ['确定','取消']  },
                function(index) {
                    awardTable.delete(data);
                    layer.close(index);
                });
        }

    });

    awardTable.updateAward=function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改奖项设置',
            area: ['390px', '400px'],
            content: Feng.ctxPath + '/award/updateAwardPage?awardId='+data.awardId,
            end: function () {
                admin.getTempData('formOk') && table.reload(awardTable.tableId);
            }
        });

    };

    awardTable.delete=function(data){
        var ajax = new $ax(Feng.ctxPath + "/award/delete", function (data) {
            if(data.code==200){
                Feng.success("删除成功！")
                table.reload(awardTable.tableId)
            }
        });
        ajax.set("awardId",data.awardId);
        ajax.start();
    };










});