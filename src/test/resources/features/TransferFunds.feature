Feature: Transfer Funds

  Scenario: Transfer funds between same currency accounts
    Given Accounts with EUR Currency
    When TransRequest is initiated with transaction amount of 50.0
    Then Funds were transfered

  Scenario: Transfer funds between different currency accounts
    Given Accounts with different currency
    When TransRequest is initiated with transaction amount of 40.0
    Then Funds were transfered