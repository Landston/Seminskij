public with sharing class sObjectFileService {
    public sObjectFileService() {

    }

    @AuraEnabled
    public static String getContentVersionId(String sobjectId){
        try {
            List<ContentDocumentLink> links = [SELECT  ContentDocumentId 
                                                    FROM ContentDocumentLink   
                                                    WHERE LinkedEntityId =: sobjectId ];
            List<Id> contentDocIds = new List<Id>();

            for(ContentDocumentLink item : links){
                contentDocIds.add(item.ContentDocumentId);
            }
            
            List<ContentVersion> content = [SELECT Id 
                                                    FROM ContentVersion 
                                                    WHERE ContentDocumentId IN: contentDocIds ];

            return content.isEmpty() ? null : content[0].Id;

        } catch (Exception e) {
            throw new AuraHandledException(e.getMessage());
        }
    }
}
