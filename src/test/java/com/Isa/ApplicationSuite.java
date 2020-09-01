package com.Isa;

import com.Isa.controller.AppointmentsControllerTest;
import com.Isa.controller.ClinicControllerTest;
import com.Isa.controller.ClinicalCenterAdministratorContollerTest;
import com.Isa.service.AppointmentServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ ClinicalCenterAdministratorContollerTest.class, ClinicControllerTest.class,
		AppointmentsControllerTest.class, AppointmentServiceTest.class, })
public class ApplicationSuite {
}
