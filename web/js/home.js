window.onload = function(){

    // example.html

// 获取URL参数
    let urlSearchParams = new URLSearchParams(window.location.search);
    let name = urlSearchParams.get('username');
    if(name == null)window.location.href="http://localhost:8080/Pbhz/login/login.html";
    document.getElementById("login-name").innerHTML = "<a class='login-name' href='person.jsp?username="+name+"'>"+name+"</a>";
    document.getElementById("post").innerHTML = "<a class='post' href='post.jsp'>我要发表</a>";
    // 现在你可以根据需要使用param1和param2
    loadHomePage();

    document.getElementById("home").onclick = function(){
        loadHome();
    }


    document.getElementById("help").onclick = function(){
        loadHelp();
    }

    document.getElementById("learn").onclick = function(){
        loadLearn();
    }

    document.getElementById("psychogical").onclick = function(){
        loadPsychogical();

    }

    document.getElementById("health").onclick = function(){
        loadHealth();
    }

    document.getElementById("law").onclick = function(){
        loadLaw();
    }

    document.getElementById("job").onclick = function (){
        loadJob();
    }


    document.getElementById("other").onclick = function (){
        loadOther();
    }

    document.getElementById("search-button").onclick = function (){
        addContent();
        let key = document.getElementById("search-key").value;
        console.log(key)
        if(key===""){
            loadHomePage();
        }
        fetch("http://localhost:8080/Pbhz/homeLoadServlet?action=searchKey&key="+key)
            .then(response => response.json())
            .then(data=>{
                //处理数据
                let html = "";
                //处理返回的内容
                for(let d of data){
                    let single = "<div class='single-rect'>"+
                        "<br>"+
                        "<div class='use'><div class='icon-person'><img class='icon-image1' src='"+d.icon+"' alt='faild'></div>"+
                        "<div class='name'><a href='users.jsp?username="+d.name+"'>"+d.name+" :</a></div></div>" +
                        "<a href='reply.jsp?id="+d.qid+"'><div class='cont'>"+d.content+"</div></a>" +
                        "<div><span class='theme'>#"+d.type+"#</span> <span class='date'>发表于"+d.date+"</span> </div>"+
                        "<br></div>";

                    html+=single;
                }

                document.getElementById("otherPage").innerHTML = html+"<br><br>";


            })
            .catch(error => console.error('error:',error));
        return false;
    }

}

function loadHomePage(){


    fetch('http://localhost:8080/Pbhz/homeLoadServlet?action=updateHome')
        .then(response => response.json())
        .then(data => {
            console.log(data)
            document.getElementById("content").innerHTML =
                " <div class=\"rectangle rectangle1\">\n" +
                "            <!-- 左侧图片和文字 -->\n" +
                "            <div onclick='loadLearn()' class=\"left-content\">\n" +
                "                <img src=\"./images/切图/icon-2.png\" alt=\"学术之林\">\n" +
                "                <div class=\"wenzi\">学术之林<br>LEARNING</div>\n" +
                "            </div>\n" +
                "            <!-- 右侧文字 -->\n" +
                "            <div class=\"right-content\">\n" +
                "                <dl>\n" +
                "                    <a href='reply.jsp?id="+data[0].qid +"'><dt id=\"learn1-content\"></dt></a>\n" +
                "                    <dd>发表于:<span id=\"learn1-date\"></span> </dd>\n" +
                "                    <a href='reply.jsp?id="+data[1].qid +"'><dt id=\"learn2-content\"></dt></a>\n" +
                "                    <dd> 发表于:<span id=\"learn2-date\"></span></dd>\n" +
                "                </dl>\n" +
                "            </div>\n" +
                "         <a href='post.jsp?type=learning'><img src='./images/切图/icon-7.png' class='rectangle11'></a>\n"+
                "        </div>\n" +
                "        <div class=\"rectangle rectangle2\">\n" +
                "            <!-- 左侧图片和文字 -->\n" +
                "            <div class=\"left-content\" onclick='loadPsychogical()'>\n" +
                "                <img src=\"./images/切图/icon-3.png\" alt=\"心灵氧气\">\n" +
                "                <div class=\"wenzi\">心灵氧气<br>PSYCHOGICAL</div>\n" +
                "            </div>\n" +
                "            <!-- 右侧文字 -->\n" +
                "            <div class=\"right-content\">\n" +
                "                <dl>\n" +
                "                    <a href='reply.jsp?id="+data[2].qid +"'><dt id=\"psychogical1-content\"></dt></a>\n" +
                "                    <dd>发表于:<span id=\"psychogical1-date\"></span></dd>\n" +
                "                    <a href='reply.jsp?id="+data[3].qid +"'><dt id=\"psychogical2-content\"></dt></a>\n" +
                "                    <dd>发表于:<span id=\"psychogical2-date\"></span></dd>\n" +
                "                </dl>\n" +
                "            </div>\n" +
                "         <a href='post.jsp?type=psychogical'><img src='./images/切图/icon-11.png' class='rectangle11'></a>\n"+
                "        </div>\n" +
                "        <div class=\"rectangle rectangle3\">\n" +
                "            <!-- 左侧图片和文字 -->\n" +
                "            <div class=\"left-content\" onclick='loadHealth()'>\n" +
                "                <img src=\"./images/切图/icon-4.png\" alt=\"健康部落\">\n" +
                "                <div class=\"wenzi\">健康部落<br>HEALTH</div>\n" +
                "            </div>\n" +
                "            <!-- 右侧文字 -->\n" +
                "            <div class=\"right-content\">\n" +
                "                <dl>\n" +
                "                    <a href='reply.jsp?id="+data[4].qid +"'><dt id=\"health1-content\"></dt></a>\n" +
                "                    <dd>发表于:<span id=\"health1-date\"></span></dd>\n" +
                "                    <a href='reply.jsp?id="+data[5].qid +"'><dt id=\"health2-content\"></dt></a>\n" +
                "                    <dd>发表于:<span id=\"health2-date\"></span></dd>\n" +
                "                </dl>\n" +
                "            </div>\n" +
                "         <a href='post.jsp?type=health'><img src='./images/切图/icon-9.png' class='rectangle11'></a>\n"+
                "        </div>\n" +
                "        <div class=\"rectangle rectangle4\">\n" +
                "            <!-- 左侧图片和文字 -->\n" +
                "            <div class=\"left-content\" onclick='loadLaw()'>\n" +
                "                <img src=\"./images/切图/icon-5.png\" alt=\"法律咨询\">\n" +
                "                <div class=\"wenzi\">法律咨询<br>LAW</div>\n" +
                "            </div>\n" +
                "            <!-- 右侧文字 -->\n" +
                "            <div class=\"right-content\">\n" +
                "                <dl>\n" +
                "                    <a href='reply.jsp?id="+data[6].qid +"'><dt id=\"law1-content\"></dt></a>\n" +
                "                    <dd>发表于:<span id=\"law1-date\"></span></dd>\n" +
                "                    <a href='reply.jsp?id="+data[7].qid +"'><dt id=\"law2-content\"></dt></a>\n" +
                "                    <dd>发表于:<span id=\"law2-date\"></span></dd>\n" +
                "                </dl>\n" +
                "            </div>\n" +
                "         <a href='post.jsp?type=law'><img src='./images/切图/icon-7.png' class='rectangle11'></a>\n"+
                "        </div>\n" +
                "        <div class=\"rectangle rectangle5\">\n" +
                "            <!-- 左侧图片和文字 -->\n" +
                "            <div class=\"left-content\" onclick='loadJob()'>\n" +
                "                <img src=\"./images/切图/icon-6.png\" alt=\"就业锦囊\">\n" +
                "                <div class=\"wenzi\">就业锦囊<br>JOB</div>\n" +
                "            </div>\n" +
                "            <!-- 右侧文字 -->\n" +
                "            <div class=\"right-content\">\n" +
                "                <dl>\n" +
                "                    <a href='reply.jsp?id="+data[8].qid +"'><dt id=\"job1-content\"></dt></a>\n" +
                "                    <dd>发表于:<span id=\"job1-date\"></span></dd>\n" +
                "                    <a href='reply.jsp?id="+data[9].qid +"'><dt id=\"job2-content\"></dt></a>\n" +
                "                    <dd>发表于:<span id=\"job2-date\"></span></dd>\n" +
                "                </dl>\n" +
                "            </div>\n" +
                "         <a href='post.jsp?type=job'><img src='./images/切图/icon-11.png' class='rectangle11'></a>\n"+
                "        </div>";





            //处理送来的字符串
            document.getElementById("learn1-content").innerHTML = data[0].content;
            document.getElementById("learn1-date").innerHTML = data[0].date;
            // document.getElementById("learn1-num").innerHTML = data[0].num;

            document.getElementById("learn2-content").innerHTML = data[1].content;
            document.getElementById("learn2-date").innerHTML = data[1].date;
            // document.getElementById("learn2-num").innerHTML = data[1].num;

            document.getElementById("psychogical1-content").innerHTML = data[2].content;
            document.getElementById("psychogical1-date").innerHTML = data[2].date;
            // document.getElementById("psychogical1-num").innerHTML = data[2].num;

            document.getElementById("psychogical2-content").innerHTML = data[3].content;
            document.getElementById("psychogical2-date").innerHTML = data[3].date;
            // document.getElementById("psychogical2-num").innerHTML = data[3].num;

            document.getElementById("health1-content").innerHTML = data[4].content;
            document.getElementById("health1-date").innerHTML = data[4].date;
            // document.getElementById("health1-num").innerHTML = data[4].num;

            document.getElementById("health2-content").innerHTML = data[5].content;
            document.getElementById("health2-date").innerHTML = data[5].date;
            // document.getElementById("health2-num").innerHTML = data[5].num;

            document.getElementById("law1-content").innerHTML = data[6].content;
            document.getElementById("law1-date").innerHTML = data[6].date;
            // document.getElementById("law1-num").innerHTML = data[6].num;

            document.getElementById("law2-content").innerHTML = data[7].content;
            document.getElementById("law2-date").innerHTML = data[7].date;
            // document.getElementById("law2-num").innerHTML = data[7].num;

            document.getElementById("job1-content").innerHTML = data[8].content;
            document.getElementById("job1-date").innerHTML = data[8].date;
            // document.getElementById("job1-num").innerHTML = data[8].num;

            document.getElementById("job2-content").innerHTML = data[9].content;
            document.getElementById("job2-date").innerHTML = data[9].date;
            // document.getElementById("job2-num").innerHTML = data[9].num;

        })
        .catch(error => console.error('error:',error));

}

function addContent(){
    document.getElementById("content").innerHTML=
        "    <div id=\"otherPage\" class=\"otherPage\">\n" +
        "\n" +
        "    </div>";

}

function loadHome(){
    loadHomePage();
    document.getElementById("home").style.backgroundColor="skyblue";
    document.getElementById("help").style.backgroundColor = "transparent";
    document.getElementById("learn").style.backgroundColor = "transparent";
    document.getElementById("psychogical").style.backgroundColor = "transparent";
    document.getElementById("health").style.backgroundColor = "transparent";
    document.getElementById("law").style.backgroundColor = "transparent";
    document.getElementById("job").style.backgroundColor = "transparent";
    document.getElementById("other").style.backgroundColor = "transparent";
}

function loadHelp(){
    addContent();
    document.getElementById("home").style.backgroundColor = "transparent";
    document.getElementById("help").style.backgroundColor = "skyblue";
    document.getElementById("learn").style.backgroundColor = "transparent";
    document.getElementById("psychogical").style.backgroundColor = "transparent";
    document.getElementById("health").style.backgroundColor = "transparent";
    document.getElementById("law").style.backgroundColor = "transparent";
    document.getElementById("job").style.backgroundColor = "transparent";
    document.getElementById("other").style.backgroundColor = "transparent";

    //进行异步请求
    fetch('http://localhost:8080/Pbhz/homeLoadServlet?action=updateHelp')
        .then(response => response.json())
        .then(data => {
            console.log(data);
            let html = "";
            //处理返回的内容
            for(let d of data){
                let single ="<div class='single-rect'><br>"+
                    "<div class='use'><div class='icon-person'><img class='icon-image1' src='"+d.icon+"' alt='faild'></div>"+
                    "<div class='name'><a href='users.jsp?username="+d.name+"'>"+d.name+" :</a></div></div>" +
                    "<a href='reply.jsp?id="+d.qid+"'><div class='cont'>"+d.content+"</div></a>" +
                    "<div><span class='theme'>#help#</span> <span class='date'>发表于"+d.date+"</span> </div>"+
                    "<br></div>";

                html+=single;
            }

            document.getElementById("otherPage").innerHTML = html+"<br><br>";

        })
        .catch(error => console.error('error:',error));

}

function loadLearn(){
    addContent();
    document.getElementById("home").style.backgroundColor = "transparent";
    document.getElementById("help").style.backgroundColor = "transparent";
    document.getElementById("learn").style.backgroundColor = "skyblue";
    document.getElementById("psychogical").style.backgroundColor = "transparent";
    document.getElementById("health").style.backgroundColor = "transparent";
    document.getElementById("law").style.backgroundColor = "transparent";
    document.getElementById("job").style.backgroundColor = "transparent";
    document.getElementById("other").style.backgroundColor = "transparent";

    //进行异步请求
    fetch('http://localhost:8080/Pbhz/homeLoadServlet?action=updateLearn')
        .then(response => response.json())
        .then(data => {

            //处理返回的内容
            let html = "";
            //处理返回的内容
            for(let d of data){
                let single ="<div class='single-rect'>"+
                    "<br>"+
                    "<div class='use'><div class='icon-person'><img class='icon-image1' src='"+d.icon+"' alt='faild'></div>"+
                    "<div class='name'><a href='users.jsp?username="+d.name+"'>"+d.name+" :</a></div></div>" +
                    "<a href='reply.jsp?id="+d.qid+"'><div class='cont'>"+d.content+"</div></a>" +
                    "<div><span class='theme'>#learn#</span> <span class='date'>发表于"+d.date+"</span> </div>"+
                    "<br></div>";

                html+=single;
            }

            document.getElementById("otherPage").innerHTML = html+"<br><br>";


        })
        .catch(error => console.error('error:',error));
}


function loadPsychogical(){
    addContent();
    document.getElementById("home").style.backgroundColor = "transparent";
    document.getElementById("help").style.backgroundColor = "transparent";
    document.getElementById("learn").style.backgroundColor = "transparent";
    document.getElementById("psychogical").style.backgroundColor = "skyblue";
    document.getElementById("health").style.backgroundColor = "transparent";
    document.getElementById("law").style.backgroundColor = "transparent";
    document.getElementById("job").style.backgroundColor = "transparent";
    document.getElementById("other").style.backgroundColor = "transparent";

    //进行异步请求
    fetch('http://localhost:8080/Pbhz/homeLoadServlet?action=updatePsychogical')
        .then(response => response.json())
        .then(data => {
            //处理返回的内容
            //处理返回的内容
            let html = "";
            //处理返回的内容
            for(let d of data){
                let single = "<div class='single-rect'>"+
                    "<br>"+
                    "<div class='use'><div class='icon-person'><img class='icon-image1' src='"+d.icon+"' alt='faild'></div>"+
                    "<div class='name'><a href='users.jsp?username="+d.name+"'>"+d.name+" :</a></div></div>" +
                    "<a href='reply.jsp?id="+d.qid+"'><div class='cont'>"+d.content+"</div></a>" +
                    "<div><span class='theme'>#psychogical#</span> <span class='date'>发表于"+d.date+"</span> </div>"+
                    "<br></div>";

                html+=single;
            }

            document.getElementById("otherPage").innerHTML = html+"<br><br>";


        })
        .catch(error => console.error('error:',error));

}


function loadHealth(){
    addContent();
    document.getElementById("home").style.backgroundColor = "transparent";
    document.getElementById("help").style.backgroundColor = "transparent";
    document.getElementById("learn").style.backgroundColor = "transparent";
    document.getElementById("psychogical").style.backgroundColor = "transparent";
    document.getElementById("health").style.backgroundColor = "skyblue";
    document.getElementById("law").style.backgroundColor = "transparent";
    document.getElementById("job").style.backgroundColor = "transparent";
    document.getElementById("other").style.backgroundColor = "transparent";

    //进行异步请求
    fetch('http://localhost:8080/Pbhz/homeLoadServlet?action=updateHealth')
        .then(response => response.json())
        .then(data => {
            //处理返回的内容
            //处理返回的内容
            let html = "";
            //处理返回的内容
            for(let d of data){
                let single = "<div class='single-rect'>"+
                    "<br>"+
                    "<div class='use'><div class='icon-person'><img class='icon-image1' src='"+d.icon+"' alt='faild'></div>"+
                    "<div class='name'><a href='users.jsp?username="+d.name+"'>"+d.name+" :</a></div></div>" +
                    "<a href='reply.jsp?id="+d.qid+"'><div class='cont'>"+d.content+"</div></a>" +
                    "<div><span class='theme'>#health#</span> <span class='date'>发表于"+d.date+"</span> </div>"+
                    "<br></div>";

                html+=single;
            }

            document.getElementById("otherPage").innerHTML = html+"<br><br>";

        })
        .catch(error => console.error('error:',error));

}

function loadLaw(){
    addContent();
    document.getElementById("home").style.backgroundColor = "transparent";
    document.getElementById("help").style.backgroundColor = "transparent";
    document.getElementById("learn").style.backgroundColor = "transparent";
    document.getElementById("psychogical").style.backgroundColor = "transparent";
    document.getElementById("health").style.backgroundColor = "transparent";
    document.getElementById("law").style.backgroundColor = "skyblue";
    document.getElementById("job").style.backgroundColor = "transparent";
    document.getElementById("other").style.backgroundColor = "transparent";

    //进行异步请求
    fetch('http://localhost:8080/Pbhz/homeLoadServlet?action=updateLaw')
        .then(response => response.json())
        .then(data => {
            //处理返回的内容
            //处理返回的内容
            let html = "";
            //处理返回的内容
            for(let d of data){
                let single = "<div class='single-rect'>"+
                    "<br>"+
                    "<div class='use'><div class='icon-person'><img class='icon-image1' src='"+d.icon+"' alt='faild'></div>"+
                    "<div class='name'><a href='users.jsp?username="+d.name+"'>"+d.name+" :</a></div></div>" +
                    "<a href='reply.jsp?id="+d.qid+"'><div class='cont'>"+d.content+"</div></a>" +
                    "<div><span class='theme'>#law#</span> <span class='date'>发表于"+d.date+"</span></div>"+
                    "<br></div>";

                html+=single;
            }

            document.getElementById("otherPage").innerHTML = html+"<br><br>";

        })
        .catch(error => console.error('error:',error));
}

function loadJob(){
    addContent();
    document.getElementById("home").style.backgroundColor = "transparent";
    document.getElementById("help").style.backgroundColor = "transparent";
    document.getElementById("learn").style.backgroundColor = "transparent";
    document.getElementById("psychogical").style.backgroundColor = "transparent";
    document.getElementById("health").style.backgroundColor = "transparent";
    document.getElementById("law").style.backgroundColor = "transparent";
    document.getElementById("job").style.backgroundColor = "skyblue";
    document.getElementById("other").style.backgroundColor = "transparent";

    //进行异步请求
    fetch('http://localhost:8080/Pbhz/homeLoadServlet?action=updateJob')
        .then(response => response.json())
        .then(data => {
            //处理返回的内容
            //处理返回的内容
            let html = "";
            //处理返回的内容
            for(let d of data){
                let single = "<div class='single-rect'>"+
                    "<br>"+
                    "<div class='use'><div class='icon-person'><img class='icon-image1' src='"+d.icon+"' alt='faild'></div>"+
                    "<div class='name'><a href='users.jsp?username="+d.name+"'>"+d.name+" :</a></div></div>" +
                    "<a href='reply.jsp?id="+d.qid+"'><div class='cont'>"+d.content+"</div></a>" +
                    "<div><span class='theme'>#job#</span> <span class='date'>发表于"+d.date+"</span></div>"+
                    "<br></div>";

                html+=single;
            }

            document.getElementById("otherPage").innerHTML = html+"<br><br>";

        })
        .catch(error => console.error('error:',error));

}

function loadOther() {
    addContent();
    document.getElementById("home").style.backgroundColor = "transparent";
    document.getElementById("help").style.backgroundColor = "transparent";
    document.getElementById("learn").style.backgroundColor = "transparent";
    document.getElementById("psychogical").style.backgroundColor = "transparent";
    document.getElementById("health").style.backgroundColor = "transparent";
    document.getElementById("law").style.backgroundColor = "transparent";
    document.getElementById("job").style.backgroundColor = "transparent";
    document.getElementById("other").style.backgroundColor = "skyblue";

    //进行异步请求
    fetch('http://localhost:8080/Pbhz/homeLoadServlet?action=updateOther')
        .then(response => response.json())
        .then(data => {
            //处理返回的内容
            //处理返回的内容
            let html = "";
            //处理返回的内容
            for (let d of data) {
                let single = "<div class='single-rect'>" +
                    "<br>" +
                    "<div class='use'><div class='icon-person'><img class='icon-image1' src='" + d.icon + "' alt='faild'></div>" +
                    "<div class='name'><a href='users.jsp?username="+d.name+"'>"+d.name+" :</a></div></div>" +
                    "<a href='reply.jsp?id=" + d.qid + "'><div class='cont'>" + d.content + "</div></a>" +
                    "<div><span class='theme'>#other#</span> <span class='date'>发表于" + d.date + "</span> </div>" +
                    "<br></div>";

                html += single;
            }

            document.getElementById("otherPage").innerHTML = html + "<br><br>";

        })
        .catch(error => console.error('error:', error));
}
