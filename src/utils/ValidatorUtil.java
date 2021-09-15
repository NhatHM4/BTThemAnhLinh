package utils;

import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

import Exception.DateException;
import Exception.ExistsIDException;
import Exception.InvalidEmailException;
import Exception.InvalidNumberException;
import Exception.PhoneNumberException;
import entity.Candidate;


public class ValidatorUtil {
	private static final String NUMBER_REGEX = "^[0-9]+$";
	
	public static boolean isValidEntryDate(String date) throws DateException {
		if (date != null && isValidDate(date)) {
			int day = Integer.parseInt(date.substring(6, 8));
			int month = Integer.parseInt(date.substring(4, 6));
			int year = Integer.parseInt(date.substring(0, 4));
			int currentDay = Calendar.getInstance().get(Calendar.DATE);
			int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
			int currentYear = Calendar.getInstance().get(Calendar.YEAR);
			if(year < currentYear) return true;
			if(year == currentYear && month < currentMonth) return true;
			if(year == currentYear && month == currentMonth && day <= currentDay) return true;
			throw new DateException("Invalid Date");
		}
		throw new DateException("Invalid Date");
	}

	public static boolean isNumeric(String number) {
		return number != null && number.matches(NUMBER_REGEX);
	}

	public static boolean isValidDate(String date) throws DateException {
		try {
			if(date.length() == 8) {
				int day = Integer.parseInt(date.substring(6, 8));
				int month = Integer.parseInt(date.substring(4, 6));
				int year = Integer.parseInt(date.substring(0, 4));
				if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
					if (day > 31)
						return false;
				}
				if (month == 4 || month == 6 || month == 9 || month == 11) {
					if (day > 30)
						return false;
				}
				if ((year % 400 == 0 || year % 4 == 0 && year % 100 != 0)) {
					if (month == 2 && day > 29)
						return false;
				} else if (month == 2 && day > 28) {
					return false;
				}
				
				return true;
			}
			throw new DateException("Invalid Date");
		} catch (Exception e) {
			throw new DateException("Invalid Date");
		}
	}
	
	
	public static boolean isNumber(String number) throws InvalidNumberException {
		Pattern p = Pattern.compile("^[0-9]+$");
		if (p.matcher(number).find()) {
			return true;
		}
		throw new InvalidNumberException(number + " is not number ");
	}

	public static boolean isPhoneNumber(String phone) throws PhoneNumberException {
		Pattern p = Pattern.compile("^(09|01[2|6|8|9])+([0-9]{8})$");
		if (p.matcher(phone).find()) {
			return true;
		}
		throw new PhoneNumberException(phone + " is not valid");
	}
	
	public static boolean isEmailValid(String email) throws InvalidEmailException {
		Pattern p = Pattern.compile("^[a-z][a-zA-Z0-9]+@[a-zA-Z0-9]+([.][a-zA-Z0-9]+)+$");
		if (p.matcher(email).find()) {
			return true;
		}
		throw new InvalidEmailException(email + " is not valid");
	}
	
	public static boolean checkExistsID(String id, List<Candidate> listCandidate) throws ExistsIDException {
		
		for (Candidate string : listCandidate) {
			if (string.getCandidateID().equals(id)) {
				throw new ExistsIDException("ID is Existed");
			}
		}
		return true;
	}
}
