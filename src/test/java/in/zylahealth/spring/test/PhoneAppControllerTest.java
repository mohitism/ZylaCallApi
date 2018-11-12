package in.zylahealth.spring.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.PropertySource;

import in.zylahealth.spring.constants.PhoneNumberDetails;
import in.zylahealth.spring.exception.ZylaHealthFatalException;
import in.zylahealth.spring.exception.ZylaHealthNonFatalException;
import in.zylaheath.spring.entity.PhoneDirectoryEntity;
import in.zylaheath.spring.repository.PhoneDirectotyDAOService;


@PropertySource("classpath:application.properties")
public class PhoneAppControllerTest {
	
	@Mock
	private PhoneDirectotyDAOService phoneDirectotyDAOService;
	
	@InjectMocks
	private in.zylahealth.spring.controller.PhoneAppController phoneAppController = new in.zylahealth.spring.controller.PhoneAppController();


	@Before
	public void setUpBeforeClass()  {
		MockitoAnnotations.initMocks(this);
		
	}

	@Test
	public void testWhenContactIsPreferred() throws ParseException, ZylaHealthFatalException {
		String phoneNumber = null;
		PhoneDirectoryEntity phone = new PhoneDirectoryEntity();
		PhoneDirectoryEntity phoneDirectoryEntity = new PhoneDirectoryEntity();
		phoneDirectoryEntity.setPhoneNumber("8674806691");
		when(phoneDirectotyDAOService.findByPhoneNumber(phoneNumber)).thenReturn(phoneDirectoryEntity);
		when(phoneDirectotyDAOService.save(phone)).thenReturn(phoneDirectoryEntity);

		String json = "{ \"headers\": {},\"request\":{ \"person_name\":\"mohit\",\"preferred_contact\" : \"8674806691\" }}";
		try {
			assertEquals(phoneAppController.getPhoneNumber(json).getPhonenumber(),"8674806691");
		} catch (ZylaHealthNonFatalException e) {
			// TODO Auto-generated catch block
			assertEquals("200", e.getErrorCode());
		}
	}
	
	
	@Test
	public void testWhenContactIsNotPreferred() throws ParseException, ZylaHealthFatalException {
		String phoneNumber = null;
		PhoneDirectoryEntity phone = new PhoneDirectoryEntity();
		PhoneDirectoryEntity phoneDirectoryEntity = new PhoneDirectoryEntity();
		phoneDirectoryEntity.setPhoneNumber("3445567867");
		when(phoneDirectotyDAOService.findByPhoneNumber(phoneNumber)).thenReturn(phoneDirectoryEntity);
		when(phoneDirectotyDAOService.save(phone)).thenReturn(phoneDirectoryEntity);

		String json = "{ \"headers\": {},\"request\":{ \"person_name\":\"mohit\" }}";
		try {
			assertFalse(phoneAppController.getPhoneNumber(json).getPhonenumber().equals("8674806691"));
			assertFalse(phoneAppController.getPhoneNumber(json).getPhonenumber().equals(""));
		} catch (ZylaHealthNonFatalException e) {
			// TODO Auto-generated catch block
			assertEquals("200", e.getErrorCode());
		}
	}
	
	@Test
	public void personNameIsNotThere() throws ParseException, ZylaHealthFatalException {
		String phoneNumber = null;

		String json = "{ \"headers\": {},\"request\":{}}";
		try {
			PhoneNumberDetails phoneNumberDetails=phoneAppController.getPhoneNumber(json);
		} catch (ZylaHealthNonFatalException e) {
			// TODO Auto-generated catch block
			assertEquals("200", e.getErrorCode());
		}
	}

}
