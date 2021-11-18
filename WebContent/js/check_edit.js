function onEditClick(){
    //入力項目を設定
	var title = edit_book.EDIT_TITLE.value;
	var publisher = edit_book.EDIT_PUBLISHER.value;
    var progress = edit_book.EDIT_PROGRESS.value;
    var publicationdate = edit_book.EDIT_PUBLICATIONDATE.value;
    var start = edit_book.EDIT_STARTDATE.value;
    var end = edit_book.EDIT_ENDDATE.value;
    var evaluation = edit_book.EDIT_EVALUATION.value;
    
    //アラートメッセージで出力するメッセージを設定
    var title_mes = 'タイトル';
    var publisher_mes = '出版社';
    var progress_mes = '読破率';
    var publicationdate_mes = '出版日';
    var startdate_mes = '開始日';
    var enddate_mes = '終了日';
    var evaluation_mes = '評価';

    // 読破率の最大値を設定
    var progress_max = 100;
    
    // 比較のために日付型に変換。
	var startdate = new Date(start);
	var enddate = new Date(end);
    
    //必須チェック
	if (title == "" && publisher == "" && publicationdate == ""){
		alert(title_mes + "、" + publisher_mes + "、" + publicationdate_mes + "は必須入力です。設定して下さい。");
		return false;
    }else if (title == "" && publisher == ""){
    	alert(title_mes + "、" + publisher_mes + "は必須入力です。設定して下さい。");
    	return false;
    }else if (title == "" && publicationdate == ""){
    	alert(title_mes + "、" + publicationdate_mes + "は必須入力です。設定して下さい。");
    	return false;
    }else if(publisher == "" && publicationdate == ""){
    	alert(publisher_mes + "、" + publicationdate_mes + "は必須入力です。設定して下さい。");
    	return false;
	}else if (title == ""){
        alert(title_mes + "は必須入力です。設定して下さい。");
        return false;
    }else if(publisher == ""){
    	alert(publisher_mes + "は必須入力です。設定して下さい。");
    	return false;
    }else if(publicationdate == ""){
    	alert(publicationdate_mes + "は必須入力です。設定して下さい。");
    	return false;  	
    }else{
    	//読破率の範囲チェック
    	if (progress != "" &&  progress > progress_max) {
    		alert(progress_mes + "は「0～100」の範囲で設定してください。");
    		return false;
    	}else{
    		// 出版日のフォーマットチェック（YYYY/MM/DD or YYYY/M/D を許可）
    		if (publicationdate != "" && !publicationdate.match(/^\d{4}\/\d{1,2}\/\d{1,2}$/)){
    			alert(publicationdate_mes + "は「西暦/月/日」で設定してください。");
    			return false;
    		}else if(publicationdate != ""){
    			// 「/」で分解して年、月、日に分ける
    			var y = publicationdate.split("/")[0];
    			var m = publicationdate.split("/")[1] - 1;
    			var d = publicationdate.split("/")[2];
    			// 日付を生成
    			var date = new Date(y,m,d);
    			// 出版日が妥当か年、月、日ごとにチェック（ありえない日付、うるう年もチェック）
    			if(date.getFullYear() != y || date.getMonth() != m || date.getDate() != d){
    				alert(publicationdate_mes + publicationdate + "は存在しません。設定し直して下さい。");
    				return false;
    			}
    		}
    		// 開始日と終了日の相関チェック
    		if (start == "" && end !== ""){
    			alert(enddate_mes + "が設定されている場合、" + startdate_mes + "も設定してください。" );
    			return false;
    		}else{
    			// 開始日のフォーマットチェック（YYYY/MM/DD or YYYY/M/D を許可）
    			if (start != "" && !start.match(/^\d{4}\/\d{1,2}\/\d{1,2}$/)){
    				alert(startdate_mes + "は「西暦/月/日」で設定してください。");
    				return false;
    			}else if(start != ""){
    				// 「/」で分解して年、月、日に分ける
    				var y = start.split("/")[0];
        			var m = start.split("/")[1] - 1;
        			var d = start.split("/")[2];
        			// 日付を生成
        			var date = new Date(y,m,d);
        			//開始日が妥当か年、月、日ごとにチェック（ありえない日付、うるう年もチェック）
        			if(date.getFullYear() != y || date.getMonth() != m || date.getDate() != d){
        				alert(startdate_mes + start + "は存在しません。設定し直して下さい。");
        				return false;
        			}else{
        				// 終了日のフォーマットチェック
        				if (end !== "" && !end.match(/^\d{4}\/\d{1,2}\/\d{1,2}$/)){
        					alert(enddate_mes + "は「西暦/月/日」で設定してください。");
        					return false;
        				}else if(end !== ""){
        					var y = end.split("/")[0];
        					var m = end.split("/")[1] - 1;
            				var d = end.split("/")[2];
            				var date = new Date(y,m,d);
            				//終了日が妥当か年、月、日ごとにチェック（ありえない日付、うるう年もチェック）
            				if(date.getFullYear() != y || date.getMonth() != m || date.getDate() != d){
            					alert(enddate_mes + end + "は存在しません。設定し直して下さい。");
            					return false;
            				}
        				}
        			}
    			}
    			// 終了日が開始日よりも未来になっていないことをチェック
    			if (startdate > enddate){
    				alert(startdate_mes + "は" + enddate_mes  + "と同日、または未来日以外は設定できません。設定し直して下さい。");
    				return false;
    			}
    		}
    	}
    }
}