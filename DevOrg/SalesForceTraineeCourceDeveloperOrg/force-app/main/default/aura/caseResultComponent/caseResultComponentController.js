({
    showPopUpMenu: function(component, event, helper) {
        const action = component.get('c.checkCaseIdForWarningPageNeedness');
        const Id = component.get('v.duplicateCaseId');

        action.setParams({
            caseId : Id
        });
        action.setCallback(this, function (response) {
            let isVerificationNeeds = response.getReturnValue();

            if(isVerificationNeeds){
                component.set('v.isPopUpPageOn', 'true');    
            } else {
                component.set('v.isPopUpPageOn', 'false'); 

                helper.mergeCases(component, event);
            }

        });
        
        $A.enqueueAction(action);
    },

    closeWindowAction: function (component, event, helper) {
        const closeWindowFlag = event.getParam('closeWindow');

        if (closeWindowFlag) {
            component.set('v.isPopUpPageOn', 'false');  
        }
    },
    doInit : function (component, event, helper) {
    },

    mergeCases: function (component, event, helper) {
        let isCheckBoxActive = event.getParam('isCheckBoxActive');

        if(isCheckBoxActive){
            helper.notifyCaseOwner(component);
        }
        
        helper.mergeCases(component, event);
    }

})
