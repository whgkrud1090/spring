package kr.or.ddit.lprod.dao;

import java.util.List;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.lprod.model.Lprod;

public interface ILprodDao {

	/**
	* Method : getLprodList
	* 작성자 : SEM-PC
	* 변경이력 :
	* @return
	* Method 설명 : 제품 그룹 리스트 조회
	*/
	List<Lprod> getLprodList();

	/**
	* Method : getLprodPagingList
	* 작성자 : PC-03
	* 변경이력 :
	* @param sqlSession
	* @param page
	* @return
	* Method 설명 : 제품그룹 페이징 리스트 조회
	 */
	List<Lprod> getLprodPagingList(Page page);
	
	/**
	* Method : getLprodTotalCnt
	* 작성자 : PC-03
	* 변경이력 :
	* @return
	* Method 설명 : 전체 제품그룹 수 조회
	 */
	int getLprodTotalCnt();
}
