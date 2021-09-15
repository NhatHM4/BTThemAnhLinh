package Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Fresher;
import utils.JdbcUtil;

public class FresherDao {
	public List<Fresher> select() {
		String sql = "select * from Candidate where Candidate_type = 1";
		ResultSet rs = JdbcUtil.excuteQuery(sql);
		try {
			return get(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private List<Fresher> get(ResultSet rs) throws SQLException {
		List<Fresher> listFresher = new ArrayList<Fresher>();
		while(rs.next()) {
			Fresher fr = new Fresher();
			fr.setCandidateID(rs.getString(1));
			fr.setFullName(rs.getString(2));
			fr.setBirthDay(rs.getString(3));
			fr.setPhone(rs.getString(4));
			fr.setEmail(rs.getString(5));
			fr.setCandidate_type(Integer.parseInt(rs.getString(6)));
			fr.setGraduation_date(rs.getString(9));
			fr.setGraduation_rank(rs.getString(10));
			fr.setEducation(rs.getString(11));
			listFresher.add(fr);
		}
		return listFresher;
	}
}
