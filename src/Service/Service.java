package Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Dao.CandidateDao;
import Exception.ExistsIDException;
import Exception.InvalidEmailException;
import Exception.PhoneNumberException;
import entity.Candidate;
import entity.Experience;
import entity.Fresher;
import entity.Intern;
import utils.ValidatorUtil;

public class Service {
	public List<Candidate> readFile() {
		List<Candidate> listCandidate = new ArrayList<Candidate>();
		String path = "E:\\nhat\\Thithu2\\src\\Book1.csv";
		String line;
		try (BufferedReader bff = new BufferedReader(new FileReader(path))) {
			while ((line = bff.readLine()) != null) {
				String[] data = line.split(",", 14);
				Candidate cand = null;

				if (!data[6].equals("")) {
					cand = new Experience();
					((Experience) cand).setExpInYear(Integer.parseInt(data[6]));
					((Experience) cand).setProSkill((data[7]));
				}

				if (!data[8].equals("")) {
					cand = new Fresher();
					((Fresher) cand).setGraduation_date(data[8]);
					((Fresher) cand).setGraduation_rank(data[9]);
					((Fresher) cand).setEducation(data[10]);
				}

				if (!data[11].equals("")) {
					cand = new Intern();
					((Intern) cand).setMajors(data[11]);
					((Intern) cand).setSemester(Integer.parseInt(data[12]));
					((Intern) cand).setUniversity_name(data[13]);
				}
				
				try {
					if (ValidatorUtil.checkExistsID(data[0], listCandidate)) {
						cand.setCandidateID(data[0]);
					}
				} catch (ExistsIDException e1) {
					e1.printStackTrace();
					continue;
				}
				
				cand.setFullName(data[1]);
				cand.setBirthDay(data[2]);
				
				try {
					if (ValidatorUtil.isPhoneNumber(data[3])) {
						cand.setPhone(data[3]);
					}
				} catch (PhoneNumberException e) {
					e.printStackTrace();
					continue;
				}
				
				try {
					if (ValidatorUtil.isEmailValid(data[4])) {
						cand.setEmail(data[4]);
					}
				} catch (InvalidEmailException e) {
					e.printStackTrace();
					continue;
				}
				cand.setCandidate_type(Integer.parseInt(data[5]));
				listCandidate.add(cand);
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listCandidate;
	}
}
