package src.Domain.Domain1.Service;


import java.util.ArrayList;
import java.util.List;

import src.Domain.Domain1.Dao.MemberDao;
import src.Domain.Domain1.Dao.MemberDaoImpl;
import src.Domain.Domain1.Dao.QnADao;
import src.Domain.Domain1.Dto.QnADto;
import src.ETC.BulletinBoardGUI;

public class QnAService {
	private static QnAService instance;
    private MemberService memService;
    private QnADao dao;
    private BulletinBoardGUI bulletinBoardGUI;
    public PostService postService;

    private QnAService() {
        dao = QnADao.getInstance();
        memService = MemberServiceImpl.getInstance();
        MemberDao memDao = MemberDaoImpl.getInstance();
        bulletinBoardGUI = new BulletinBoardGUI();
        postService = new PostService();
        
        bulletinBoardGUI.setPostService(postService); // GUI와 PostService 연결
        bulletinBoardGUI.addActionListener(postService); // GUI의 ActionListener에 PostService 연결
    }

    public static synchronized QnAService getInstance() {
        if (instance == null) {
            instance = new QnAService();
        }
        return instance;
    }

    public void setMemberService(MemberService memService) {
        this.memService = memService;
    }

    public void setQnAUI(BulletinBoardGUI qnaUI) {
        this.bulletinBoardGUI = qnaUI;
        bulletinBoardGUI.addActionListener(postService);
    }

    public boolean memService(String sid, String id) throws Exception {
        String role1 = memService.getRole(sid);
        String role2 = memService.getRole(id);

        if (role1.equals("ROLE_MEMBER") || role2.equals("ROLE_ADMIN")) {
            // 게시물 서비스 인스턴스 생성
        	

           
			// 게시물 생성, 수정, 삭제 메서드 호출
            QnADto postContent = new QnADto();
            postContent.setNo(1);  // 게시물 번호 설정
            postContent.setTitle("제목");  // 게시물 제목 설정
            postContent.setContents("내용");  // 게시물 내용 설정

            // 게시물 생성 메서드 호출
            boolean success = postService.createPost(postContent);

            return true;
        } else {
            System.out.println("관리자, 회원만 등록할 수 있습니다.");
            return false;
        }
    }

    public class PostService {
        private List<QnADto> postList;
        private BulletinBoardGUI bulletinBoardGUI;

        public PostService() {
            postList = new ArrayList<>();
        }

        public Boolean createPost(QnADto dto) {
            if (dto != null) {
                postList.add(dto);
                System.out.println("글 생성");
                return true;
            }
            return false;
        }
        public boolean updatePost(int no, QnADto updatedDto) {
            // 지정된 번호 (no)를 가진 게시물 찾기
            for (QnADto dto : postList) {
                if (dto.getNo() == no) {
                    // 게시물 내용 업데이트
                    dto.setContents(updatedDto.getContents());
                    dto.setTitle(updatedDto.getTitle());
                    System.out.println("글 수정");
                    return true; // 수정 성공
                }
            }

            System.out.println("해당 글을 찾을 수 없습니다.");
            return false; // 게시물을 찾지 못함
        }

        public boolean deletePost(int no) {
            for (QnADto dto : postList) {
                if (dto.getNo() == no) {
                    postList.remove(dto);
                    System.out.println("글 삭제");
                    return true;
                }
            }

            System.out.println("해당 글을 찾을 수 없습니다.");
            return false;
        }

        public List<QnADto> getPostList() {
            return postList;
        }

        public void setQnAUI(BulletinBoardGUI bulletinBoardGUI) {
            this.bulletinBoardGUI = bulletinBoardGUI;
        }
    }

	

    
}