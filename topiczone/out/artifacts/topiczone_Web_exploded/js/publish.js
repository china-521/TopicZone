/* 发布日志表单验证 */

window.onload = function(){
    let publishForm = document.querySelector(".publish-form");
    let titleInput = document.querySelector(".topic-title");
    let contentInput = document.querySelector("#content");
    let title = null;
    let content = null;
    // 是否发布标识
    let titleFlag = true;
    let contentFlag = true;

    titleInput.onblur = function(){
        title = document.querySelector(".topic-title").value;
        if(!isEmpty(title)){
            // 去除标题中的空格
            if (/\s/g.test(title)) {
                title = title.replace(/\s/g, '');
                titleInput.value = title;
            }
            if(title.length >= 1 && title.length <= 20){
                titleFlag = true;
            }else {
                titleFlag = false;
                alert("标题长度不合法，标题长度在【1-20】个字符之间，请重新输入");
            }
        }
    };

    contentInput.onblur = function () {
        content = document.querySelector("#content").value;
        if(content.length >= 1){
            contentFlag = true;
        }else {
            contentFlag = false;
        }
    };

    publishForm.onsubmit = function(){
        if(isEmpty(title)){
            titleFlag = false;
            alert("日志标题不能为空");
            return titleFlag;
        }
        if(isEmpty(content)){
            contentFlag = false;
            alert("日志内容不能为空");
            return contentFlag;
        }
        if(titleFlag && contentFlag){
            return true;
        }else {
            return false;
        }
    };

};

// 判断字符串是否为空
function isEmpty(value) {
    if (value === null || value === "" || value.length === 0) {
        return true;
    }
    return false;
}
