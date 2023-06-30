package Domain.Domain1.Dto;


import java.util.Date;


public class QnADto {
	private int no;			//Auto_Increment
	private String id;			//userid
	private Date qnadate;	
	private String contents ;	
	private String title;
	//디폴트생성자
	
	public QnADto() {
		
	}
	public QnADto(int no, String id, Date qnadate, String contents, String title) {
		super();
		this.no = no;
		this.id = id;
		this.qnadate = qnadate;
		this.contents = contents;
		this.title = title;
	}
	@Override
	public String toString() {
		return "QnADto [no=" + no + ", id=" + id + ", qnadate=" + qnadate + ", contents=" + contents + ", title="
				+ title + "]";
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getQnadate() {
		return  qnadate;
	}
	public void setQnadate(Date qnadate) {
		this.qnadate = qnadate;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
	
	//인자받는 생성자
	//ToString
	//Getter and Setter
	
	
	
	
	
	
	
}
