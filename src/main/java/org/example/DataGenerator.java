package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DataGenerator {
    public static void main(String[] args) {
        int numberOfRecords = 30;

        StringBuilder sql = new StringBuilder();
//        sql.append("INSERT INTO board_article (board_id, article_title, article_cont, article_view, article_at, is_locked, is_deleted , member_id) values");
        sql.append("insert into member(user_id , user_pw , user_name , user_phonenum , user_regdate , user_addr , user_birthday , user_email , user_gender , user_role) values");


        for (int i = 0 , num = 1; i < numberOfRecords; i++ , num++) {

            sql.append("\n('admin" + num + "' , '123123' , '관리자99','01022225555' , '2023-1-1' , '관리자네집', '2023-1-1' ,'admin@admin.com', 'MALE' , 'ADMIN')");

//            sql.append("\n(1, '돌겠네~ 제목크기 때문에 돌겠어', '돌겠다고', 0, NOW(), FALSE, FALSE, 2),\n");
//            sql.append("(2, '어떤 제목을 정할까 고민인데', '내용은 대충 어쩌구저쩌구...', 0, NOW(), FALSE, FALSE, 2),\n");
//            sql.append("(3, '최초 로그인 이후 비밀번호 변경 바랍니다.', '분명 경고했음...', 0, NOW(), FALSE, FALSE, 1),\n");
//            sql.append("(4, '비대면 월급루팡 개꿀~', '너무 좋아', 0, NOW(), FALSE, FALSE, 3)");


            if (i < numberOfRecords - 1) {
                sql.append(", ");
            } else {
                sql.append(";");
            }
        }

        // SQL을 파일에 쓰기
        String fileName = "output.sql"; // 파일명을 지정하세요
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(sql.toString());
            System.out.println("SQL 쿼리가 " + fileName + " 파일에 성공적으로 저장되었습니다.");
        } catch (IOException e) {
            System.err.println("파일에 SQL 쿼리를 쓸 수 없습니다: " + e.getMessage());
        }
    }
}

