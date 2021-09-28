function onRegisterClick(){
    //入力項目を設定
	var title = edit_form.TITLE.value;
    var progress = edit_form.PROGRESS.value;
    var startdate = edit_form.STARTDATE.value;
    var enddate = edit_form.ENDDATE.value;
    var evaluation = edit_form.EVALUATION.value;
    
    //アラートメッセージで出力するメッセージを設定
    var title_mes = 'タイトル';
    var progress_mes = '読破率';
    var startdate_mes = '開始日';
    var enddate_mes = '終了日';
    var evaluation_mes = '評価';

    // 読破率の最大値を設定
    var progress_max = 100;
    
    //必須チェック
    if (title == ""){
        alert(title_mes + "を入力してください。");
        return false;
    }else{
    	//読破率の範囲チェック
    	if (progress != "" &&  progress > progress_max) {
    		alert(progress_mes + "は「0～100」の範囲で設定してください。");
    		return false;
    	}else{
    		//開始日と終了日の相関チェック
    		if (startdate == "" && enddate !== ""){
    			alert(enddate_mes + "が設定されている場合、" + startdate_mes + "も設定してください。" );
    			return false;
    		}else{
    			//開始日のフォーマットチェック
    			if (startdate != "" && !startdate.match(/^\d{4}\/\d{1,2}\/\d{1,2}$/)){
    				alert(startdate_mes + "は「西暦/月/日」で設定してください。");
    				return false;
    			}else if(startdate != ""){
    				var y = startdate.split("/")[0];
        			var m = startdate.split("/")[1] - 1;
        			var d = startdate.split("/")[2];
        			var date = new Date(y,m,d);
        			//開始日が妥当かチェック
        			if(date.getFullYear() != y || date.getMonth() != m || date.getDate() != d){
        				alert(startdate_mes + "は「西暦/月/日」で設定してください。");
        				return false;
        			}else{
        				//終了日のフォーマットチェック
        				if (enddate !== "" && !enddate.match(/^\d{4}\/\d{1,2}\/\d{1,2}$/)){
        					alert(enddate_mes + "は「西暦/月/日」で設定してください。");
        					return false;
        				}else if(enddate !== ""){
        					var y = enddate.split("/")[0];
        					var m = enddate.split("/")[1] - 1;
            				var d = enddate.split("/")[2];
            				var date = new Date(y,m,d);
            				//終了日が妥当かチェック
            				if(date.getFullYear() != y || date.getMonth() != m || date.getDate() != d){
            					alert(enddate_mes + "は「西暦/月/日」で設定してください。");
            					return false;
            				}
        				}
        			}
    			}
    		}
    	}
    }
}