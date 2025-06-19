function handleError(res) {
    switch (res?.code) { // ✅ 安全：如果 res 为 null/undefined，则返回 undefined，不报错
        case 401:
            alert("请登录后操作");
            location.href = "/blog_login.html";
            break;
        case 400:
            alert(res.errMsg || "请求参数错误");
            break;
        case 500:
            alert("系统异常，请稍后重试");
            break;
        default:
            alert(res.errMsg || "请求失败");
    }
}

$(document).ajaxSend(function(event,xhr,options) {
    xhr.setRequestHeader("user_token", localStorage.getItem("user_token"));
});

$(document).ajaxError(function(event,xhr,options,exc) {
    if (xhr.status === 401) {
        alert("用户未登录，请先登录~");
        location.href = "/blog_login.html";
    } else if (xhr.status === 0) {
        alert("无法连接服务器，请检查网络");
    } else if (xhr.status === 404) {
        alert("请求的接口不存在");
    } else {
        alert("系统异常，请稍后重试（状态码：" + xhr.status + "）");
    }
})

function getUserInfo(queryUrl) {
    $.ajax({
        url: queryUrl,
        type: "get",
        success: function(result) {
            console.log(result);
            if(result != null && result.code == 200 && result.data != null) {
                $(".container .left .card h3").html(result.data.userName);
                $(".container .left .card a").attr("href", result.data.githubUrl);
            }
        }
    });
}

function logout() {
    // 清除token即可
    localStorage.removeItem("user_token");
    localStorage.removeItem("loginUserId");
    location.href = "../blog_login.html";
}