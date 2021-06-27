function onButtonClick(){
    var user_id = login_form.user_id.value;
    var password = login_form.password.value;
    
    var user_id_mes = 'ユーザーID';
    var password_mes = 'パスワード';

    if (user_id == "" && password == ""){
        alert(user_id_mes + "と" + password_mes + "を入力してください。");
        return false;
    }else if(user_id == ""){
        alert(user_id_mes + "を入力してください。");
        return false;
    }else if(password == ""){
        alert(password_mes + "を入力してください。");
        return false;
    }else{
        
    	if (user_id.match(/[^A-Za-z0-9]+/) && password.match(/[^A-Za-z0-9]+/)){
    		alert(user_id_mes + "と" + password_mes + "は半角英数字のみ入力可能です。修正してください。");
    	}else if (user_id.match(/[^A-Za-z0-9]+/)) {
    		alert(user_id_mes + "は半角英数字のみ入力可能です。修正してください。");
    		return false;
    	}else if(password.match(/[^A-Za-z0-9]+/)){
    		alert(password_mes + "は半角英数字のみ入力可能です。修正してください。");
    		return false;
    	}else{
        	return true;		
    	}
    }
}
