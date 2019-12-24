layui.use(['layer', 'form', 'admin', 'laydate', 'ax','upload'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer;
    var upload = layui.upload;


    var wordId = $("#wordId").val();


    /**
     * 回显
     */
    var ajax = new $ax(Feng.ctxPath + "/word/detail?wordId="+wordId,function (data) {
        $('#wordName').val(data.wordName);
        $('#wordSort').val(data.wordSort);
        $('#wordChance').val(data.wordChance);
        $('#wordName').val(data.wordName);
        $('#wordUrl').val(data.wordUrl);
        $('#demo').attr('src', data.wordUrl);
    });
    ajax.start();

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



    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/word/editWordDeploy", function (data) {
            Feng.success("修改成功");
            admin.putTempData('formOk', true);
            admin.closeThisDialog()

        });
        data.field.wordId=wordId;
        ajax.set(data.field);
        ajax.start();
    });


    form.render();
    //让当前iframe弹层高度适应
    admin.iframeAuto();
});