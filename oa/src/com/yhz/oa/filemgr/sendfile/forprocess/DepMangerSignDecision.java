package com.yhz.oa.filemgr.sendfile.forprocess;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

public class DepMangerSignDecision implements DecisionHandler {

	@Override
	public String decide(OpenExecution exe) {
		Object obj = exe.getVariable("depManagerAuditResult");
		if("toCheck".equals(obj)){
			return "toCheck";
		}
		return "toDepMangerSign";
	}

}
