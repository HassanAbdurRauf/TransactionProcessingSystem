Feature: Transfer Funds

  Scenario: Transfer funds between same currency accounts
    Given 2 Accounts with EUR Currency
    When TransRequest is initiated
    Then Funds were transfered
