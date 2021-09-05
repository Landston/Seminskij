({
    closeWindow : function(component, event) {
    
        let compEvent = component.getEvent("windowCloseEvent");
     
        compEvent.setParams({
            closeWindow : "true" 
        });

        compEvent.fire();
    },

    mergeCase : function(component, event) {
        let compEvent = component.getEvent("mergeCaseEvent");
        let chechBox = component.find("mergeCheckBox").get('v.checked');

        compEvent.setParams({
            isCheckBoxActive : chechBox
        });
        compEvent.fire();
    },

    notificationOwnerAction : function (component, event) {
        const notificationAction = component.get('c.notifyCaseOwner');
        caseId = component.get('v.duplicateCaseId');
    }

})
