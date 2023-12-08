window.onload = function(){

    loadHomePage();

    document.getElementById("home").onclick = function(){
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

    document.getElementById("help").onclick = function(){
        addContent();
        document.getElementById("home").style.backgroundColor = "transparent";
        document.getElementById("help").style.backgroundColor = "skyblue";
        document.getElementById("learn").style.backgroundColor = "transparent";
        document.getElementById("psychogical").style.backgroundColor = "transparent";
        document.getElementById("health").style.backgroundColor = "transparent";
        document.getElementById("law").style.backgroundColor = "transparent";
        document.getElementById("job").style.backgroundColor = "transparent";
        document.getElementById("other").style.backgroundColor = "transparent";

    }

    document.getElementById("learn").onclick = function(){
        addContent();
        document.getElementById("home").style.backgroundColor = "transparent";
        document.getElementById("help").style.backgroundColor = "transparent";
        document.getElementById("learn").style.backgroundColor = "skyblue";
        document.getElementById("psychogical").style.backgroundColor = "transparent";
        document.getElementById("health").style.backgroundColor = "transparent";
        document.getElementById("law").style.backgroundColor = "transparent";
        document.getElementById("job").style.backgroundColor = "transparent";
        document.getElementById("other").style.backgroundColor = "transparent";
    }

    document.getElementById("psychogical").onclick = function(){
        addContent();
        document.getElementById("home").style.backgroundColor = "transparent";
        document.getElementById("help").style.backgroundColor = "transparent";
        document.getElementById("learn").style.backgroundColor = "transparent";
        document.getElementById("psychogical").style.backgroundColor = "skyblue";
        document.getElementById("health").style.backgroundColor = "transparent";
        document.getElementById("law").style.backgroundColor = "transparent";
        document.getElementById("job").style.backgroundColor = "transparent";
        document.getElementById("other").style.backgroundColor = "transparent";

    }

    document.getElementById("health").onclick = function(){
        addContent();
        document.getElementById("home").style.backgroundColor = "transparent";
        document.getElementById("help").style.backgroundColor = "transparent";
        document.getElementById("learn").style.backgroundColor = "transparent";
        document.getElementById("psychogical").style.backgroundColor = "transparent";
        document.getElementById("health").style.backgroundColor = "skyblue";
        document.getElementById("law").style.backgroundColor = "transparent";
        document.getElementById("job").style.backgroundColor = "transparent";
        document.getElementById("other").style.backgroundColor = "transparent";

    }

    document.getElementById("law").onclick = function(){
        addContent();
        document.getElementById("home").style.backgroundColor = "transparent";
        document.getElementById("help").style.backgroundColor = "transparent";
        document.getElementById("learn").style.backgroundColor = "transparent";
        document.getElementById("psychogical").style.backgroundColor = "transparent";
        document.getElementById("health").style.backgroundColor = "transparent";
        document.getElementById("law").style.backgroundColor = "skyblue";
        document.getElementById("job").style.backgroundColor = "transparent";
        document.getElementById("other").style.backgroundColor = "transparent";

    }

    document.getElementById("job").onclick = function (){
        addContent();
        document.getElementById("home").style.backgroundColor = "transparent";
        document.getElementById("help").style.backgroundColor = "transparent";
        document.getElementById("learn").style.backgroundColor = "transparent";
        document.getElementById("psychogical").style.backgroundColor = "transparent";
        document.getElementById("health").style.backgroundColor = "transparent";
        document.getElementById("law").style.backgroundColor = "transparent";
        document.getElementById("job").style.backgroundColor = "skyblue";
        document.getElementById("other").style.backgroundColor = "transparent";


    }
    document.getElementById("other").onclick = function (){
        addContent();
        document.getElementById("home").style.backgroundColor = "transparent";
        document.getElementById("help").style.backgroundColor = "transparent";
        document.getElementById("learn").style.backgroundColor = "transparent";
        document.getElementById("psychogical").style.backgroundColor = "transparent";
        document.getElementById("health").style.backgroundColor = "transparent";
        document.getElementById("law").style.backgroundColor = "transparent";
        document.getElementById("job").style.backgroundColor = "transparent";
        document.getElementById("other").style.backgroundColor = "skyblue";
    }

}

function loadHomePage(){
        document.getElementById("content").innerHTML =
            " <div class=\"rectangle rectangle1\">\n" +
            "            <!-- 左侧图片和文字 -->\n" +
            "            <div class=\"left-content\">\n" +
            "                <img src=\"./images/切图/icon-2.png\" alt=\"学术之林\">\n" +
            "                <div class=\"wenzi\">学术之林<br>LEARNING</div>\n" +
            "            </div>\n" +
            "            <!-- 右侧文字 -->\n" +
            "            <div class=\"right-content\">\n" +
            "                <dl>\n" +
            "                    <dt id=\"learn1-content\"></dt>\n" +
            "                    <dd>发表于:<span id=\"learn1-date\"></span> .<span id=\"learn1-num\"></span>个回复 <a href=\"#\"  style=\"margin-left: 25px;color: orange;\">回复</a></dd>\n" +
            "                    <dt id=\"learn2-content\"></dt>\n" +
            "                    <dd> 发表于:<span id=\"learn2-date\"></span> .<span id=\"learn2-num\"></span>个回复 <a href=\"#\" style=\"margin-left: 25px;color: orange;\">回复</a></dd>\n" +
            "                </dl>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "        <div class=\"rectangle rectangle2\">\n" +
            "            <!-- 左侧图片和文字 -->\n" +
            "            <div class=\"left-content\">\n" +
            "                <img src=\"./images/切图/icon-3.png\" alt=\"心灵氧气\">\n" +
            "                <div class=\"wenzi\">心灵氧气<br>PSYCHOGICAL</div>\n" +
            "            </div>\n" +
            "            <!-- 右侧文字 -->\n" +
            "            <div class=\"right-content\">\n" +
            "                <dl>\n" +
            "                    <dt id=\"psychogical1-content\"></dt>\n" +
            "                    <dd>发表于:<span id=\"psychogical1-date\"></span> .<span id=\"psychogical1-num\"></span> 个回复<a href=\"#\" style=\"margin-left: 25px;color: orange;\">回复</a></dd>\n" +
            "                    <dt id=\"psychogical2-content\"></dt>\n" +
            "                    <dd>发表于:<span id=\"psychogical2-date\"></span> .<span id=\"psychogical2-num\"></span> 个回复<a href=\"#\" style=\"margin-left: 25px;color: orange;\">回复</a></dd>\n" +
            "                </dl>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "        <div class=\"rectangle rectangle3\">\n" +
            "            <!-- 左侧图片和文字 -->\n" +
            "            <div class=\"left-content\">\n" +
            "                <img src=\"./images/切图/icon-4.png\" alt=\"健康部落\">\n" +
            "                <div class=\"wenzi\">健康部落<br>HEALTH</div>\n" +
            "            </div>\n" +
            "            <!-- 右侧文字 -->\n" +
            "            <div class=\"right-content\">\n" +
            "                <dl>\n" +
            "                    <dt id=\"health1-content\"></dt>\n" +
            "                    <dd>发表于:<span id=\"health1-date\"></span> .<span id=\"health1-num\"></span>个回复<a href=\"#\" style=\"margin-left: 25px;color: orange;\">回复</a></dd>\n" +
            "                    <dt id=\"health2-content\"></dt>\n" +
            "                    <dd>发表于:<span id=\"health2-date\"></span> .<span id=\"health2-num\"></span>个回复<a href=\"#\" style=\"margin-left: 25px;color: orange;\">回复</a></dd>\n" +
            "                </dl>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "        <div class=\"rectangle rectangle4\">\n" +
            "            <!-- 左侧图片和文字 -->\n" +
            "            <div class=\"left-content\">\n" +
            "                <img src=\"./images/切图/icon-5.png\" alt=\"法律咨询\">\n" +
            "                <div class=\"wenzi\">法律咨询<br>LAW</div>\n" +
            "            </div>\n" +
            "            <!-- 右侧文字 -->\n" +
            "            <div class=\"right-content\">\n" +
            "                <dl>\n" +
            "                    <dt id=\"law1-content\"></dt>\n" +
            "                    <dd>发表于:<span id=\"law1-date\"></span> .<span id=\"law1-num\"></span>个回复<a href=\"#\" style=\"margin-left: 25px;color: orange;\">回复</a></dd>\n" +
            "                    <dt id=\"law2-content\"></dt>\n" +
            "                    <dd>发表于:<span id=\"law2-date\"></span> .<span id=\"law2-num\"></span>个回复<a href=\"#\" style=\"margin-left: 25px;color: orange;\">回复</a></dd>\n" +
            "                </dl>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "        <div class=\"rectangle rectangle5\">\n" +
            "            <!-- 左侧图片和文字 -->\n" +
            "            <div class=\"left-content\">\n" +
            "                <img src=\"./images/切图/icon-6.png\" alt=\"就业锦囊\">\n" +
            "                <div class=\"wenzi\">就业锦囊<br>JOB</div>\n" +
            "            </div>\n" +
            "            <!-- 右侧文字 -->\n" +
            "            <div class=\"right-content\">\n" +
            "                <dl>\n" +
            "                    <dt id=\"job1-content\"></dt>\n" +
            "                    <dd>发表于:<span id=\"job1-date\"></span> .<span id=\"job1-num\"></span>个回复<a href=\"#\" style=\"margin-left: 25px;color: orange;\">回复</a></dd>\n" +
            "                    <dt id=\"job2-content\"></dt>\n" +
            "                    <dd>发表于:<span id=\"job2-date\"></span> .<span id=\"job2-num\"></span>个回复<a href=\"#\" style=\"margin-left: 25px;color: orange;\">回复</a></dd>\n" +
            "                </dl>\n" +
            "            </div>\n" +
            "        </div>";

    fetch('http://localhost:8080/Pbhz/homeLoadServlet?action=updateHome')
        .then(response => response.json())
        .then(data => {
            //处理送来的字符串
            document.getElementById("learn1-content").innerHTML = data[0].content;
            document.getElementById("learn1-date").innerHTML = data[0].date;
            document.getElementById("learn1-num").innerHTML = data[0].num;

            document.getElementById("learn2-content").innerHTML = data[1].content;
            document.getElementById("learn2-date").innerHTML = data[1].date;
            document.getElementById("learn2-num").innerHTML = data[1].num;

            document.getElementById("psychogical1-content").innerHTML = data[2].content;
            document.getElementById("psychogical1-date").innerHTML = data[2].date;
            document.getElementById("psychogical1-num").innerHTML = data[2].num;

            document.getElementById("psychogical2-content").innerHTML = data[3].content;
            document.getElementById("psychogical2-date").innerHTML = data[3].date;
            document.getElementById("psychogical2-num").innerHTML = data[3].num;

            document.getElementById("health1-content").innerHTML = data[4].content;
            document.getElementById("health1-date").innerHTML = data[4].date;
            document.getElementById("health1-num").innerHTML = data[4].num;

            document.getElementById("health2-content").innerHTML = data[5].content;
            document.getElementById("health2-date").innerHTML = data[5].date;
            document.getElementById("health2-num").innerHTML = data[5].num;

            document.getElementById("law1-content").innerHTML = data[6].content;
            document.getElementById("law1-date").innerHTML = data[6].date;
            document.getElementById("law1-num").innerHTML = data[6].num;

            document.getElementById("law2-content").innerHTML = data[7].content;
            document.getElementById("law2-date").innerHTML = data[7].date;
            document.getElementById("law2-num").innerHTML = data[7].num;

            document.getElementById("job1-content").innerHTML = data[8].content;
            document.getElementById("job1-date").innerHTML = data[8].date;
            document.getElementById("job1-num").innerHTML = data[8].num;

            document.getElementById("job2-content").innerHTML = data[9].content;
            document.getElementById("job2-date").innerHTML = data[9].date;
            document.getElementById("job2-num").innerHTML = data[9].num;

        })
        .catch(error => console.error('error:',error));

}

function addContent(){
    document.getElementById("content").innerHTML=
        "    <div id=\"otherPage\" class=\"otherPage\">\n" +
        "\n" +
        "    </div>";

}