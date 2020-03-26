Feature: A new user account can be created if a proper unused username and password are given

    Scenario: creation is successful with valid username and password
        Given command new user is selected
        When  a valid username "liisa" and password "salainen1" and matching password confirmation are entered
        Then  a new user is created

    Scenario: creation fails with too short username and valid password
        Given command new user is selected
        When  an invalid username "xy" and password "salainen1" and matching password confirmation are entered
        Then  user is not created and error "username should have at least 3 characters" is reported

    Scenario: creation fails with correct username and too short password
        Given command new user is selected
        When  a valid username "johndoe" and invalid password "secret" and matching password confirmation are entered
        Then  user is not created and error "password should have at least 8 characters" is reported

    Scenario: creation fails when password and password confirmation do not match
        Given command new user is selected
        When  a valid username "matti" is entered but password confirmation "salainen2" does not match valid password "salainen1"
        Then  user is not created and error "password and password confirmation do not match" is reported

    Scenario: creation fails with username incorrectly containing numbers and valid password
        Given command new user is selected
        When  an invalid username "asdasd123" and password "salainen1" and matching password confirmation are entered
        Then  user is not created and error "username must contain only characters a-z" is reported

    Scenario: creation fails with valid username but password incorrectly containing only letters
        Given command new user is selected
        When  a valid username "asdasd" and invalid password "asdasdasd" and matching password confirmation are entered
        Then  user is not created and error "password should not contain only letters" is reported
