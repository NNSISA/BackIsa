package com.Isa;

import com.Isa.controller.AppointmentControllerUnitTest;
import com.Isa.controller.ClinicControllerUnitTest;
import com.Isa.service.AppointmentServiceUnitTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ ClinicControllerUnitTest.class, AppointmentControllerUnitTest.class,
		AppointmentServiceUnitTest.class, })
public class UnitSuite {
}
