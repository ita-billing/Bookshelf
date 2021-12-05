function onBookClick(){
	
	$('#book_form').validate({
	    //ルールの設定
	    rules: {
	    	TITLE: {
	            // 項目に値が設定されているか確認
	    		required: true
	        },
			PUBLISHER: {
				required: true
			},
			PROGRESS: {
				number :true,
				// 数字が0～100の範囲内
				range: [0, 100]
			},
			STARTDATE: {
				// 日付かどうかのチェック
				date :true
			},
			ENDDATE: {
				date :true
			},
			PUBLICATIONDATE: {
				required: true,
				date :true
			}
	    },
	    //エラーメッセージの設定
	    messages: {
	    	TITLE: {
	            required: 'タイトルは必須入力です。設定して下さい。'
	        },
	    	PUBLISHER: {
	    		required: '出版社は必須入力です。設定して下さい。'
	    	},
	    	STARTDATE: {
	    		date: '「西暦/月/日」（YYYY/MM/DD）で設定して下さい。'
	    	},
	    	ENDDATE: {
	    		date: '「西暦/月/日」（YYYY/MM/DD）で設定して下さい。'
	    	},
	    	PUBLICATIONDATE: {
	    		required: '出版日は必須入力です。設定して下さい。',
	    		date: '「西暦/月/日」（YYYY/MM/DD）で設定して下さい。'
	    	},
	    	PROGRESS: {
	    		range: '「0～100」の範囲で設定してください。'
	    	}
	    }    
	})
	
	
    // 日付の値を設定。
	var publication = book_form.PUBLICATIONDATE.value;
    var start = book_form.STARTDATE.value;
    var end = book_form.ENDDATE.value;
    
	// 比較のために日付型に変換。
    var publicationdate = new Date(publication);
    var startdate = new Date(start);
	var enddate = new Date(end);
	
    // アラートメッセージで出力するメッセージを設定
    var publicationdate_mes = '出版日';
    var startdate_mes = '開始日';
    var enddate_mes = '終了日';
    
	// 出版日のフォーマットチェック（YYYY/MM/DD or YYYY/M/D を許可）
    if(publication != ""){
		// 「/」で分解して年、月、日に分ける
		var y = publication.split("/")[0];
		var m = publication.split("/")[1] - 1;
		var d = publication.split("/")[2];
		// 日付を生成
		var date = new Date(y,m,d);
		// 出版日が妥当か年、月、日ごとにチェック（ありえない日付、うるう年もチェック）
		if(date.getFullYear() != y || date.getMonth() != m || date.getDate() != d){
			alert(publicationdate_mes + publication + "は存在しません。設定し直して下さい。");
			return false;
		}
	}
	// 開始日と終了日の相関チェック
	if (start == "" && end !== ""){
		alert(enddate_mes + "が設定されている場合、" + startdate_mes + "も設定してください。" );
		return false;
	}else{
		if(start != ""){
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
				if(end !== ""){
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
		// 出版日が開始日よりも過去になっていないことをチェック（終了日は開始日との相関チェックでクリア出来ているので不要）
		if (publicationdate > startdate){
			alert(publicationdate_mes + "は" + startdate_mes  + "と同日、または未来日以外は設定できません。設定し直して下さい。");
			return false;
		// 開始日が終了日よりも過去になっていないことをチェック
		}else if (startdate > enddate){
			alert(startdate_mes + "は" + enddate_mes  + "と同日、または未来日以外は設定できません。設定し直して下さい。");
			return false;
		}
	}
};

//半角数字のみ
jQuery.validator.addMethod(
		  "number",
		  function(val,elem){
		    reg = new RegExp("^[0-9]+$");
		    return this.optional(elem) || reg.test(val);
		  },
		  "設定できるのは、半角数字のみです。"
		);
