package kr.or.ddit.test.board.service;

import kr.or.ddit.test.board.dao.IBoardDao;

public interface IBoardService {
	/**
	* Method : getBoardList
	* 작성자 : PC-21
	* 변경이력 :
	* Method 설명 : 게시물 리스트 조회
	*/
	void getBoardList();
	
	void setBoardDao(IBoardDao boardDao);
	
	void setBoardNm(String boardNm);
}
