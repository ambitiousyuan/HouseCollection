layui.use([ 'upload','admin', 'form', 'ax'], function(){
    var upload = layui.upload,
        $ = layui.jquery;
    var admin = layui.admin;
    var form = layui.form;
    var $ax = layui.ax;

    var uploadInst = upload.render({
        elem: '#upload'
        , url: '/word/upload'
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


    admin.iframeAuto();



    //添加项目 保存
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/word/save", function (data) {
           /* if(data.code == 401){
                Feng.error(data.message);
            }
            if(data.code==200){
                Feng.success("添加成功！");
            }*/
            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);
            //关掉对话框
            admin.closeThisDialog();
        });
        ajax.set(data.field);
        ajax.start();
    });


    form.render();
});