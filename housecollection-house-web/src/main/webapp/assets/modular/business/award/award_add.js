layui.use([ 'table','admin','ax','upload','form' ], function() {
    var $ax = layui.ax;
    var table = layui.table;
    $ = layui.jquery;
    var admin = layui.admin;
    var upload = layui.upload;
    var form = layui.form;


    admin.iframeAuto();


    var uploadInst = upload.render({
        elem: '#upload'
        , url: '/award/upload'
        , before: function (obj) {
            obj.preview(function (index, file, result) {
                $('#demo').attr('src', result);
            });
        }
        , done: function (res) {
            if (res.code > 0) {
                return layer.msg('上传失败');
            }
            document.getElementById("wordUrl").value = res.url;

        }
        , error: function () {
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function () {
                uploadInst.upload();
            });
        }
    });


    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/award/save", function (data) {
            Feng.success("添加成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
    });

});
