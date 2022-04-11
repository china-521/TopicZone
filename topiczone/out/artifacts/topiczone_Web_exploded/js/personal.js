/* 个人中心 */

// 注销账户
function shutAccount(nickName,userBasicId){
    if(confirm("注销后将清空所有信息，确定要注销 " + nickName + " 账户吗")){
        window.location.href = "http://localhost:8080/topiczone/user.do?choice=shutAccount&userBasicId=" + userBasicId;
    }
}

/* 表单验证 */
window.onload = function(){
    // 获取表单
    let personal_form = document.querySelector(".personal-form");
    // 获取输入框
    let nameInput = document.querySelector(".name");
    let sexInput = document.querySelector(".sex");
    let emailInput = document.querySelector(".email");
    let phoneInput = document.querySelector(".phone");
    let cardIdInput = document.querySelector(".cardId");
    let starInput = document.querySelector(".star");

    // 表单输入的值
    let name = null;
    let sex = null;
    let email = null;
    let phone = null;
    let cardId = null;
    let star = null;

    // 异常信息
    let emailError = document.querySelector(".email-error");
    let phoneError = document.querySelector(".phone-error");
    let cardIdError = document.querySelector(".card-id-error");
    // 是否提交表单标识
    let flag = false;

    // 表单获得焦点事件
    emailInput.onfocus = function(){
        emailError.innerHTML = "";
    };
    phoneInput.onfocus = function(){
        phoneError.innerHTML = "";
    };
    cardIdInput.onfocus = function(){
        cardIdError.innerHTML = "";
    };

    // 表单失去焦点事件
    nameInput.onblur = function(){
        name = document.querySelector(".name").value;
        if(!isEmpty(name)){
            if(/^[A-Za-z\u4e00-\u9fa5]+$/.test(name)){
                flag = true;
            }else {
                flag = false;
                alert("名字格式不合法，名字只能输入中文或英文，请重新输入");
                return;
            }

            if(name.length < 2){
                flag = false;
                alert("姓名长度不能少于2个字符，请重新输入");
                return;
            }else if(name.length > 4){
                flag = false;
                alert("姓名长度不能超过4个字符，请重新输入");
                return;
            }else {
                flag = true;
            }
        }
    };

    sexInput.onblur = function(){
        sex = document.querySelector(".sex").value;
        if(!(isEmpty(sex))){
            if(!(sex === '男' || sex === '女')){
                flag = false;
                alert("性别格式不合法，只能输入男或女，请重新输入");
                return;
            }else {
                flag = true;
            }
        }
    };

    emailInput.onblur = function(){
        email = document.querySelector(".email").value;
        if(!isEmpty(email)){
            if(!(/^\w{3,}(\.\w+)*@[A-z0-9]+(\.[A-z]{2,5}){1,2}$/g.test(email))){
                flag =  false;
                alert("邮件格式不合法，请重新输入");
                return;
            }else {
                flag = true;
            }
        }
    };

    phoneInput.onblur = function () {
        phone = document.querySelector(".phone").value;
        if(!isEmpty(phone)){
            if(!(/^1[3-9][0-9]{9}$/.test(phone))){
                flag = false;
                alert("手机号码格式不合法：只能输入数字，且长度为11位，请重新输入");
                return;
            }else {
                flag = true;
            }
        }
    };

    cardIdInput.onblur = function(){
        cardId = document.querySelector(".cardId").value;
        if(!isEmpty(cardId)){
            if(!(/^[0-9]{8,12}$/.test(cardId))){
                flag = false;
                alert("身份证格式不合法：只能输入数字，且长度不超过12位，不低于8位，请重新输入");
                return;
            }else {
                flag = true;
            }
        }
    };

    starInput.onblur = function(){
        star = document.querySelector(".star").value;
        if(/\s/g.test(star)){
            star = star.replace(/\s/g, '');
            starInput.value = star;
        }
    };

    // 表单提交事件
    personal_form.onsubmit = function(){
        if(!flag){
            alert("更新失败，请检查表单输入数据格式是否合法！");
            return;
        }
        return flag;
    };

};

// 判断字符串是否为空
function isEmpty(value) {
    if (value === null || value === "" || value.length === 0) {
        return true;
    }
    return false;
}
