package demo.sample.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;

import demo.sample.pojo.UserPOJO;

public class LoginDAO {

	@Autowired
	CassandraOperations cassandraOperations;
	
	public UserPOJO authenticateUser(String username) {
		return cassandraOperations.selectOneById(UserPOJO.class, username);
	}
	
}
