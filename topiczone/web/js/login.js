/* 登录表单验证 */
window.onload = function () {
    // 1. 获取表单
    let loginForm = document.querySelector(".login-form");
    // 2. 获取form中的输入表单
    let loginInput = document.querySelector(".login-id");
    let passwordInput = document.querySelector(".login-password");
    // 3. 表单输入的值
    let loginId = null;
    let password = null;
    // 4. 获取错误提示
    let loginException = document.querySelector(".login-validation");
    // 5. 设置是否提交的标识
    let loginIdFlag = true;
    let passwordFlag = true;

    // 表单获取焦点事件
    loginInput.onfocus = function () {
        loginException.innerHTML = "";
    };
    passwordInput.onfocus = function () {
        loginException.innerHTML = "";
    };

    // 表单失去焦点事件
    loginInput.onblur = function () {
        loginId = document.querySelector(".login-id").value;
        if (!isEmpty(loginId)) {
            // 验证账号长度是否合法
            if (!(loginId.length >= 6 && loginId.length <= 11)) {
                loginIdFlag = false;
                alert("账号长度必须在 6 ~ 11 位");
            }else {
                loginIdFlag = true;
            }
        }
    };
    passwordInput.onblur = function () {
        password = document.querySelector(".login-password").value;
        if (!isEmpty(password)) {
            // 去除密码中的空格
            if (/\s/g.test(password)) {
                password = password.replace(/\s/g, '');
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

    // 表单提交事件
    loginForm.onsubmit = function () {
        let loginId = document.querySelector(".login-id").value;
        let password = document.querySelector(".login-password").value;

        // 1. 验证账号是否为空
        if (isEmpty(loginId)) {
            loginIdFlag = false;
            alert("账号不能为空");
            return loginIdFlag;
        }

        // 2. 验证密码是否为空
        if (isEmpty(password)) {
            passwordFlag = false;
            alert("密码不能为空");
            return passwordFlag;
        }

        // 3. 如果以上条件都通过，则提交表单
        if(loginIdFlag && passwordFlag){
            return true;
        }else {
            alert("账号或密码格式有误,请重新输入");
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

