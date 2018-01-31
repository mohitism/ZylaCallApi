package in.zylahealth.spring.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.text.html.FormSubmitEvent.MethodType;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.zylahealth.spring.constants.PhoneNumberDetails;
import in.zylahealth.spring.constants.PhoneNumberConstants;
import in.zylahealth.spring.exception.ZylaHealthFatalException;
import in.zylahealth.spring.exception.ZylaHealthNonFatalException;
import in.zylaheath.spring.entity.PhoneDirectoryEntity;
import in.zylaheath.spring.repository.PhoneDirectotyDAOService;

@RestController
public class PhoneAppController {
	
	@Autowired
	PhoneDirectotyDAOService phoneDirectoryDAOService;
	
	
	@RequestMapping(method=RequestMethod.POST,value="/getphonenumber", produces = {MediaType.APPLICATION_JSON_VALUE} )
	public PhoneNumberDetails getPhoneNumber(@RequestBody String apirequestBodyasJson) throws ParseException, ZylaHealthNonFatalException,ZylaHealthFatalException{
		JSONParser parser = new JSONParser();
		Object requestObj = parser.parse(apirequestBodyasJson);
		JSONObject headerObject = (JSONObject) ((JSONObject) requestObj).get("headers");
		JSONObject requestObject = (JSONObject) ((JSONObject) requestObj).get("request");
		
		String personName=null;
		
		if(requestObject.containsKey(PhoneNumberConstants.PERSON_NAME))
			personName = (String) requestObject.get(PhoneNumberConstants.PERSON_NAME);
		
		String preferredContact = null;
		if(requestObject.containsKey(PhoneNumberConstants.PREFFERED_CONTACT))
			preferredContact = (String) requestObject.get(PhoneNumberConstants.PREFFERED_CONTACT);
		
		if(personName==null || personName.isEmpty()) {
			throw new ZylaHealthNonFatalException("200","User Not present");
		}
		
		if(preferredContact != null && !preferredContact.isEmpty()) {
			if(phoneDirectoryDAOService.findByPhoneNumber(preferredContact)==null)
			{
				PhoneDirectoryEntity phoneNumberEntity = getPhoneDirectoryEntity(personName,preferredContact);
				phoneNumberEntity = phoneDirectoryDAOService.save(phoneNumberEntity);
				return new PhoneNumberDetails(personName,"India",preferredContact);
			}
			else {
				String contactString = generateRandomNumber();
				while(phoneDirectoryDAOService.findByPhoneNumber(contactString)!=null) {
					 contactString = generateRandomNumber();
				}
				PhoneDirectoryEntity phoneNumberEntity = getPhoneDirectoryEntity(personName,contactString);
				
				phoneNumberEntity = phoneDirectoryDAOService.save(phoneNumberEntity);
				return new PhoneNumberDetails(personName,"India",contactString);
				
			}
		}
		else
		{
			String contactString = generateRandomNumber();
			while(phoneDirectoryDAOService.findByPhoneNumber(contactString)!=null) {
				 contactString = generateRandomNumber();
			}
			PhoneDirectoryEntity phoneNumberEntity = getPhoneDirectoryEntity(personName,contactString);
			phoneNumberEntity = phoneDirectoryDAOService.save(phoneNumberEntity);
			return new PhoneNumberDetails(personName,"India",contactString);
		}
		
	}

	private String generateRandomNumber() {
		Long num = new RandomDataGenerator().nextLong(1111111111L, 9999999999L);;
		return num.toString();
	}

	private String AppendZeroestoContact(Long contact) {
		// TODO Auto-generated method stub
		
		String contactString = Long.toString(contact);
		
		StringBuilder sb = new StringBuilder();

		for (int toPrepend=10-contactString.length(); toPrepend>0; toPrepend--) {
		    sb.append('0');
		}

		sb.append(contactString);
		
		return sb.toString();
	}
	
	private PhoneDirectoryEntity getPhoneDirectoryEntity(String personName,String contactString) {
		PhoneDirectoryEntity phoneNumberEntity = new PhoneDirectoryEntity();
		phoneNumberEntity.setName(personName);
		phoneNumberEntity.setPhoneNumber(contactString);
		Date now = new Date();
		phoneNumberEntity.setStatus("ACTIVE");
		phoneNumberEntity.setStatusChangedOn(now);
		phoneNumberEntity.setStatusChangedRemarks(" ");// TODO

		phoneNumberEntity.setCreatedOn(new Date());
		phoneNumberEntity.setCreatedBy("");
		phoneNumberEntity.setUpdatedOn(new Date());
		phoneNumberEntity.setUpdatedBy("");
		return phoneNumberEntity;
	}

}
