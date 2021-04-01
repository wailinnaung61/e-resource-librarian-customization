package controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorPageController {

	@RequestMapping(value = "errors", method = RequestMethod.GET)
	public String renderErrorPage(HttpServletRequest httpRequest) {

		int httpErrorCode = getErrorCode(httpRequest);

		switch (httpErrorCode) {
		case 400: {
		}
		case 401: {
		}
		case 404: {
			return "404errorpage";
		}
		case 500: {
			return "500errorpage";
		}

		}
		return null;

	}

	private int getErrorCode(HttpServletRequest httpRequest) {
		return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
	}

	@RequestMapping(value = "500Error", method = RequestMethod.GET)
	public void throwRuntimeException() {
		throw new NullPointerException("Throwing a null pointer exception");
	}

}
