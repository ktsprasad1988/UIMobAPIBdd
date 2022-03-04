package com.Armorcode.bdd.integrations.common_utils;
/*package com.dc.bdd.integrations.common_utils;

import com.dc.bdd.common.hooks.CucumberHooks;

import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackMessage;

public class SlackNotification {
	
	public static void sendSlackNotification(String ui) {
		
		SlackApi api = new SlackApi("https://hooks.slack.com/services/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");
		 api.call(new SlackMessage("AUTOMATED TESTING - "+ui+""+'\n'
			+'\n'
			+"TOTAL TESTCASES: "+ CucumberHooks.totalTestCases.size()+" | "+"TOTAL PASSED TESTCASES: " + CucumberHooks.passedTests.size()+" | "+"TOTAL FAILED TESTCASES: "+ CucumberHooks.failedTests.size()+""+'\n'
			+'\n'
			+"PASSED TESTCASES:-"+'\n'
			+CucumberHooks.passedTests.toString()+'\n'+
			'\n'+
			"FAILED TESTCASES:-"+'\n'
			+CucumberHooks.failedTests.toString()+'\n'+
			'\n'
			+"Please contact Cloud Services Team & Testing team if you need any further information."+ ""));
	}

}
*/