/**
 ********************************************************************************
 * Copyright (c) 2018 Robert Bosch GmbH.
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Robert Bosch GmbH - initial API and implementation
 ********************************************************************************
 */

package app4mc.example.tool.java;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.eclipse.app4mc.amalthea.model.ActivityGraph;
import org.eclipse.app4mc.amalthea.model.ActivityGraphItem;
import org.eclipse.app4mc.amalthea.model.Amalthea;
import org.eclipse.app4mc.amalthea.model.AmaltheaFactory;
import org.eclipse.app4mc.amalthea.model.Runnable;
import org.eclipse.app4mc.amalthea.model.Tag;
import org.eclipse.app4mc.amalthea.model.Task;
import org.eclipse.app4mc.amalthea.model.io.AmaltheaLoader;
import org.eclipse.app4mc.amalthea.model.io.AmaltheaWriter;
import org.eclipse.app4mc.amalthea.model.util.ModelUtil;
import org.eclipse.emf.common.util.EList;

public class LoadModifySaveExample {

	public static void main(String[] args) {

		// example: absolute path
		// final File inputFile = new File("d:/temp/democar.amxmi");
		// final File outputFile = new File("d:/temp/democar_1.amxmi");

		// example: relative path
		final File inputFile = new File("model-input/democar.amxmi");
		final File outputFile = new File("model-output/LoadModifySave/democar_1.amxmi");

		// ***** Load *****

		Amalthea model = AmaltheaLoader.loadFromFile(inputFile);

		if (model == null) {
			System.out.println("Error: No model loaded!");
			return;
		}
		
		// ***** Modify *****

		final AmaltheaFactory fac = AmaltheaFactory.eINSTANCE;
		
		Tag tag = fac.createTag();
		tag.setName("The new tag!");
		ModelUtil.getOrCreateCommonElements(model).getTags().add(tag);
		
		System.out.println("Printing all tasks");
		for(Task task: model.getSwModel().getTasks()) {
			System.out.println(task.getName());
		}
		System.out.println();
		
		System.out.println("Printing all runnables");
		for(Runnable runnable: model.getSwModel().getRunnables()) {
			System.out.println(runnable.getName());
		}
		
		// ***** Save *****

		AmaltheaWriter.writeToFile(model, outputFile);

		System.out.println("done");
	}

}
