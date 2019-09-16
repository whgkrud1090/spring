package kr.or.ddit.lprod.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.config.test.RootTestConfig;
import kr.or.ddit.lprod.dao.ILprodDao;
import kr.or.ddit.lprod.dao.LprodDao;
import kr.or.ddit.lprod.model.Lprod;

public class LprodDaoTest extends RootTestConfig{
	
	@Resource(name="lprodDao")
	private LprodDao lprodDao;
	

	@Test
	public void getLpordListTest() {
		/***Given***/
		/***When***/
		List<Lprod> lprodList = lprodDao.getLprodList();

		/***Then***/
		assertEquals(10, lprodList.size());
	}
	
	public void getLprodPagingListTest() {
		/***Given***/
		Page page = new Page();
		page.setPage(1);
		page.setPagesize(5);

		/***When***/
		List<Lprod> lprodList = lprodDao.getLprodPagingList(page);
		
		/***Then***/
		
		assertEquals(5, lprodList.size());
	}
	
	public void getLprodTotalCnt() {
		/***Given***/
		ILprodDao lprodDao = new LprodDao();

		/***When***/
		int totalCnt = lprodDao.getLprodTotalCnt();

		/***Then***/
		assertEquals(10, totalCnt);
	}
}