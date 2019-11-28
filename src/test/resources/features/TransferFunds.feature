Feature: Transfer Funds

  Scenario: Transfer funds between same currency accounts
    Given Accounts with EUR Currency
    When TransRequest is initiated
    Then Funds were transfered
