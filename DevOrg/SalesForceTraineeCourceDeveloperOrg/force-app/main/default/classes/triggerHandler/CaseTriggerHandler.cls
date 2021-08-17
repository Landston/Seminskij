public with sharing class CaseTriggerHandler extends TriggerHandler {
    
    public CaseTriggerHandler(){
    }

    public override void beforeUpdate(){
        
    }
    
    public override void beforeInsert(){
        List<Case> cases =(List<Case>)(Trigger.New);
        List<String> emails = new List<String>();
        
        Map<String, Account> accountIdByEmail = new Map<String, Account>();
        Map<String, Lead> leadByEmail = new Map<String,Lead>();
    
        emails = getEmailListFromCases(cases);
        
        List<Account> suitableAccountsByEmail  = ([SELECT ID, Email__c, Language__c
                                                                          FROM Account 
                                                                          WHERE Email__c IN : emails]);
        List<Lead> suitableLeadsByEmail = [SELECT ID, Email, Language__c
                                                              FROM Lead
                                                              WHERE Email IN : emails];
    
        accountIdByEmail =  MapUtil.getAccountValueByStringKeyMapFromList('Email__c', suitableAccountsByEmail);
        leadByEmail =  MapUtil.getLeadValueByStringKeyMapFromList('Email', suitableLeadsByEmail);
        
        for(Case item : cases){
            String email  = item.SuppliedEmail;
            
            if(item.Origin == 'Web'){
                item.Priority = fillPriorityByReason(item.Reason);
            }

            if(accountIdByEmail.containsKey(email)){
                item.AccountId = accountIdByEmail.get(email).Id;
                item.Language__c = accountIdByEmail.get(email).Language__c;
            } else if ( leadByEmail.containsKey(email)){
                item.Lead__c = leadByEmail.get(email).Id;
                item.Language__c = leadByEmail.get(email).Language__c;
            }
            
        }
    }
    
    private  List<String> getEmailListFromCases(List<Case> cases){
        List<String> emails = new List<String>(); 

        for(Case item : cases) emails.add(item.SuppliedEmail);

        return emails; 
    }

    private String fillPriorityByReason(String reason){
        switch on reason {
            when 'Financial Claim' {
                return 'High';
            }
            when else {
                return 'Medium';
            }
    }
        
    }

    public override void afterInsert(){

    }
    
    public override void afterUpdate(){

    }

    public override void beforeDelete(){

    }

    public override void afterDelete(){

    }

    public override void afterUndelete(){

    }
}