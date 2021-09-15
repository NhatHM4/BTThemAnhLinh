package Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Experience;
import utils.JdbcUtil;

public class ExperienceDao {
	public List<Experience> select() {
		String sql = "select * from Candidate where Candidate_type = 0";
		ResultSet rs = JdbcUtil.excuteQuery(sql);
		try {
			return get(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private List<Experience> get(ResultSet rs) throws SQLException {
		List<Experience> listExp = new ArrayList<Experience>();
		while(rs.next()) {
			Experience exp = new Experience();
			exp.setCandidateID(rs.getString(1));
			exp.setFullName(rs.getString(2));
			exp.setBirthDay(rs.getString(3));
			exp.setPhone(rs.getString(4));
			exp.setEmail(rs.getString(5));
			exp.setCandidate_type(Integer.parseInt(rs.getString(6)));
			exp.setExpInYear(Integer.parseInt(rs.getString(7)));
			exp.setProSkill(rs.getString(8));
			listExp.add(exp);
		}
		return listExp;
	}
}
