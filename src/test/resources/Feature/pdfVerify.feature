Feature: PDF Verification

  Scenario: Verify PDF contains correct deatils
    Given launch pdf
    When pdf filed get opened
    Then verify it contains correct informations
