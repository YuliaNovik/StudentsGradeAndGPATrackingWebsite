package ca.sheridancollege.database;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.beans.Student;
import ca.sheridancollege.beans.User;

@Repository
public class DatabaseAccess {

	@Autowired
	protected NamedParameterJdbcTemplate jdbc;

	public User findUserAccount(String userName) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM sec_user where userName=:userName";
		parameters.addValue("userName", userName);
		ArrayList<User> users = (ArrayList<User>)jdbc.query(query, parameters,
				new BeanPropertyRowMapper<User>(User.class));
		if (users.size()>0)
			return users.get(0);
		return null;
	}

	public List<String> getRolesById(long userId) {
		ArrayList<String> roles = new ArrayList<String>();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT user_role.userId, sec_role.roleName "
				+ "FROM user_role, sec_role "
				+ "WHERE user_role.roleId=sec_role.roleId "
				+ "AND userId=:userId";
		parameters.addValue("userId", userId);
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		for (Map<String, Object> row : rows) {
			roles.add((String)row.get("roleName"));
		}
		return roles;		
	}

	@Autowired 
	private BCryptPasswordEncoder passwordEncoder;

	public void createNewUser(String username, String password) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "insert into SEC_User (userName, encryptedPassword, ENABLED) " +
				"values (:name, :pass, 1)";
		parameters.addValue("name", username);
		parameters.addValue("pass", passwordEncoder.encode(password));
		jdbc.update(query, parameters);

	}

	public void addRole(long userId, long roleId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "insert into user_role (userId, roleId) values (:userId, :roleId)";
		parameters.addValue("userId", userId);
		parameters.addValue("roleId", roleId);
		jdbc.update(query, parameters);
	}

	public void addStudent(Student student) {

		MapSqlParameterSource parameters = new MapSqlParameterSource();
       
		
		//calculate student average
		double wAvg=(student.getExs()*0.1)+(student.getAssign1()*0.06)
				+(student.getAssign2()*0.12)+(student.getAssign3()*0.12)
				+(student.getMidterm()*0.3)+(student.getFinalEx()*0.3);	
		System.out.println(wAvg);


     //define a student grade
		String tempGrade="N/A";
		if (wAvg >= 90) {
			tempGrade="A+";
		}
		else if (wAvg >= 80) {
			tempGrade="A";
		}
		else if (wAvg >= 75) {
			tempGrade="B+";
		}
		else if (wAvg >= 70) {
			tempGrade="B";
		}
		else if (wAvg >= 65) {
			tempGrade="C+";
		}
		else if (wAvg >= 60) {
			tempGrade="C";
		}
		else if (wAvg >= 50) {
			tempGrade="D";
		}
		else if (wAvg < 50) {
			tempGrade="F";
		}
		student.setGrade(tempGrade);


		String query = "INSERT INTO STUDENT  (name, stId, exs, assign1, assign2, assign3, midterm, finalEx, wA, grade)"
				+ " VALUES (:name, :stId, :exs, :assign1, :assign2, :assign3, :midterm, :finalEx,"
				+ " :wA+(:exs*0.1)+(:assign1*0.06)+(:assign2*0.12)+(:assign3*0.12)+(:midterm*0.3)+(:finalEx*0.3), :grade)";
		parameters.addValue("name", student.getName());
		parameters.addValue("stId", student.getStId());
		parameters.addValue("exs", student.getExs());
		parameters.addValue("assign1", student.getAssign1());
		parameters.addValue("assign2", student.getAssign2());
		parameters.addValue("assign3", student.getAssign3());
		parameters.addValue("midterm", student.getMidterm());
		parameters.addValue("finalEx", student.getFinalEx());
		parameters.addValue("wA", student.getWA());
		parameters.addValue("grade", student.getGrade());

		// String classAvg =  "SELECT AVG(exs) FROM STUDENT";

		jdbc.update(query, parameters);
	}

	public  ArrayList<Student> getStudent(){
		ArrayList<Student> students = new ArrayList<Student>();
		String query = "SELECT * FROM STUDENT";
		List<Map<String, Object>> rows = jdbc.queryForList(query, new HashMap<String, Object>());
		for(Map<String, Object> row: rows) {
			Student s = new Student();
			s.setId((Integer)row.get("id"));
			s.setName((String)row.get("name")); 
			s.setStId((String)row.get("stId"));
			s.setExs(((BigDecimal)(row.get("exs"))).doubleValue());
			s.setAssign1(((BigDecimal)(row.get("assign1"))).doubleValue());
			s.setAssign2(((BigDecimal)(row.get("assign2"))).doubleValue());
			s.setAssign3(((BigDecimal)(row.get("assign3"))).doubleValue());
			s.setMidterm(((BigDecimal)(row.get("midterm"))).doubleValue());
			s.setFinalEx(((BigDecimal)(row.get("finalEx"))).doubleValue());			 
			s.setWA(((BigDecimal)(row.get("wA"))).doubleValue());
			s.setGrade((String)row.get("grade"));

			students.add(s);
		}
		return students;
	}

	public void deleteStudent(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "DELETE FROM STUDENT WHERE id=:id";
		parameters.addValue("id", id);
		jdbc.update(query, parameters);
	}



	

	public  Student getStudentById(int id){
		ArrayList<Student> st = new ArrayList<Student>();
		String query = "SELECT * FROM STUDENT WHERE id=:id";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", id);

		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		for(Map<String, Object> row: rows) {
			Student s = new Student();
			s.setId((Integer)row.get("id"));
			s.setName((String)row.get("name")); 
			s.setStId((String)row.get("stId"));
			s.setExs(((BigDecimal)(row.get("exs"))).doubleValue());
			s.setAssign1(((BigDecimal)(row.get("assign1"))).doubleValue());
			s.setAssign2(((BigDecimal)(row.get("assign2"))).doubleValue());
			s.setAssign3(((BigDecimal)(row.get("assign3"))).doubleValue());
			s.setMidterm(((BigDecimal)(row.get("midterm"))).doubleValue());
			s.setFinalEx(((BigDecimal)(row.get("finalEx"))).doubleValue());
			s.setWA(((BigDecimal)(row.get("wA"))).doubleValue());
			s.setGrade((String)row.get("grade"));

			st.add(s);
		}
		if (st.size()>0)
			return st.get(0);
		return null;
	}

	public void editStudent(Student st) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		double wAvg=(st.getExs()*0.1)+(st.getAssign1()*0.06)
				+(st.getAssign2()*0.12)+(st.getAssign3()*0.12)
				+(st.getMidterm()*0.3)+(st.getFinalEx()*0.3) - st.getWA();
		System.out.println(wAvg);
		String tempGrade="N/A";
		if (wAvg >= 90) {
			tempGrade="A+";
		}
		else if (wAvg >= 80) {
			tempGrade="A";
		}
		else if (wAvg >= 75) {
			tempGrade="B+";
		}
		else if (wAvg >= 70) {
			tempGrade="B";
		}
		else if (wAvg >= 65) {
			tempGrade="C+";
		}
		else if (wAvg >= 60) {
			tempGrade="C";
		}
		else if (wAvg >= 50) {
			tempGrade="D";
		}
		else if (wAvg < 50) {
			tempGrade="F";
		}
		st.setGrade(tempGrade);


		String query = "UPDATE STUDENT SET "
				+ "name=:name, stId=:stId, exs=:exs, assign1=:assign1, assign2=:assign2, assign3=:assign3, "
				+ "midterm=:midterm, finalEx=:finalEx,"
				+ " wA=:wA+(:exs*0.1)+(:assign1*0.06)+(:assign2*0.12)+(:assign3*0.12)+(:midterm*0.3)+(:finalEx*0.3), "
				+ "grade=:grade"			
				+ " WHERE id=:id";
		parameters.addValue("id", st.getId());
		parameters.addValue("name", st.getName());
		parameters.addValue("stId", st.getStId());
		parameters.addValue("exs", st.getExs());
		parameters.addValue("assign1", st.getAssign1());
		parameters.addValue("assign2", st.getAssign2());
		parameters.addValue("assign3", st.getAssign3());
		parameters.addValue("midterm", st.getMidterm());
		parameters.addValue("finalEx", st.getFinalEx());
		parameters.addValue("wA", st.getWA());
		parameters.addValue("grade", st.getGrade());

		jdbc.update(query, parameters);
	}



}
