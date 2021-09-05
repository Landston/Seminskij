public with sharing class CaseManager {
    public CaseManager() {

    }

    @AuraEnabled
    public static Case getCaseById(String caseId){
        try {
            return [SELECT iD, Description, Priority, ParentId, Country__c, SuppliedEmail, Status, AccountId, Lead__c, OwnerId, CaseNumber
                                  FROM Case 
                                  WHERE ID =: caseid LIMIT 1];

        } catch (Exception e) {
            throw new AuraHandledException(e.getMessage());
        }
    }

}