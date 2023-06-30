package src.Domain.Domain1.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import src.Domain.Domain1.Dto.MemberDto;

public class MemberDao {
	private String id;
	private String pw;
	private String url;
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static MemberDao instance;
	public static MemberDao getInstance() {
		if(instance == null)
			instance = new MemberDao();
		return instance;
	}
	private MemberDao(){
		id = "root";
		pw = "1234";
		url = "jdbc:mysql://localhost:3306/musicdb";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, id, pw);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public int insert(MemberDto dto) throws Exception {
		pstmt = conn.prepareStatement("insert into tbl_member values(?,?,?,?,?,?)");
		pstmt.setString(1, dto.getId());
		pstmt.setString(2, dto.getPw());
		pstmt.setString(3, dto.getName());
		pstmt.setString(4, dto.getAddr());
		pstmt.setString(5, dto.getPhone());
		pstmt.setString(6, dto.getRole());
		int result = pstmt.executeUpdate();
		pstmt.close();
		
		return result;
		
	}
	public List<MemberDto> select() throws Exception{
		List<MemberDto> list = new ArrayList();
		MemberDto dto = null;
		pstmt = conn.prepareStatement("select * from tbl_member");
		rs = pstmt.executeQuery();
		if(rs!=null) {
			while(rs.next()) {
				dto = new MemberDto();
				dto.setId(rs.getString("id"));
				dto.setPw(null);
				dto.setName(rs.getString("name"));
				dto.setAddr(rs.getString("addr"));
				dto.setPhone(rs.getString("phone"));
				dto.setRole(rs.getString("role"));
				list.add(dto);
			}
			rs.close();
		}
		pstmt.close();
		
		return list;
	}
	public MemberDto select(String id) throws Exception{
		MemberDto dto = null;
		pstmt = conn.prepareStatement("select * from tbl_member where id=?");
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		if(rs!=null && rs.isBeforeFirst())
		{
			rs.next();
			dto = new MemberDto();
			dto.setId(rs.getString("id"));
			dto.setPw(rs.getString("pw"));
			dto.setName(rs.getString("name"));
			dto.setAddr(rs.getString("addr"));
			dto.setPhone(rs.getString("phone"));
			dto.setRole(rs.getString("role"));
			rs.close();
		}
		pstmt.close();
		
		return dto;
	}
	public int update(MemberDto dto) throws Exception{
		pstmt = conn.prepareStatement("update tbl_member set pw=?,name=?,addr=?,phone=?,role=? where id=?");
		pstmt.setString(1, dto.getPw());
		pstmt.setString(2, dto.getName());
		pstmt.setString(3, dto.getAddr());
		pstmt.setString(4, dto.getPhone());
		pstmt.setString(5, dto.getRole());
		pstmt.setString(6, dto.getId());
		int result = pstmt.executeUpdate();
		pstmt.close();
		
		return result;
		
	}
	public int delete(String id) throws Exception{
		pstmt = conn.prepareStatement("delete from tbl_member where id=?");
		pstmt.setString(1, id);
		int result = pstmt.executeUpdate();
		pstmt.close();
		
		return result;
	}
}
