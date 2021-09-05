public with sharing class CaseService {
    public CaseService() {

    }

    @AuraEnabled
    public static void notifyCaseOwner(String caseId){
        try {
            Case item = CaseManager.getCaseById(caseId);
            CustomNotificationType  notificationType = [ SELECT ID	 FROM CustomNotificationType WHERE customNotifTypeName= 'Case Merge Custom Notification Type'];
	        Messaging.CustomNotification notification = new Messaging.CustomNotification();
            String body = System.Label.Case_merge_notification;

            notification.setTitle('Close a case');
            notification.setBody( String.format(body, new List<String>{item.CaseNumber}));
            notification.setNotificationTypeId(notificationType.Id);
            notification.setTargetId(item.Id);
                    
            try {
                notification.send(new Set<String>{item.OwnerId});
            }
            catch (Exception e) {
                System.debug('Problem sending notification: ' + e.getMessage());
            }
        } catch (Exception e) {
            throw new AuraHandledException(e.getMessage());
        }
    }

    @AuraEnabled
    public static void carryOnEmails(String childCaseId, String parentCaseId){
        try {
            List<EmailMessage> emails = [SELECT FIELDS(standard) FROM EmailMessage WHERE ParentId =: childCaseId];
           
            List<EmailMessage> newEmails = new List<EmailMessage>();

            for(EmailMessage email : emails){
                EmailMessage newEmail = email.clone(false, true, true);
                
                newEmail.ParentId = parentCaseId;
                
                newEmails.add(newEmail);
            }
            insert newEmails;

        } catch (Exception e) {
            throw new AuraHandledException(e.getMessage());
        }
    }
    @AuraEnabled
    public static Boolean checkCaseIdForWarningPageNeedness(String caseId){
        try {
            List<String> statuses = new List<String>{'In progress', 'On Hold'};
            Case chencaseToVerify = CaseManager.getCaseById(caseId);

            String ownerId = chencaseToVerify.OwnerId;

            if((statuses.contains(chencaseToVerify.Status) && !ownerid.startsWith('00G'))){
                return true;
            }
            
            return false;
        } catch (Exception e) {
            throw new AuraHandledException(e.getMessage());
        }
    }

    @AuraEnabled
    public static List<Id> getDuplicateCases(String caseId){
        try{
        Case mainCase = [SELECT ID, CaseNumber, Subject, SuppliedEmail, Reason 
                                        FROM Case 
                                            WHERE ID =: caseId 
                                                AND Status != 'Closed' ] ;  
         Map<Id, Case> duplicates = new Map<Id, Case>([SELECT ID, Subject
                                                        FROM Case 
                                                            WHERE Subject = : mainCase.Subject 
                                                                AND SuppliedEmail =: mainCase.SuppliedEmail
                                                                AND Reason =: mainCase.Reason  
                                                                AND Status !=  'Closed'
                                                                AND Id !=: mainCase.Id]);
       
        if(duplicates.isEmpty()){
            return new List<Id>();
        }

        return new List<Id>(duplicates.keySet());

    } catch(Exception e){
        throw new AuraException(e.getMessage());
    }

    }

    @AuraEnabled
    public static Case getCaseById(String caseId){
        try {
            return CaseManager.getCaseById(caseId);
        } catch (Exception e) {
            throw new AuraHandledException(e.getMessage());
        }
    }

    @AuraEnabled
    public static void mergeCasesById(String parentCaseId, String childCaseId){
        try {
            Case parentCase = CaseManager.getCaseById(parentCaseId);
            Case childCase = CaseManager.getCaseById(childCaseId);

            if(parentCase.Description !=null){
                parentCase.Description += mergeDescription(childCase.Description);
            } else {
                parentCase.Description = childCase.Description;
            }

            carryOnEmails(childCaseId, parentCaseId);
            parentCase.Priority = getHigherPriority(parentCase.Priority, childCase.Priority);            
            fillParentCaseEmptyFieldsWithChild(parentCase, childCase);
            closeChildCase(parentCase,childCase);

            update parentCase;
            update childCase;

        } catch (Exception e) {
            throw new AuraHandledException(e.getMessage());
        }
    }

    @AuraEnabled
    public static void closeChildCase(Case parentCase, Case childCase){
        try {
            childCase.Status = 'Closed';
            childCase.ParentId = parentCase.Id;
        } catch (Exception e) {
            throw new AuraHandledException(e.getMessage());
        }
    }

    @AuraEnabled
    public static void fillParentCaseEmptyFieldsWithChild(Case parent, Case child){
        try {
            System.debug(LoggingLevel.INFO, 'Fill in empty fields');
            if(parent.AccountId == null && child.AccountId != null){
                parent.AccountId = child.AccountId;
            }
            if(parent.Lead__c == null && child.Lead__c != null){
                parent.Lead__c = child.Lead__c;
            }
            if(parent.Country__c == null && child.Country__c != null){
                parent.Country__c = child.Country__c;
            }
            if(parent.SuppliedEmail == null && child.SuppliedEmail != null){
                parent.SuppliedEmail = child.SuppliedEmail;
            }
        } catch (Exception e) {
            throw new AuraHandledException(e.getMessage());
        }
    }
    @AuraEnabled
    public static String mergeDescription(String childDescription ){
        if( childDescription == null){
            return '';
        } else{
            return '----//----' + childDescription;  
        }
    }

    @AuraEnabled
    public static String getHigherPriority(String parentCasePriorityLabel, String childCasePriorityLabel){
        Map<String, Integer> priorityLabelByPriorityNumber = new Map<String, Integer>(getCasePriorityLabelByNumber());

        if (priorityLabelByPriorityNumber.get(childCasePriorityLabel) < priorityLabelByPriorityNumber.get(parentCasePriorityLabel)){
            return childCasePriorityLabel;
        } else return parentCasePriorityLabel;
       
    }

    @AuraEnabled
    public static Map<String, Integer> getCasePriorityLabelByNumber(){
        String objectName = 'Case';
        String fieldName = 'Priority';
        Integer priorityNumber = 1;
        Map<String, Integer> values = new Map<String, Integer>();

	    Schema.DescribeFieldResult fieldResult = Case.Priority.getDescribe();
	
        for( Schema.PicklistEntry pickListVal : fieldResult.getPicklistValues()){
			values.put( pickListVal.getLabel(), priorityNumber);
            priorityNumber++;
		}
        
        return values;

    }
    
   
}