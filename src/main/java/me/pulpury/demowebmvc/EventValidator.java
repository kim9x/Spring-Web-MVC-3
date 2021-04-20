package me.pulpury.demowebmvc;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

// Validator 자체를 bean으로 등록할 수 있다.
@Component
public class EventValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Event.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Event event = (Event)target;
		if (event.getName().equalsIgnoreCase("aaa")) {
			errors.rejectValue("name", "wrongValue", "thie value is not allowed");
		}
	}
}
