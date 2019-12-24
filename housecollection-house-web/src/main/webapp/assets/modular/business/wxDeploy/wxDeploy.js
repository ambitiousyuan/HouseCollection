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

    var uploadInst = upload.render({
        elem: '#upload'
        , url: '/deploy/upload'
        , before: function (obj) {
            obj.preview(function (index, file, result) {
                $('#demo').attr('src', result);
            });
        }
        , done: function (res) {
            if (res.code > 0) {
                return layer.msg('上传失败');
            }
            document.getElementById("img_url").value = res.url;

            /*var imgUrl =getImgUrl(res.url);
            $('#demoText').text(imgUrl);*/

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
        var ajax = new $ax(Feng.ctxPath + "/deploy/save", function (data) {

        });
        ajax.set(data.field);
        ajax.start();

    });
    //配置回显
    initData();
    function initData(params) {
        var ajax = new $ax(Feng.ctxPath + "/deploy/backShow", function (data) {
            if (data.code == 200) {
                $('#appId').val(data.data.appId);
                $('#appSecret').val(data.data.appSecret);
                $('#title').val(data.data.title);
                $('#wxDescribe').val(data.data.wxDescribe);
                $('#img_url').val(data.data.picUrl);
                $('#demo').attr('src', data.data.picUrl);

            } else {
                Feng.error(data.message);
            }

        });
        ajax.start();
    }
    var password  = '888888';
    $("#clearData").click(function () {
        layer.prompt({ title: '请输入密码', formType: 1 }, function (text, index) {
            if (text == password) {
                var deselectAjax = new $ax(Feng.ctxPath + "/activeConfig/removeAll", function (res) {
                    if (res.code = 200) {
                        Feng.success("重置成功");
                    } else {
                        Feng.error("重置失败")
                    }

                });
                deselectAjax.start();

            } else {
                Feng.error("密码错误,请重新输入");
                return;
            }
            layer.closeAll();
        });
    });








});

