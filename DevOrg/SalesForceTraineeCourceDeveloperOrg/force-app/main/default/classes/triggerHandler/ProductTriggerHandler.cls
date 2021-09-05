public with sharing class ProductTriggerHandler extends TriggerHandler {
    public ProductTriggerHandler() {

    }

    protected override void beforeInsert(){}

    protected override void beforeUpdate(){}
    
    protected override void afterInsert(){}
    
    protected override void afterUpdate(){
        List<Product2> products = (List<Product2>) Trigger.New;

        for(Product2 prodItem : products){
            if(!prodItem.IsActive){

            }
        }
        
    }

    public void deactivateProductAction(Product2 prod){
        List<OpportunityLineItem> prodOpps = [SELECT Id, OpportunityId
        FROM OpportunityLineItem WHERE Product2Id =: prod.Id ];

        List<Task> tasks= new List<Task>();
        RecordType recordType =  [SELECT id FROM RecordType WHERE Name = 'Call' AND SobjectType = 'Task'];

        for(OpportunityLineItem lineItem : prodOpps){
            Task taska = new Task();
            taska.Subject='Old model deal!!!';
            taska.Status = 'New';
            taska.Auto_Created__c = true;
            taska.ActivityDate = Date.today().addMonths(1);
            taska.Priority = 'High';
            taska.Phone__c = lineItem.Opportunity.Account.Phone;
            taska.RecordType= recordType;

            tasks.add(taska);
        }
       
    }

    protected override void beforeDelete(){}

    protected override void afterDelete(){}

    protected override void afterUndelete(){}
}
