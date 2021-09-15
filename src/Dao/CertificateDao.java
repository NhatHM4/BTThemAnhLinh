package Dao;

import entity.Certificated;
import utils.JdbcUtil;

public class CertificateDao {
	public void insert(Certificated cer, String candidateID) {
		String sql = "insert into Certificated values(?,?,?,?,?)";
		JdbcUtil.excuteUpdate(sql, cer.getCertificatedID(),
								   cer.getCertificateName(),
								   cer.getCertificateRank(),
								   cer.getCertificatedDate(),
								   candidateID
				);
	}
	
	
}
