package Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Fresher;
import entity.Intern;
import utils.JdbcUtil;

public class InternDao {
	public List<Intern> select() {
		String sql = "select * from Candidate where Candidate_type = 2";
		ResultSet rs = JdbcUtil.excuteQuery(sql);
		try {
			return get(rs);
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private List<Intern> get(ResultSet rs) throws NumberFormatException, SQLException {
		List<Intern> listIntern = new ArrayList<Intern>();
		while(rs.next()) {
			Intern intern = new Intern();
			intern.setCandidateID(rs.getString(1));
			intern.setFullName(rs.getString(2));
			intern.setBirthDay(rs.getString(3));
			intern.setPhone(rs.getString(4));
			intern.setEmail(rs.getString(5));
			intern.setCandidate_type(Integer.parseInt(rs.getString(6)));
			intern.setMajors(rs.getString(12));
			intern.setSemester(Integer.parseInt(rs.getString(13)));
			intern.setUniversity_name(rs.getString(14));
			listIntern.add(intern);
		}
		return listIntern;
	}
}
