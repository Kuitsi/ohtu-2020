Feature: A new user account can be created if a proper unused username and a proper password are given

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

    Scenario: user can login with successfully generated account
        Given user with username "lea" with password "salainen1" is successfully created
        And   user has logged out
        And   login is selected
        When  correct username "lea" and password "salainen1" are given
        Then  user is logged in

    Scenario: user can not login with account that is not successfully created
        Given user with username "aa" and password "bad" is tried to be created
        And   login is selected
        When  incorrect username "aa" and password "bad" are given
        Then  user is not logged in and error message is given
