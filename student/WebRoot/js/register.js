window.onload = function() {

    username();

    password();

    repassword();
}
var usernamenum = 0,

    passwordnum = 0,

    repasswordnum = 0;

function changeSucceedStyle(nameID, spanID) { //如果输入成功的话，用来修改后面span的内容样式和输入框的颜色

    spanID.firstChild.nodeValue = "*";

    spanID.style.fontSize = "larger";

    spanID.style.color = "green";

    nameID.style.borderColor = "limegreen";

}



function changeFailedStyle(nameID, spanID) { //输入失败而且焦点不在该输入框时的样式

    spanID.firstChild.nodeValue = "*"; //设置后面的字体颜色、格式、大小

    spanID.style.fontSize = "larger";

    spanID.style.color = "red";

    nameID.style.borderColor = "red";

}



function changeFailingStyle(nameID, spanID) { //输入失败时但是焦点还在该输入框时的样式

    spanID.style.fontSize = "small";

    spanID.style.color = "red";

    nameID.style.borderColor = "red";

}

function spanValue(spanID, spanValue) { //匹配失败时，修改后面字的总和

    switch(spanValue) {

        case "usernameSpan":

            spanID.firstChild.nodeValue = "非空字符";

            break;

        case "passwordSpan":

            spanID.firstChild.nodeValue = "非空字符";

            break;

        case "repasswordSpan":

            spanID.firstChild.nodeValue = "确认密码";

            break;

        case "repasswordSpan1":

            spanID.firstChild.nodeValue = "密码不一致";

            break;
    }

}
function username() {

    var username = document.getElementById("username"); //得到账户的对象

    var usernameSpan = document.getElementById("usernameSpan"); //得到文本对象

    var pattern = /^\S{1,100}$/;


    username.onfocus = function() { //获得焦点时根据匹配成功与否修改span中的样式和内容

        if(!pattern.test(username.value)) { //如果获得焦点时输入不正确，重新调整样式

            spanValue(usernameSpan, "usernameSpan"); //修改提示语句

            changeFailingStyle(username, usernameSpan); //修改为匹配中并且失败的样式

        } else {//如果匹配成功但是用户名存在

                if(userAgain == 0) {

                    spanValue(usernameSpan, "usernameAgain"); //修改提示语句

                    changeFailingStyle(username, usernameSpan); //修改为匹配中并且失败的样式

                }

        }

    }

    username.onkeyup = function() { //输入内容是判断根据输入的值修改span中的样式和内容,使用up不是down，因为down读取时候有出入

        if(pattern.test(username.value)) { //匹配成功的话

            changeSucceedStyle(username, usernameSpan); //修改为匹配成功的样式

            if(!checkUsername(username.value + "username")) {//如果用户名存在

                spanValue(usernameSpan, "usernameAgain"); //修改提示语句

                changeFailingStyle(username, usernameSpan); //修改为匹配中并且失败的样式

                userAgain = 0; //修改为0

            } else {

                userAgain = 1; //修改为1

            }

        } else { //匹配失败

            spanValue(usernameSpan, "usernameSpan"); //修改提示语句

            changeFailingStyle(username, usernameSpan);

        }

    }

    username.onblur = function() { //失去焦点时根据匹配成功与否修改span中的样式和内容

        if(pattern.test(username.value) && userAgain) { //匹配成功的话

            changeSucceedStyle(username, usernameSpan); //修改为成功的样式

            usernamenum = 1;

        } else { //匹配失败

            changeFailedStyle(username, usernameSpan); //修改为失败的样式

            usernamenum = 0;

        }

    }

}



function password() {

    var password = document.getElementById("password"); //得到密码的对象

    var passwordSpan = document.getElementById("passwordSpan");

    var pattern = /^\S{1,100}$/;

    var repassword = document.getElementById("repassword");

    password.onfocus = function() { //获得焦点时根据匹配成功与否修改span中的样式和内容

        if(!pattern.test(password.value)) { //如果获得焦点时输入不正确，重新调整样式

            spanValue(passwordSpan, "passwordSpan"); //修改提示语句

            changeFailingStyle(password, passwordSpan);

        }

    }

    password.onkeyup = function() { //输入内容是判断根据输入的值修改span中的样式和内容,使用up不是down，因为down读取时候有出入

        if(pattern.test(password.value)) { //匹配成功

            changeSucceedStyle(password, passwordSpan);

            if(repassword.value != "") { //如果此时确认密码不为空，需要重新进行确认

                repassword.onfocus();

            }

        } else { //匹配失败

            spanValue(passwordSpan, "passwordSpan"); //修改提示语句

            changeFailingStyle(password, passwordSpan);

            if(repassword.value != "") {

                repassword.onfocus();

            }

        }

    }



    password.onblur = function() { //失去焦点时根据匹配成功与否修改span中的样式和内容

        if(repassword.value == "") {

            if(pattern.test(password.value)) { //匹配成功

                changeSucceedStyle(password, passwordSpan);

                passwordnum = 1;

            } else { //匹配失败

                changeFailedStyle(password, passwordSpan);

                passwordnum = 0;

            }

        } else {

            if(password.value != repassword.value) {

                repassword.onfocus();

                //repassword.style.borderColor = "red";

                repasswordnum = 0;
            }
        }
    }
}
function repassword() {

    var password = document.getElementById("password");

    var repassword = document.getElementById("repassword"); //得到确认密码的样式

    var repasswordSpan = document.getElementById("repasswordSpan");



    repassword.onfocus = function() {

        if(!(password.value == repassword.value && password.value != "")) { //如果两次密码的值不相等，修改内容和样式

            spanValue(repasswordSpan, "repasswordSpan"); //修改提示语句

            changeFailingStyle(repassword, repasswordSpan);

        } else { //是有可能修改完密码后 与 此时的确认密码不同   然后又修改成相同的时候又用

            changeSucceedStyle(repassword, repasswordSpan);

        }

    }

    repassword.onkeyup = function() {

        if(password.value == repassword.value && password.value != "") { //如果两次密码的值相等，修改内容和样式

            changeSucceedStyle(repassword, repasswordSpan);

        } else {

            spanValue(repasswordSpan, "repasswordSpan1");

            changeFailingStyle(repassword, repasswordSpan);

        }

    }

    repassword.onblur = function() {

        if(password.value == repassword.value && password.value != "") { //如果两次密码的值相等，修改内容和样式

            changeSucceedStyle(repassword, repasswordSpan);

            repasswordnum = 1;

        } else {

            spanValue(repasswordSpan, "repasswordSpan");

            changeFailedStyle(repassword, repasswordSpan);

            repasswordnum = 0;

        }

    }

}
