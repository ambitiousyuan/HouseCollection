layui.use(['layer', 'form', 'admin', 'laydate', 'ax','upload'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer;
    var upload = layui.upload;


    var awardId = $("#awardId").val();

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
            document.getElementById("awardUrl").value = res.url;

        }
        , error: function () {
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function () {
                uploadInst.upload();
            });
        }
    });

    /**
     * 回显
     */
    var ajax = new $ax(Feng.ctxPath + "/award/detail?awardId="+awardId,function (data) {
        $('#awardLevel').val(data.awardLevel);
        $('#awardName').val(data.awardName);
        $('#awardRequire').val(data.awardRequire);
        $('#awardNum').val(data.awardNum);
        $('#wordUrl').val(data.awardUrl);
        $('#demo').attr('src', data.awardUrl);
    });
    ajax.start();




    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/award/updateAward", function (data) {
            Feng.success("修改成功");
            admin.putTempData('formOk', true);
            admin.closeThisDialog()

        });
        data.field.awardId=awardId;
        ajax.set(data.field);
        ajax.start();
    });


    form.render();
    //让当前iframe弹层高度适应
    admin.iframeAuto();
});