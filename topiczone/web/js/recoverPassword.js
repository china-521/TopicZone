/* 表单验证 */

window.onload = function(){
    let password_form = document.querySelector(".password-form");
    let phoneInput = document.querySelector(".phone");
    let recoverPasswordError = document.querySelector(".recover-password-error");
    let phone = null;
    let flag = true;


    phoneInput.onfocus = function(){
        recoverPasswordError.innerHTML = "";
    };


    phoneInput.onblur = function(){
        phone = document.querySelector(".phone").value;
        if(!isEmpty(phone)){
            if(!(/^1[3-9][0-9]{9}$/.test(phone))){
                flag = false;
                alert("手机号码格式不合法：只能输入数字，且长度为11位，请重新输入");
            }else {
                flag = true;
            }
        }
    };

    password_form.onsubmit = function(){
        if(isEmpty(phone)){
            flag = false;
            alert("手机号不能为空");
            return flag;
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