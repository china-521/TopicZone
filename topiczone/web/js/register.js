/* 注册验证 */
window.onload = function () {
    // 获取表单
    let registerForm = document.querySelector(".register-form");
    // 获取form中的输入表单
    let loginIdInput = document.querySelector(".loginId");
    let nickNameInput = document.querySelector(".nickName");
    let passwordInput = document.querySelector(".password");
    let passwordInput1 = document.querySelector(".password1");
    let headImageInput = document.querySelector(".headImage")
    // 获取表单输入的值
    let loginId = null;
    let nickName = null;
    let password = null;
    let password1 = null;
    let headImage = null;
    // 获取表单验证文本容器
    let registerValidation = document.querySelector(".register-validation");
    // 是否提交表单的标识
    let loginIdFlag = true;
    let nickNameFlag = true;
    let passwordFlag = true;
    let password1Flag = true;
    let headImageFlag = true;

    // 表单获取焦点事件
    loginIdInput.onfocus = function(){
        registerValidation.innerHTML = "";
    };
    nickNameInput.onfocus = function(){
        registerValidation.innerHTML = "";
    };
    passwordInput.onfocus = function(){
        registerValidation.innerHTML = "";
    };
    passwordInput1.onfocus = function(){
        registerValidation.innerHTML = "";
    };
    headImageInput.onfocus = function(){
        registerValidation.innerHTML = "";
    }

    // 表单失去焦点验证
    loginIdInput.onblur = function () {
        loginId = document.querySelector(".loginId").value;
        if (!isEmpty(loginId)) {
            // 验证账号长度是否合法
            if (!(loginId.length >= 6 && loginId.length <= 11)) {
                loginIdFlag = false;
                alert("账号长度必须在 6 ~ 11 位");
            }else {
                loginIdFlag = true;
            }
        }
    }
    nickNameInput.onblur = function () {
        nickName = document.querySelector(".nickName").value;
        if (!isEmpty(nickName)) {
            // 去除昵称中的空格
            if (/\s/.test(nickName)) {
                nickName = nickName.replace(/\s/g, '');
                nickNameInput.value = nickName;
            }
            if (!(nickName.length >= 1 && nickName.length <= 10)) {
                nickNameFlag = false;
                alert("昵称长度不合法,长度只能在：【1 ~ 10】之间");
            }else {
                nickNameFlag = true;
            }
            // 验证昵称字符是否合法
            if (!(/^[A-Za-z0-9\u4e00-\u9fa5]+$/.test(nickName))) {
                nickNameFlag = false;
                alert('昵称只能使用中文、英文或数字');
            }else {
                nickNameFlag = true;
            }
        }
    };
    passwordInput.onblur = function () {
        password = document.querySelector(".password").value;
        if (!isEmpty(password)) {
            // 去除密码中的空格
            if (/\s/g.test(password)) {
                password = password.replace(/\s/g, '');
                passwordInput.value = password;
            }
            // 必须包含大小写字母和数字的组合，不能使用特殊字符，长度在 6-10 之间
            if (!(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]{6,10}$/.test(password))) {
                passwordFlag = false;
                alert("密码格式错误：必须包含大小写字母和数字的组合，不能使用特殊字符，长度在 6-10 之间");
            }else {
                passwordFlag = true;
            }
        }

    };
    passwordInput1.onblur = function () {
        password = document.querySelector(".password").value;
        password1 = document.querySelector(".password1").value;
        if (!isEmpty(password1)) {
            // 去除密码中的空格
            if (/\s/g.test(password1)) {
                password1 = password1.replace(/\s/g, '');
                passwordInput1.value = password1;
            }
            // 必须包含大小写字母和数字的组合，不能使用特殊字符，长度在 6-10 之间
            if (!(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]{6,10}$/.test(password1))) {
                password1Flag = false;
                alert("密码格式错误：必须包含大小写字母和数字的组合，不能使用特殊字符，长度在 6-10 之间");
            }else {
                password1Flag = true;
            }
            if (password1 !== password) {
                password1Flag = false;
                alert("两次密码不一致，请重新输入");
                document.querySelector(".password").value = '';
                document.querySelector(".password1").value = '';
            }else {
                password1Flag = true;
            }
        }
    };
    headImageInput.onblur = function () {
        headImage = document.querySelector(".headImage").value;
        if (!isEmpty(headImage)) {
            if (/^[0-9]/.test(headImage)) {
                headImageFlag = true;
            } else {
                headImageFlag = false;
                alert("图片格式必须要以数字【0 ~ 9】开头");
            }

            if ((/\.jpg$/.test(headImage)) || (/\.png$/.test(headImage)) || (/\.gif$/.test(headImage)) || (/\.webp$/.test(headImage))) {
                headImageFlag = true;
            } else {
                headImageFlag = false;
                alert("图片格式必须以：【.jpg/.png/.gif/.webp】 结尾");
            }
        }
    };


    // 表单提交验证
    registerForm.onsubmit = function () {
        // 验证是否为空
        if (isEmpty(loginId)) {
            loginIdFlag = false;
            alert('账号不能为空');
            return loginIdFlag;
        }
        if (isEmpty(nickName)) {
            nickNameFlag = false;
            alert('昵称不能为空');
            return nickNameFlag;
        }
        if (isEmpty(password)) {
            passwordFlag = false;
            alert('密码不能为空');
            return passwordFlag;
        }
        if(isEmpty(password1)){
            password1Flag = false;
            alert('确认密码不能为空');
            return password1Flag;
        }

        if (isEmpty(headImage)) {
            headImageFlag = false;
            alert('头像不能为空');
            return headImageFlag;
        }
        if(loginIdFlag && nickNameFlag && passwordFlag && password1Flag && headImageFlag){
            return true;
        }else {
            alert("填写的数据格式有误，请重新输入");
            return false;
        }
    }
};

// 判断字符串是否为空
function isEmpty(value) {
    if (value === null || value === "" || value.length === 0) {
        return true;
    }
    return false;
}
