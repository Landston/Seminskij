trigger productTrigger on Product2 (before insert, before update, after insert, after update, before delete, after delete, after undelete) {
    new ProductTriggerHandler().run(Trigger.operationType);
}