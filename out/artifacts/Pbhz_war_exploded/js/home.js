window.onload = function(){
    /**
     * 当页面记载完成之后，执行从数据库拉取数据
     * 先改掉一处数据看一看
     */
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