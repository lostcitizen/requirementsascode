package org.requirementsascode.builder;

import org.requirementsascode.Actor;
import org.requirementsascode.UseCase;
import org.requirementsascode.UseCaseModel;

public class UseCaseModelBuilder {
	
	private UseCaseModel useCaseModel;

	public UseCaseModelBuilder() {
		this.useCaseModel = new UseCaseModel(null);
	}

	public UseCaseModel build() {
		return useCaseModel;
	}

	public UseCasePart useCase(String useCaseName) {
		UseCase useCase = useCaseModel.useCase(useCaseName);
		return new UseCasePart(useCase, this);
	}

	public Actor actor(String actorName) {
		return useCaseModel.actor(actorName);
	}

}
