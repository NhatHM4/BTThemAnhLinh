package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import Dao.CandidateDao;
import Dao.CertificateDao;
import Dao.ExperienceDao;
import Dao.FresherDao;
import Dao.InternDao;
import Exception.DateException;
import Exception.InvalidNumberException;
import Service.Service;
import entity.Candidate;
import entity.Certificated;
import entity.Experience;
import entity.Fresher;
import entity.Intern;
import utils.ValidatorUtil;

public class maintain {
	static Service listCandidate = new Service();
	static CandidateDao getDB = new CandidateDao();
	static CertificateDao cerDao = new CertificateDao();

	public static void main(String[] args) {

//		for (Candidate cand : listCandidate.readFile()) {
//			getDB.insert(cand);
//		}

//		for (Candidate cand : listCandidate.readFile()) {
//			cand.showMe();
////		}
//	List<Experience> listExp = new ExperienceDao().select();
//	List<Fresher> listFresher = new FresherDao().select();
//	List<Intern> listIntern = new InternDao().select();
//	List<Candidate> listCan = new ArrayList<Candidate>();
//	listCan.addAll(listExp);
//	listCan.addAll(listFresher);
//	listCan.addAll(listIntern);
//	
//	for (Candidate candidate : listCan) {
//		candidate.showMe();
//	}

//	Collections.sort(listCan, new sortByName());
//	
//	for (Candidate candidate : listCan) {
//		candidate.showMe();
//	}

		updateCandidate();
	}

	private static void updateCandidate() {
		Scanner sc = new Scanner(System.in);
		boolean err = true;
		do {
			System.out.println("Enter id");
			String id = sc.nextLine();
			if (checkContainID(id)) {
				cerDao.insert(createCertificated(sc), id);
				getDB.updateName(id, getDB.getName(id) + "1");
				err = false;
			} else {
				System.out.println("ID not exists");
				err = true;
			}
		} while (err);
	}

	private static Certificated createCertificated(Scanner sc) {
		Certificated cer = new Certificated();
		System.out.println("Enter Cer ID");
		String id = sc.nextLine();
		cer.setCertificatedID(id);
		System.out.println("Enter Cer Name");
		String name = sc.nextLine();
		cer.setCertificateName(name);
		System.out.println("Enter Cer rank");
		String rank = sc.nextLine();
		try {
			if (ValidatorUtil.isNumber(rank)) {
				cer.setCertificateRank(Integer.parseInt(rank));
			}
		} catch (NumberFormatException | InvalidNumberException e1) {
			e1.printStackTrace();
		}
		System.out.println("Enter Cer date");
		String date = sc.nextLine();
		try {
			if (ValidatorUtil.isValidEntryDate(date)) {
				cer.setCertificatedDate(date);
			}
		} catch (DateException e) {
			System.err.printf("%s is not match date%n", date);
			e.printStackTrace();
			System.exit(0);
		}
		return cer;
	}

	private static boolean checkContainID(String id) {
		for (String string : getDB.getID()) {
			if (string.equals(id)) {
				return true;
			}
		}
		return false;
	}

}

class sortByName implements Comparator<Candidate> {

	@Override
	public int compare(Candidate o1, Candidate o2) {
		if (o1.getFullName().compareTo(o2.getFullName()) != 0) {
			return o1.getFullName().compareTo(o2.getFullName());
		}
		return -Integer.compare(o1.getCandidate_type(), o2.getCandidate_type());

	}
}
