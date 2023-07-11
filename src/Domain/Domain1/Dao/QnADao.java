package src.Domain.Domain1.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import src.Domain.Domain1.Dto.QnADto;

public class QnADao {
	private String id;
	private String pw;
	private String url;
				
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static QnADao instance;
	public static QnADao getInstance() {
		if(instance==null)
			instance=new QnADao();
		return instance;
	}
	
	private QnADao(){
		id="root";
		pw="1234";
		url="jdbc:mysql://localhost:3306/musicdb";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(url,id,pw);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//CRUD
	public boolean insertPost(QnADto dto) {
		try {
		pstmt=conn.prepareStatement("insert into tbl_QnA values(null,?,curdate(),?,?)");
		pstmt.setString(1, dto.getId());
		pstmt.setString(2, dto.getTitle());
		pstmt.setString(3,dto.getContents());
		int result=pstmt.executeUpdate();
		pstmt.close();
		
		return result>0;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deletePost(int no) throws Exception {
	    pstmt = conn.prepareStatement("DELETE FROM tbl_QnA WHERE no = ?");
	    pstmt.setInt(1, no);
	    int result = pstmt.executeUpdate();
	    pstmt.close();

	    return result > 0;
	}
	
	public List<QnADto> select() throws Exception{
		List<QnADto> list = new ArrayList();
		QnADto dto=null;
		pstmt=conn.prepareStatement("select * from tbl_QnA");
		rs=pstmt.executeQuery();
		if(rs!=null)
		{
			while(rs.next()) {
				dto=new QnADto();
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setQnadate(rs.getDate("qnadate"));
				dto.setContents(rs.getString("contents"));
				dto.setTitle(rs.getString("title"));
				list.add(dto);
			}
			rs.close();
		}
		
		pstmt.close();
			
		return list;
	}
	
	
	
	
	public QnADto select(String title) throws Exception{
		
		QnADto dto=null;
		pstmt=conn.prepareStatement("select * from tbl_QnA where title=?");
		pstmt.setString(1, title);
		rs=pstmt.executeQuery();
		if(rs!=null && rs.isBeforeFirst())
		{
			rs.next();
			dto=new QnADto();			
			 dto.setNo(rs.getInt("no"));
	            dto.setId(rs.getString("id"));
	            dto.setQnadate(rs.getDate("qnadate"));
	            dto.setContents(rs.getString("contents"));
	            dto.setTitle(rs.getString("title"));
	            rs.close();
		}
		pstmt.close();
			
		return dto;
	}	
	
	public int updatePost(int no, QnADto dto) throws Exception{
		pstmt=conn.prepareStatement("update tbl_qna set no=?,qnadate=?,contents=?, title =? where id=?");
		pstmt.setInt(1, dto.getNo());
        pstmt.setDate(2, dto.getQnadate());
        pstmt.setString(3, dto.getContents());
        pstmt.setString(4, dto.getTitle());
        pstmt.setString(5, dto.getId());
        int result = pstmt.executeUpdate();
        pstmt.close();
		
		return result;
		
	}
	
	
	
	
	
	
}