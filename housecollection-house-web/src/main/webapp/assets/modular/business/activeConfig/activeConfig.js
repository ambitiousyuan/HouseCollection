layui.use(['table', 'element', 'ax', 'layedit', 'laydate', 'form', 'admin', 'upload', 'laydate'], function () {
    var $ax = layui.ax;
    var laydate = layui.laydate;
    var layedit = layui.layedit;
    var form = layui.form;
    var element = layui.element;
    $ = layui.jquery;
    var admin = layui.admin;
    var upload = layui.upload;
    var nowTime = new Date().valueOf();

    admin.iframeAuto();
    var start = laydate.render({ //开始
        elem: '#openTime',
        min:nowTime,
        format: 'yyyy-MM-dd HH:mm:ss',
        type: 'datetime',
        done:function(value,date){
            endMax = end.config.max;
            end.config.min = date;
            end.config.min.month = date.month -1;
        }
    });
    var end = laydate.render({ //结束
        elem: '#closeTime',
        min : nowTime,
        format: 'yyyy-MM-dd HH:mm:ss',
        type: 'datetime',
        done:function(value,date){
            if($.trim(value) == ''){
                var curDate = new Date();
                date = {'date': curDate.getDate(), 'month': curDate.getMonth()+1, 'year': curDate.getFullYear()};
            }
            start.config.max = date;
            start.config.max.month = date.month -1;
        }
    });


    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/activeConfig/add", function (data) {

        });
        ajax.set(data.field);
        ajax.start();

    });


    //配置回显
    var ajax = new $ax(Feng.ctxPath + "/activeConfig/backShow", function (res) {
        $('#activeName').val(res.data.activeName);
        $('#closeTime').val(res.data.closeTime);
        $('#gameNumbers').val(res.data.gameNumbers);
        $('#helpNum').val(res.data.helpNum);
        $('#masterNum').val(res.data.masterNum);
        $('#openTime').val(res.data.openTime);
        $('#prizeNums').val(res.data.prizeNums);
        $('#writeOffCode').val(res.data.writeOffCode);
    });
    ajax.start();





});

