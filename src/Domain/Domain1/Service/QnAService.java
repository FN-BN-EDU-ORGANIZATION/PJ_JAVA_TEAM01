package src.Domain.Domain1.Service;

import java.util.ArrayList;
import java.util.List;

import src.Domain.Domain1.Dao.QnADao;


public class QnAService {
	
	private MemberService memService;
	private QnAService qnaService;
	private QnADao dao;
	
	public class PostService {
	    private List<String> postList;

	    public PostService() {
	        postList = new ArrayList<>();
	    }

	    public void createPost(String postContent) {
	        postList.add(postContent);
	        System.out.println("글 생성");
	    }

	    public void updatePost(String oldPostContent, String newPostContent) {
	    	int index = postList.indexOf(oldPostContent);
	    	if(index != -1) {
	    		postList.set(index, newPostContent);
	    		System.out.println("글 수정");
	    	}
	    	else {
	    		System.out.println("해당 글을 찾을 수 없습니다.");
	    	}
	    }

	    public void deletePost(String postContent) {
	        if(postList.contains(postContent)) {
	        	postList.remove(postContent);
	        	System.out.println("글 삭제");
	        }
	    	System.out.println("해당 글을 찾을 수 없습니다.");
	    }

	    public List<String> getPostList() {
	        return postList;
	    }
	}
	
	
	
	//싱글톤
	private static QnAService instance;
	public static QnAService getInstance() {
		if(instance==null)
			instance = new QnAService();
		return instance;
	}
	//
	
	private QnAService(){
		dao=QnADao.getInstance();
		qnaService = QnAService.getInstance();
	}
	
	//외부로부터 Service받기
	public void setMemberService(MemberService memService) {
		this.memService = memService;
	}
	
	public boolean memService(String sid, String id) throws Exception{
		String role1 = memService.getRole(sid);
		String role2 = memService.getRole(id);
		
		if (role1.equals("ROLE_MEMBER") || role2.equals("ROLE_ADMIN")) {
	        PostService postService = new PostService();  // PostService 인스턴스 생성
	        postService.createPost("글 생성");  // createPost() 메소드 호출
	        postService.updatePost("이전내용", "새로운 내용");
	        postService.deletePost("글 삭제");

	        return true;
		}else {
			System.out.println("관리자, 회원만 등록할 수 있습니다.");
			return false;
		}
		
	}
	


}
