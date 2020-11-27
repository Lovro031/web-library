package interview.library.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import interview.library.model.PersonIdInfo;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Base64;

public class MicroblinkAPI {

	//TODO: store in a property file
	private static final String API_SECRET = "da4c6eee-3921-4d51-b46d-22d64c49293f";
	private static final String API_KEY = "3e11434b1b404b30bf9ce38bc3badaa2";
	private static final String DOMAIN = "https://api.microblink.com";
	private static final String END_POINT = "/v1/recognizers/mrtd";
	private static final String AUTHORIZATION = "Bearer " + new String(Base64.getEncoder().encode((API_KEY + ":" + API_SECRET).getBytes()));

	//"https://mup.gov.hr/UserDocsImages/topvijesti/2015/ozujak/SeOI-poledjina.jpg"

    public static String getRawMrzDataFromImage(String imageSource) {
		JsonObject json = new JsonObject();
		json.addProperty("imageSource", imageSource);
		WebClient client = WebClient.create(DOMAIN);
		String response = client.post()
				.uri(END_POINT)
				.header("Authorization",AUTHORIZATION)
				.header("Content-Type", "application/json")
				.body(Mono.just(json.toString()), String.class)
				.retrieve()
				.bodyToMono(String.class)
				.block();

		JsonObject obj = new JsonParser().parse(response).getAsJsonObject();
		obj = obj.getAsJsonObject("result").getAsJsonObject("mrzData");
		String rawMrzData = obj.get("rawMrzString").getAsString();

		return rawMrzData;
    }

    public static PersonIdInfo parseRawMrzData(String rawMrzData) {
    	PersonIdInfo personIdInfo = new PersonIdInfo();
    	String[] rows = rawMrzData.split("\\r?\\n");

    	personIdInfo = parseFirstRow(personIdInfo, rows[0]);
    	personIdInfo = parseSecondRow(personIdInfo, rows[1]);
    	personIdInfo = parseThirdRow(personIdInfo, rows[2]);

    	return personIdInfo;
	}

	private static PersonIdInfo parseFirstRow(PersonIdInfo personIdInfo, String firstRow) {
    	int i = 0;
		StringBuilder sb = new StringBuilder();

		//document type
		while(i < 2) {
			if(firstRow.charAt(i) == '<') {
				i++;
				break;
			}
			sb.append(firstRow.charAt(i));
			i++;
		}
		String documentType = sb.toString();
		personIdInfo.setDocumentType(documentType);

		//issuing state
		sb.setLength(0);
		while(i < 5) {
			sb.append(firstRow.charAt(i));
			i++;
		}
		String issuingState = sb.toString();
		personIdInfo.setIssuingState(issuingState);

		//documentNumber
		sb.setLength(0);
		while(i < firstRow.length()) {
			if (firstRow.charAt(i) == '<') break;
			sb.append(firstRow.charAt(i));
			i++;
		}
		String combined = sb.toString();
		String documentNumber = combined.substring(0, combined.length()-1);
		boolean isValidDocumentNumber = checkDigits(documentNumber, combined.charAt(documentNumber.length()));

		personIdInfo.setDocumentNumber(documentNumber);
		personIdInfo.setIsValidDocumentNumber(isValidDocumentNumber);

		return personIdInfo;
	}

	private static PersonIdInfo parseSecondRow(PersonIdInfo personIdInfo, String secondRow) {
		int i = 0;
		StringBuilder sb = new StringBuilder();

		//date of birth
		while(i < 6) {
			sb.append(secondRow.charAt(i));
			i++;
		}
		String dateOfBirth = sb.toString();
		char checkDigit = secondRow.charAt(i++);
		boolean isValidDateOfBirth = checkDigits(dateOfBirth, checkDigit);

		personIdInfo.setDateOfBirth(dateOfBirth);
		personIdInfo.setIsValidDateOfBirth(isValidDateOfBirth);

		//sex
		String sex = String.valueOf(secondRow.charAt(i++));
		personIdInfo.setSex(sex);

		//date of expiry
		sb.setLength(0);
		while(i < 14) {
			sb.append(secondRow.charAt(i));
			i++;
		}
		String dateOfExpiry = sb.toString();
		checkDigit = secondRow.charAt(i++);
		boolean isValidDateOfExpiry = checkDigits(dateOfExpiry, checkDigit);

		personIdInfo.setDateOfExpiry(dateOfExpiry);
		personIdInfo.setIsValidDateOfExpiry(isValidDateOfExpiry);

		//nationality
		sb.setLength(0);
		while(i < 18) {
			sb.append(secondRow.charAt(i));
			i++;
		}
		String nationality = sb.toString();

		personIdInfo.setNationality(nationality);

		//check digit first two lines
		checkDigit = secondRow.charAt(secondRow.length()-1);
		boolean isValidFirstTwoRows = checkDigits(personIdInfo.getDocumentNumber() + dateOfBirth + dateOfExpiry, checkDigit);

		personIdInfo.setIsValidFirstTwoLines(isValidFirstTwoRows);

		return personIdInfo;
	}

	private static PersonIdInfo parseThirdRow(PersonIdInfo personIdInfo, String thirdRow) {
		int i = 0;
		StringBuilder sb = new StringBuilder();

		//primary identifier
		while(!(thirdRow.charAt(i) == '<' && thirdRow.charAt(i+1) == '<')) {
			if(thirdRow.charAt(i) == '<')
				sb.append(" ");
			else
				sb.append(thirdRow.charAt(i));
			i++;
		}
		i+=2;
		String primaryIdentifier = sb.toString();

		personIdInfo.setPrimaryIdentifier(primaryIdentifier);

		//secondary identifier
		sb.setLength(0);
		while(i < thirdRow.length()) {
			if(thirdRow.charAt(i) == '<')
				sb.append(" ");
			else
				sb.append(thirdRow.charAt(i));
			i++;
		}
		String secondaryIdentifier = sb.toString().trim();

		personIdInfo.setSecondaryIdentifier(secondaryIdentifier);

		return personIdInfo;
	}

	private static boolean checkDigits(String string, char checkDigit) {
		int[] weights = {7,3,1};
		int alphabetical_offset = 10;
		int sum = 0;
		for(int i = 0; i < string.length(); i++) {
			char digit = string.charAt(i);
			if(digit == '<') continue;

			int value;
			if(Character.isDigit(digit)) {
				value = ((int) digit) - ((int) '0');
			} else {
				value = ((int) digit) - ((int) 'A') + alphabetical_offset;
			}

			sum += (value * weights[i % weights.length]);
		}

		return sum % 10 == (checkDigit - (int)'0') ? true : false;
	}


}
