package in.zylahealth.spring.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.PostConstruct;
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
import in.zylahealth.spring.constants.PhoneNumberStatusEnum;
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
			
			PhoneDirectoryEntity preferredPhoneNumberEntity = this.phoneDirectoryDAOService.findByPhoneNumber(preferredContact);
			if(preferredPhoneNumberEntity!=null) {
				if(preferredPhoneNumberEntity.getStatus() != PhoneNumberStatusEnum.ACTIVE.getValue())
				{
					preferredPhoneNumberEntity.setStatus(PhoneNumberStatusEnum.ACTIVE.getValue());
					this.phoneDirectoryDAOService.save(preferredPhoneNumberEntity);
					return new PhoneNumberDetails(personName,"India",preferredContact);
					}
				else {
		
					PhoneDirectoryEntity inactivePhoneNumberEntity = this.phoneDirectoryDAOService.findInactivePhoneNumber();
					
					if(inactivePhoneNumberEntity!=null) {
						
						inactivePhoneNumberEntity.setStatus(PhoneNumberStatusEnum.ACTIVE.getValue());
						this.phoneDirectoryDAOService.save(inactivePhoneNumberEntity);
						return new PhoneNumberDetails(personName,"India",inactivePhoneNumberEntity.getPhoneNumber());
					}else {
						throw new ZylaHealthFatalException("200","Phone Numbers List Exhausted");
					}
					
				}
			}else {
				PhoneDirectoryEntity inactivePhoneNumberEntity = this.phoneDirectoryDAOService.findInactivePhoneNumber();
				
				if(inactivePhoneNumberEntity!=null) {
					
					inactivePhoneNumberEntity.setStatus(PhoneNumberStatusEnum.ACTIVE.getValue());
					this.phoneDirectoryDAOService.save(inactivePhoneNumberEntity);
					return new PhoneNumberDetails(personName,"India",inactivePhoneNumberEntity.getPhoneNumber());
				}else {
					throw new ZylaHealthFatalException("200","Phone Numbers List Exhausted");
				}
			}
		}
		else
		{
			PhoneDirectoryEntity inactivePhoneNumberEntity = this.phoneDirectoryDAOService.findInactivePhoneNumber();
			
			if(inactivePhoneNumberEntity!=null) {
				
				inactivePhoneNumberEntity.setStatus(PhoneNumberStatusEnum.ACTIVE.getValue());
				this.phoneDirectoryDAOService.save(inactivePhoneNumberEntity);
				return new PhoneNumberDetails(personName,"India",inactivePhoneNumberEntity.getPhoneNumber());
			}else {
				throw new ZylaHealthFatalException("200","Phone Numbers List Exhausted");
			}
		}
		
	}

/*	private String generateRandomNumber() {
		number++;
		return String.valueOf(number);
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
	
	private PhoneDirectoryEntity getPhoneDirectoryEntity(String contactString) {
		PhoneDirectoryEntity phoneNumberEntity = new PhoneDirectoryEntity();
		phoneNumberEntity.setPhoneNumber(contactString);
		Date now = new Date();
		phoneNumberEntity.setStatus(PhoneNumberStatusEnum.ACTIVE.getValue());
		phoneNumberEntity.setStatusChangedOn(now);
		phoneNumberEntity.setStatusChangedRemarks(" ");// TODO

		phoneNumberEntity.setCreatedOn(new Date());
		phoneNumberEntity.setCreatedBy("");
		phoneNumberEntity.setUpdatedOn(new Date());
		phoneNumberEntity.setUpdatedBy("");
		return phoneNumberEntity;
	}
	*/

}
