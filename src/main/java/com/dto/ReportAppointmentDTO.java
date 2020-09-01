package com.dto;

public class ReportAppointmentDTO {

	private AppointmentDTO appointment;
	private DiagnosisDTO diagnosis;
	private RecipeDTO recipe;

	public AppointmentDTO getAppointment() {
		return appointment;
	}

	public void setAppointment(AppointmentDTO appointment) {
		this.appointment = appointment;
	}

	public DiagnosisDTO getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(DiagnosisDTO diagnosis) {
		this.diagnosis = diagnosis;
	}

	public RecipeDTO getRecipe() {
		return recipe;
	}

	public void setRecipe(RecipeDTO recipe) {
		this.recipe = recipe;
	}

	@Override
	public String toString() {
		return "ReportAppointmentDTO{" + "appointment=" + appointment + ", diagnosis=" + diagnosis + ", recipe="
				+ recipe + '}';
	}
}
