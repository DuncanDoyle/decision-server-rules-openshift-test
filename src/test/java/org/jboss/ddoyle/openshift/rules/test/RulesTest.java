package org.jboss.ddoyle.openshift.rules.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.jboss.ddoyle.openshift.rules.model.SimpleFact;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.command.Command;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.command.CommandFactory;

public class RulesTest {

	@Test
	public void testRules() {

		SimpleFact fact = new SimpleFact();
		fact.setType("test");
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kieContainer = kieServices.newKieClasspathContainer();
		StatelessKieSession kieSession = kieContainer.newStatelessKieSession();

		List<Command> cmds = new ArrayList<>();
		cmds.add(CommandFactory.newInsert(fact));
		cmds.add(CommandFactory.newFireAllRules());
		kieSession.execute(CommandFactory.newBatchExecution(cmds));

		assertEquals("This is a test", fact.getResult());

	}

}
