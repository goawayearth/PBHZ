window.onload = function(){

    // 返回值是true才会提交表单
    document.getElementById("Button").onclick = function (){

            var username = document.getElementById("username").value;
            var password = document.getElementById("password").value;
            var vali = document.getElementById("login-validate").value;
            var xhr = new XMLHttpRequest();
            xhr.open("GET","http://localhost:8080/Pbhz/loginServlet?username="+username+"&password="+password+"&validate="+vali);

            xhr.onreadystatechange  = function() {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    var data = JSON.parse(xhr.responseText);
                    console.log(data)
                    if (data.fin === "ok") {
                        document.getElementById("error").innerText = "账号或密码错误";
                        document.getElementById("username").value = "";
                        document.getElementById("password").value = "";
                        document.getElementById('login-validate').value = '';
                        changeImg();
                    } else if (data.fin === "ok1") {
                        document.getElementById("error").innerText = "验证码错误";
                        // document.getElementById("username").value = "";
                        // document.getElementById("password").value = "";
                        document.getElementById('login-validate').value = '';
                        changeImg();

                    }
                    else if(data.fin === "ok2"){
                        document.getElementById("error").innerText = "您已被网站管理员拉黑";
                        document.getElementById("username").value = "";
                        document.getElementById("password").value = "";
                        document.getElementById('login-validate').value = '';
                        document.getElementById("aregis").innerHTML="";
                        document.getElementById("Button").style.backgroundColor="#5d5b5b"
                        changeImg();
                    }

                    else {
                        document.getElementById("error").inner = "正确";
                        var path = String(data.path);
                        path = path + "?username=" + username;
                        window.location.href = path;
                    }
                }
            }
            xhr.send();
            return false;
    }
    document.getElementById("img").onclick = function (){
        changeImg();
    }


}

function changeImg() {
    console.log("草泥马")
    document.getElementById("img").src="http://localhost:8080/Pbhz/validateCode?n="+Math.random();
}
