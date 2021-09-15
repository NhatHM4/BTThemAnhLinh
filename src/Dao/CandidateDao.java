package Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Candidate;
import entity.Experience;
import entity.Fresher;
import entity.Intern;
import utils.JdbcUtil;

public class CandidateDao {
	public void insert(Candidate cand) {
		if (cand instanceof Experience) {
			String sql = "insert into Candidate(CandidateID,FullName,BirthDay,Phone,Email,Candidate_type,ExpInYear,ProSkill) values(?,?,?,?,?,?,?,?)";
			JdbcUtil.excuteUpdate(sql,	cand.getCandidateID(),
										cand.getFullName(),
										cand.getBirthDay(),
										cand.getPhone(),
										cand.getEmail(),
										cand.getCandidate_type(),
										((Experience) cand).getExpInYear(),
										((Experience) cand).getProSkill()
					);
		}
		
		if (cand instanceof Fresher) {
			String sql = "insert into Candidate(CandidateID,FullName,BirthDay,Phone,Email,Candidate_type,Graduation_date,Graduation_rank,Education) values(?,?,?,?,?,?,?,?,?)";
			JdbcUtil.excuteUpdate(sql,	cand.getCandidateID(),
										cand.getFullName(),
										cand.getBirthDay(),
										cand.getPhone(),
										cand.getEmail(),
										cand.getCandidate_type(),
										((Fresher) cand).getGraduation_date(),
										((Fresher) cand).getGraduation_rank(),
										((Fresher) cand).getEducation()
					);
		}
		
		if (cand instanceof Intern) {
				String sql = "insert into Candidate(CandidateID,FullName,BirthDay,Phone,Email,Candidate_type,Majors,Semester,University_name) values(?,?,?,?,?,?,?,?,?)";
				JdbcUtil.excuteUpdate(sql,	cand.getCandidateID(),
											cand.getFullName(),
											cand.getBirthDay(),
											cand.getPhone(),
											cand.getEmail(),
											cand.getCandidate_type(),
											((Intern) cand).getMajors(),
											((Intern) cand).getSemester(),
											((Intern) cand).getUniversity_name()
						);
			
		}
	}
	
	public void update(String id, String birthday) {
		String sql = "update Candidate set birthday = ? where candidateid = ?";
		JdbcUtil.excuteUpdate(sql, birthday, id );
	}
	
	public List<String> getID(){
		String sql = "select Candidateid from Candidate";
		ResultSet rs = JdbcUtil.excuteQuery(sql);
		try {
			return get(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private List<String> get(ResultSet rs) throws SQLException {
		List<String> listID = new ArrayList<String>();
		while(rs.next()) {
			listID.add(rs.getString(1));
		}
		return listID;
	}
	
	public void updateName(String id, String name) {
		String sql = "update Candidate set Fullname = ? where CandidateID = ?";
		JdbcUtil.excuteUpdate(sql, name,id);
		
	}
	
	public String getName(String id) {
		String sql = "select Fullname from Candidate where Candidateid = ?";
		ResultSet rs = JdbcUtil.excuteQuery(sql, id);
		try {
			return getName(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String getName(ResultSet rs) throws SQLException {
		while(rs.next()) {
			return rs.getString("Fullname");
		}
		return null;
	}
}
