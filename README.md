# Validity Checker

## Simple JShell Example

    jshell> import com.wensby.validity.*;

    jshell> ValidityChecker<String> checker = new ValidityCheckerBuilder<String>()
       ...>       .add(new NonNullCheck())
       ...>       .add(new SwedishPersonalIdentityNumberCheck(new LuhnAlgorithm()))
       ...>       .build();

    jshell> checker.validate("19760728-0794");

    jshell> checker.validate(null);
    |  Exception com.wensby.validity.FailedValidityCheckException: Candidate 'null' did not fulfill criteria: not null
    |        at ValidityChecker.validate (ValidityChecker.java:19)
    |        at (#7:1)

    jshell> checker.validate("19760728-0795");
    |  Exception com.wensby.validity.FailedValidityCheckException: Candidate '19760728-0795' did not fulfill criteria: valid swedish personal number
    |        at ValidityChecker.validate (ValidityChecker.java:19)
    |        at (#6:1)

