trigger IdentifieUserByEmailCaseTrigger on Case (before insert, before update, after insert, after update, before delete, after delete, after undelete) {
    new CaseTriggerHandler().Run(Trigger.operationType);
    
}