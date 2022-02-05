package com.ui.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Locale locale, Model model, HttpSession session) {
		logger.info("##### LOGIN PAGE #####");
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}		
		return "index";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String adminhome(Locale locale, Model model, HttpSession session) {
		logger.info("##### DASHBOARD #####");
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}
		return "home";
	}
	
	@RequestMapping(value = "/logoutadmin", method = RequestMethod.GET)
	public String logoutadmin(Locale locale, Model model, HttpSession session) {
		logger.info("##### ADMIN LOGOUT #####");
		try {
			if (session.getAttribute("useridadmin") != null
					|| Integer.parseInt(session.getAttribute("useridadmin").toString()) != 0) {
				session.setAttribute("useridadmin", null);
				session.setAttribute("shownameadmin", null);
				session.setAttribute("usertypeidadmin", null);
				session.setAttribute("emailidadmin", null);
				session.setAttribute("mobilenumberadmin", null);
				session.setAttribute("locationidadmin", null);
				return "index";
			}
		} catch (Exception e) {
			return "index";
		}
		return "index";
	}
	
	@RequestMapping(value = "/manage_country", method = RequestMethod.GET)
	public String manage_country(Locale locale, Model model, HttpSession session) {
		logger.info("##### MANAGE COUNTRY #####");
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}
		return "manage_country";
	}
	
	@RequestMapping(value = "/manage_state", method = RequestMethod.GET)
	public String manage_state(Locale locale, Model model, HttpSession session) {
		logger.info("##### MANAGE STATE #####");
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}
		return "manage_state";
	}
	
	@RequestMapping(value = "/manage_measurement_unit", method = RequestMethod.GET)
	public String manage_measurement_unit(Locale locale, Model model, HttpSession session) {
		logger.info("##### MANAGE UNIT #####");
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}
		return "manage_measurement_unit";
	}
	
	@RequestMapping(value = "/manage_currency", method = RequestMethod.GET)
	public String manage_currency(Locale locale, Model model, HttpSession session) {
		logger.info("##### MANAGE CURRENCY #####");
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}
		return "manage_currency";
	}
	
	@RequestMapping(value = "/manage_financial_year", method = RequestMethod.GET)
	public String manage_financial_year(Locale locale, Model model, HttpSession session) {
		logger.info("##### MANAGE FINANCIAL YEAR #####");
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}
		return "manage_financial_year";
	}
	
	@RequestMapping(value = "/manage_user", method = RequestMethod.GET)
	public String manage_user(Locale locale, Model model, HttpSession session) {
		logger.info("##### MANAGE USER #####");
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}
		return "manage_user";
	}
	
	@RequestMapping(value = "/manage_enquiry", method = RequestMethod.GET)
    public String manage_enquiry(Locale locale, Model model, HttpSession session) {
        logger.info("##### MANAGE ENQUIRY #####"+session.getAttribute("usertypeidadmin"));
        if (session.getAttribute("useridadmin") == null
                || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
            return "index";
        }
        return "manage_enquiry";
    }
	
	@RequestMapping(value = "/manage_property", method = RequestMethod.GET)
    public String manage_property(Locale locale, Model model, HttpSession session) {
        logger.info("##### MANAGE USER #####");
        if (session.getAttribute("useridadmin") == null
                || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
            return "index";
        }
        return "manage_property";
    }
	
	@RequestMapping(value = "/manage_tax", method = RequestMethod.GET)
	public String manage_tax(Locale locale, Model model, HttpSession session) {
		logger.info("##### MANAGE TAX #####");
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}
		return "manage_tax";
	}
	
	@RequestMapping(value = "/manage_district", method = RequestMethod.GET)
	public String manage_district(Locale locale, Model model, HttpSession session) {
		logger.info("##### MANAGE DISTRICT #####");
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}
		return "manage_district";
	}
	
	@RequestMapping(value = "/manage_taluka", method = RequestMethod.GET)
	public String manage_taluka(Locale locale, Model model, HttpSession session) {
		logger.info("##### MANAGE TALUKA #####");
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}
		return "manage_taluka";
	}
	
	@RequestMapping(value = "/manage_location", method = RequestMethod.GET)
	public String manage_location(Locale locale, Model model, HttpSession session) {
		logger.info("##### MANAGE LOCATION #####");
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}
		return "manage_location";
	}
	
	@RequestMapping(value = "/manage_project", method = RequestMethod.GET)
	public String manage_project(Locale locale, Model model, HttpSession session) {
		logger.info("##### MANAGE PROJECT #####");
		if (session.getAttribute("useridadmin") == null
				|| Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
			return "index";
		}
		return "manage_project";
	}
	
	@RequestMapping(value = "/manage_payment_schedule", method = RequestMethod.GET)
    public String manage_payment_schedule(Locale locale, Model model, HttpSession session) {
        logger.info("##### MANAGE PAYMENT #####");
        if (session.getAttribute("useridadmin") == null
                || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
            return "index";
        }
        return "manage_payment_schedule";
    }
	
	@RequestMapping(value = "/manage_project_details", method = RequestMethod.GET)
    public String manage_project_details(Locale locale, Model model, HttpSession session) {
        logger.info("##### MANAGE PROJECT DETAILS #####");
        if (session.getAttribute("useridadmin") == null
                || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
            return "index";
        }
        return "manage_project_details";
    }
	
	@RequestMapping(value = "/manage_category", method = RequestMethod.GET)
    public String manage_category(Locale locale, Model model, HttpSession session) {
        logger.info("##### MANAGE CATEGORY #####");
        if (session.getAttribute("useridadmin") == null
                || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
            return "index";
        }
        return "manage_category";
    }
	
	@RequestMapping(value = "/manage_subcategory", method = RequestMethod.GET)
    public String manage_subcategory(Locale locale, Model model, HttpSession session) {
        logger.info("##### MANAGE SUBCATEGORY #####");
        if (session.getAttribute("useridadmin") == null
                || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
            return "index";
        }
        return "manage_subcategory";
    }
	
	@RequestMapping(value = "/manage_type", method = RequestMethod.GET)
    public String manage_type(Locale locale, Model model, HttpSession session) {
        logger.info("##### MANAGE TYPE #####");
        if (session.getAttribute("useridadmin") == null
                || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
            return "index";
        }
        return "manage_type";
    }
	
	@RequestMapping(value = "/manage_price_label", method = RequestMethod.GET)
    public String manage_price_label(Locale locale, Model model, HttpSession session) {
        logger.info("##### MANAGE PRICE LABEL #####");
        if (session.getAttribute("useridadmin") == null
                || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
            return "index";
        }
        return "manage_price_label";
    }
	
	@RequestMapping(value = "/manage_occupation", method = RequestMethod.GET)
    public String manage_occupation(Locale locale, Model model, HttpSession session) {
        logger.info("##### MANAGE Occupation #####");
        if (session.getAttribute("useridadmin") == null
                || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
            return "index";
        }
        return "manage_occupation";
    }
	
	@RequestMapping(value = "/manage_designation", method = RequestMethod.GET)
    public String manage_designation(Locale locale, Model model, HttpSession session) {
        logger.info("##### MANAGE Designation #####");
        if (session.getAttribute("useridadmin") == null
                || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
            return "index";
        }
        return "manage_designation";
    }
	
	@RequestMapping(value = "/manage_range", method = RequestMethod.GET)
    public String manage_range(Locale locale, Model model, HttpSession session) {
        logger.info("##### MANAGE RANGE #####");
        if (session.getAttribute("useridadmin") == null
                || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
            return "index";
        }
        return "manage_range";
    }
	//manage_site_slider
	@RequestMapping(value = "/manage_slider", method = RequestMethod.GET)
    public String manage_slider(Locale locale, Model model, HttpSession session) {
        logger.info("##### MANAGE SLIDER #####");
        if (session.getAttribute("useridadmin") == null
                || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
            return "index";
        }
        return "manage_slider";
    }
	
	@RequestMapping(value = "/manage_site_slider", method = RequestMethod.GET)
    public String manage_site_slider(Locale locale, Model model, HttpSession session) {
        logger.info("##### MANAGE SLIDER #####");
        if (session.getAttribute("useridadmin") == null
                || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
            return "index";
        }
        return "manage_site_slider";
    }
	
	@RequestMapping(value = "/manage_slider_details", method = RequestMethod.GET)
    public String manage_slider_details(Locale locale, Model model, HttpSession session) {
        logger.info("##### MANAGE SLIDER #####");
        if (session.getAttribute("useridadmin") == null
                || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
            return "index";
        }
        return "manage_slider_details";
    }
	
	@RequestMapping(value = "/manage_feedback", method = RequestMethod.GET)
    public String manage_feedback(Locale locale, Model model, HttpSession session) {
        logger.info("##### MANAGE TESTIMONIAL #####");
        if (session.getAttribute("useridadmin") == null
                || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
            return "index";
        }
        return "manage_feedback";
    }
	
	@RequestMapping(value = "/manage_achievement", method = RequestMethod.GET)
    public String manage_achievement(Locale locale, Model model, HttpSession session) {
        logger.info("##### MANAGE ACHIEVEMENT #####");
        if (session.getAttribute("useridadmin") == null
                || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
            return "index";
        }
        return "manage_achievement";
    }
	
	@RequestMapping(value = "/manage_workstatus", method = RequestMethod.GET)
    public String manage_workstatus(Locale locale, Model model, HttpSession session) {
        logger.info("##### MANAGE WORKSTATUS #####");
        if (session.getAttribute("useridadmin") == null
                || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
            return "index";
        }
        return "manage_workstatus";
    }
	
	@RequestMapping(value = "/manage_bank", method = RequestMethod.GET)
    public String manage_bank(Locale locale, Model model, HttpSession session) {
        logger.info("##### MANAGE BANK #####");
        if (session.getAttribute("useridadmin") == null
                || Integer.parseInt(session.getAttribute("useridadmin").toString()) == 0) {
            return "index";
        }
        return "manage_bank";
    }
}
