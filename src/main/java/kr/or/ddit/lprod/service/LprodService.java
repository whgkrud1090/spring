package kr.or.ddit.lprod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.lprod.dao.ILprodDao;
import kr.or.ddit.lprod.model.Lprod;

@Service
public class LprodService implements ILprodService{
	
	@Resource(name = "lprodDao")
	private ILprodDao lprodDao;
	
	public LprodService(ILprodDao lprodDao) {
		this.lprodDao = lprodDao;
	}

	public LprodService() {
	}

	@Override
	public List<Lprod> getLprodList() {
		List<Lprod> lprodList = lprodDao.getLprodList();
		return lprodList;
	}

	@Override
	public Map<String, Object> getLprodPagingList(Page page) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Lprod> lprodList = lprodDao.getLprodPagingList(page);
		int totalCnt = lprodDao.getLprodTotalCnt();
		
		map.put("lprodList", lprodList);
		map.put("totalCnt", (int)Math.ceil((double)totalCnt/page.getPagesize()));
		
		return map;
	}
}
