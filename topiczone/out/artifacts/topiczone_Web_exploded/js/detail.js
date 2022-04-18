// 点击回复，弹出回复评论框
window.onload = function () {
    let reply_btn = document.querySelectorAll(".reply-comments");
    let reply_board = [];
    let minHeight = 0;
    let index = 0;
    for (let i = 0; i < reply_btn.length; i++) {
        reply_board[i] = reply_btn[i].parentNode.parentNode.children[2];
        reply_btn[i].onclick = function () {
            index++;
            if (index % 2 === 0) {
                reply_btn[i].innerText = "回复评论";
                clearInterval(timer); // 防止点击过快
                var timer = setInterval(function () {
                    minHeight -= 10;
                    reply_board[i].style.minHeight = minHeight + "px";
                    if (minHeight <= 0) {
                        clearInterval(timer);
                    }
                }, 40);
            } else {
                clearInterval(timer1);
                reply_btn[i].innerText = "收起评论";
                var timer1 = setInterval(function () {
                    minHeight += 10
                    reply_board[i].style.minHeight = minHeight + "px";
                    if (minHeight >= 150) {
                        console.log(minHeight);
                        clearInterval(timer1);
                    }
                }, 40)
            }
        };
    }
};

// 删除评论
function deleteReply(author,replyId){
    if(confirm("确定要删除 " + author + " 的评论吗")){
        window.location.href = "http://localhost:8080/topiczone/reply.do?choice=deleteReply&replyId=" + replyId;
    }
}

// 删除评论回复
function deleteHostReply(author,hostReplyId) {
    if(confirm("确定要删除 " + author + " 的评论吗")){
        window.location.href = "http://localhost:8080/topiczone/reply.do?choice=deleteHostReply&hostReplyId=" + hostReplyId;
    }
}