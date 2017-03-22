package org.requirementsascode;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ExceptionHandlingTest extends AbstractTestCase{
	private static final String EXCEPTION_HANDLING_FLOW = "Exception Handling Flow";
	private static final String SYSTEM_THROWS_EXCEPTION = "System throws Exception";
	private static final String SYSTEM_HANDLES_EXCEPTION = "System handles exception";

	@Before
	public void setup() {
		setupWith(new TestUseCaseRunner());
	}
	
	@Test
	public void doesNotHandleExceptionIfNoExceptionOccurs() {		
		useCaseModel.useCase(USE_CASE)
			.basicFlow()
				.step(SYSTEM_DISPLAYS_TEXT).system(displayConstantText())
			.flow(EXCEPTION_HANDLING_FLOW)
				.after(SYSTEM_DISPLAYS_TEXT)
					.step(SYSTEM_HANDLES_EXCEPTION)
						.handle(ArrayIndexOutOfBoundsException.class).system(e -> {});

		useCaseRunner.run();

		assertEquals(SYSTEM_DISPLAYS_TEXT + ";", runStepNames());
	}
	
	@Test
	public void doesNotHandleExceptionIfSystemReactionDoesNotThrowException() {		
		useCaseModel.useCase(USE_CASE)
			.basicFlow()
				.step(SYSTEM_DISPLAYS_TEXT).system(displayConstantText())		
			.flow(EXCEPTION_HANDLING_FLOW)
				.after(SYSTEM_DISPLAYS_TEXT)
					.step(SYSTEM_HANDLES_EXCEPTION).handle(ArrayIndexOutOfBoundsException.class).system(e -> {});
		
		useCaseRunner.run();
		
		assertEquals(SYSTEM_DISPLAYS_TEXT +";", runStepNames());
	}
	
	@Test
	public void handlesExceptionAfterSpecificStep() {				
		useCaseModel.useCase(USE_CASE)
			.basicFlow()
				.step(SYSTEM_THROWS_EXCEPTION).system(throwArrayIndexOutOfBoundsException())		
			.flow(EXCEPTION_HANDLING_FLOW).after(SYSTEM_THROWS_EXCEPTION)
				.step(SYSTEM_HANDLES_EXCEPTION).handle(ArrayIndexOutOfBoundsException.class).system(e -> {});
		
		useCaseRunner.run();
		
		assertEquals(SYSTEM_HANDLES_EXCEPTION, latestStepName());
	}
	
	@Test
	public void handlesExceptionAtAnyTime() {			
		useCaseModel.useCase(USE_CASE)
			.basicFlow()
				.step(SYSTEM_DISPLAYS_TEXT).system(displayConstantText())		
			.flow(ALTERNATIVE_FLOW).after(SYSTEM_DISPLAYS_TEXT)
				.step(SYSTEM_THROWS_EXCEPTION).system(throwArrayIndexOutOfBoundsException())
			.flow(EXCEPTION_HANDLING_FLOW).when(r -> true)
				.step(SYSTEM_HANDLES_EXCEPTION).handle(ArrayIndexOutOfBoundsException.class).system(e -> {});
		
		useCaseRunner.run();
		
		assertEquals(SYSTEM_DISPLAYS_TEXT +";" + SYSTEM_THROWS_EXCEPTION + ";" + SYSTEM_HANDLES_EXCEPTION +";", runStepNames());
	}
}
