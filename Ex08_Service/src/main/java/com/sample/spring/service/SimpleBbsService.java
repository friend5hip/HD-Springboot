package com.sample.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.spring.dao.ISimpleBbsDao;
import com.sample.spring.dto.SimpleBbsDto;

@Service
public class SimpleBbsService implements ISimpleBbsService {
	
	@Autowired
	ISimpleBbsDao dao;

	@Override
	public List<SimpleBbsDto> list() {
		// TODO Auto-generated method stub
		return dao.listDao();
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return dao.countDao();
	}

	@Override
	public SimpleBbsDto view(String id) {
		// TODO Auto-generated method stub
		return dao.viewDao(id);
	}

	@Override
	public int write(String writer, String title, String content) {
		// TODO Auto-generated method stub
		int nResult = dao.writeDao(writer, title, content);
		return nResult;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		int nResult = dao.deleteDao(id);
		return nResult;
	}

}
