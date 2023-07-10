package src.Domain.Domain1.Dao;

import java.util.List;

import src.Domain.Domain1.Dto.MemberDto;

public interface MemberDao {

	int insert(MemberDto dto) throws Exception;

	List<MemberDto> select() throws Exception;

	MemberDto select(String id) throws Exception;

	int update(MemberDto dto) throws Exception;

	int delete(String id) throws Exception;

}