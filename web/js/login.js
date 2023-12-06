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
            alert('验证码不正确');
            document.getElementById('inputCode').value = '';
            document.getElementById('checkCode').innerText = getCode();

            return false;
        }
        return true;
    }


}
