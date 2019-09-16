package kr.or.ddit.prod.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.config.test.RootTestConfig;
import kr.or.ddit.prod.model.Prod;

public class ProdDaoTest extends RootTestConfig{
	
	@Resource(name="prodDao")
	private ProdDao prodDao;
	
	@Test
	public void testGetProdList() {
		/***Given***/
		String lprod_gu ="P101";
				
		/***When***/
		List<Prod> prodList = prodDao.getProdList(lprod_gu);

		/***Then***/
		assertEquals(6, prodList.size());
	}

}
