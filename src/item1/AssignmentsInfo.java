package item1;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)//SOURCE,CLASS,RUNTIME
@Target(ElementType.TYPE)//���w annotation �ϥι�H
public @interface AssignmentsInfo {

	public enum State {
		FINISH, UNFINISH, CHECKING
	};

	public enum Priority {
		HIGH, MEDDIUM, LOW
	};

	public enum Difficulty {
		CAKE, NORMAL, HARD, HELL
	};

	String coder() default "Voldemort";

	Priority priority() default Priority.LOW;

	State state() default State.UNFINISH;

	String lastModified() default "2016/7/1";

	Difficulty difficulty() default Difficulty.NORMAL;

}
