package com.service;

import com.model.RequestUser;
import com.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {

	@Autowired
	private RequestRepository rr;

	public List<RequestUser> findAll() {
		return rr.findAll();
	}

	public RequestUser save(RequestUser user) {
		return rr.save(user);
	}

	public void delete(RequestUser user) {
		rr.delete(user);
	}

}
