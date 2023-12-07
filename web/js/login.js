window.onload = function(){
    var res = getCode();
    function getCode(){
        var arr = ['0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'];
        var str = '';
        for(var i = 0;i<6;i++){
            var num = Math.round(Math.random()*(15-0)+0);
            str += arr[num];
        }
        return str;
    }
    document.getElementById('checkCode').innerText = res;
    // 点击事件
    document.getElementById( 'linkbt').onclick = function(){
        document.getElementById('checkCode').innerText = getCode();
    }

    // 返回值是true才会提交表单
    document.getElementById("Button").onclick = function (){
        var code = document.getElementById('checkCode').innerText;
        var inputCode = document.getElementById('inputCode').value;
        if(code != inputCode){
            document.getElementById("error").innerText="验证码错误";
            document.getElementById('inputCode').value = '';
            document.getElementById('checkCode').innerText = getCode();

        }
        else{

            var username = document.getElementById("username").value;
            var password = document.getElementById("password").value;
            console.log("username"+username)
            console.log("password"+password)
            var xhr = new XMLHttpRequest();
            xhr.open("GET","http://localhost:8080/Pbhz/loginServlet?username="+username+"&password="+password);

            xhr.onreadystatechange  = function(){
                if(xhr.readyState === 4 && xhr.status === 200){
                    var data = JSON.parse(xhr.responseText);
                    console.log(data)
                    if(data.fin == "ok"){
                        document.getElementById("error").innerText = "账号或密码错误";
                        document.getElementById("username").innerText="";
                        document.getElementById("password").innerText="";
                        document.getElementById('inputCode').value = '';
                        document.getElementById('checkCode').innerText = getCode();
                    }
                    else{
                        document.getElementById("error").inner = "正确";
                        var path = String(data.path);
                        window.location.href = path;
                    }
                }
            }
            xhr.send();
        }
        return false;
    }


}
