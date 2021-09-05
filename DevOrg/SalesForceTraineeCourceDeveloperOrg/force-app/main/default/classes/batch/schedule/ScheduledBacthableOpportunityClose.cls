public with sharing class ScheduledBacthableOpportunityClose implements System.Schedulable{
    public ScheduledBacthableOpportunityClose() {

    }

    public void execute(SchedulableContext context){

        //List<String> stages = new List<String>{'Closed Won', 'Closed Lost'};
        
        String stagesString =  '(\'Closed Won\' ,' + '\'Closed Lost\')';
        String query = ' SELECT Id, Name, StageName, CloseDate FROM Opportunity';
        String condition = ' WHERE CloseDate = THIS_MONTH' + ' AND StageName NOT IN ' + stagesString ;
    
        CloseOppBatch batch = new CloseOppBatch(query+condition);

        Database.executeBatch(batch);
    }
}