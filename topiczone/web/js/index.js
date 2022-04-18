// 删除日志
function deleteTopic(topicId,authorId,title) {
    if (confirm("确定要删除 《" + title + "》吗")) {
        window.location.href = "http://localhost:8080/topiczone/topic.do?choice=delete&topicId=" + topicId + "&authorId=" + authorId;
    }
}

// 退出登录
function out(nickName) {
    if (confirm("确定要退出【" + nickName + "】的空间吗？")) {
        window.location.replace("http://localhost:8080/topiczone/user.do?choice=intoLogin");
    }
}

// 分页
function page(friendId,pageNumber){
    window.location.href = "http://localhost:8080/topiczone/user.do?choice=friend&id=" + friendId + "&pageNumber=" + pageNumber;
}
