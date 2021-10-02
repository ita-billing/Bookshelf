package info.books;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import info.books.BookBeans;

public class Edit_book extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 文字コードの設定
        request.setCharacterEncoding("utf-8");

        // modeの取得
        String mode = request.getParameter("mode");

        // 実行ステータスの宣言
        String status = "";

        // JavaBeansの初期化
     	BookBeans books = new BookBeans(request);
        
     	switch (mode) {
     	
        // 登録する場合
            case "add":
            	// 失敗した場合
            	if (books.addData() == true) {
                    status = "登録に成功しました。";
                }else{
                	status = "登録に失敗しました。登録内容が正しいか、再確認してください。";
                }
                break;

            // 削除する場合
            case "delete":
                if (books.deleteData() == true) {
                	status = "削除に成功しました。";
                }else{
                	status = "削除に失敗しました。削除対象を再確認してください。";
                }
                break;

            
            // 編集画面に遷移
            case "change":
            	request.setAttribute("books", books);
            	request.getRequestDispatcher("/db_change.jsp").forward(request, response);
            	return;

            // 変更する場合
            case "update":
            	if (shain.updateData() == true) {
            		status = "更新に成功しました。";
            	}else{
            		status = "更新に失敗しました。更新内容が正しいか、再確認してください。";
            	}
            	break;
            }      	

            // statusをセットして、db_result.jspに転送
            request.setAttribute("status", status);
            request.getRequestDispatcher("/db_result.jsp").forward(request, response);
        }

        //doGetの追加
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
        		throws ServletException, IOException {
        	
            doPost(request, response);
        }

}
