
    function validate_form(){
        var name = document.getElementById("username").value;
        var password = document.getElementById("password").value;
        if(name==null || name==""){
            alert("用户名不能为空");
            return false;
        }
        if(password==null || password==""){
            alert("密码不能为空");
            return false;
        }
        return true;

    }