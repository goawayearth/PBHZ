document.addEventListener("DOMContentLoaded", function () {
    // 页面加载完成之后

    // 获取用户名输入框
    var usernameInput = document.getElementById("username");
    // 注册失去焦点事件
    usernameInput.addEventListener("input", function () {
        // 获取用户名
        var username = this.value;
        // 获取显示错误信息的元素
        var errorMsgSpan = document.getElementById("error");
        console.log(typeof (username));
        if(username == null || username == ""){
            errorMsgSpan.innerText="用户名不能为空！";
        }
        else{
            // 创建XMLHttpRequest对象
            var xhr = new XMLHttpRequest();
            // 设置请求方法和URL
            xhr.open("GET", "http://localhost:8080/Pbhz/checkUsername?action=ajaxExistsUsername&username=" + username, true);
            // 注册回调函数
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    // 解析JSON数据
                    var data = JSON.parse(xhr.responseText);



                    if (data.existsUsername) {
                        errorMsgSpan.innerText = "用户名已存在！";
                    } else {
                        errorMsgSpan.innerText = "用户名可用！";
                    }
                }
            }};
        // 发送请求
        xhr.send();
    });

    // 获取密码输入框
    var passwordInput = document.getElementById("password");

    // 注册输入事件
    passwordInput.addEventListener("input", function () {
        // 获取密码
        var password = this.value;

        // 创建正则表达式对象，用于检查密码格式
        var passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{5,12}$/;
        console.log("11")

        // 检查密码是否符合格式
        if (!passwordPattern.test(password)) {
            console.log("22")
            // 密码不符合格式，显示错误信息
            document.getElementById("error").innerText = "密码必须包含数字和字母，并且长度为5到12位。";
        } else {
            console.log("33")
            // 密码符合格式，清空错误信息
            document.getElementById("error").innerText = "";
        }
    });

});


