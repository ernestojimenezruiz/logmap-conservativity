package main;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Set;

import repair.ConservativityRepairFacility;
import uk.ac.ox.krr.logmap2.LogMap2Core;

import uk.ac.ox.krr.logmap2.Parameters;
import uk.ac.ox.krr.logmap2.io.OAEIAlignmentOutput;
import uk.ac.ox.krr.logmap2.io.OutPutFilesManagerStatic;
import uk.ac.ox.krr.logmap2.mappings.objects.MappingObjectStr;

import uk.ac.ox.krr.logmap2.oaei.Oraculo;

import util.FileUtil;

public class LogMapConservativityMatching {
	
	private long init_tot, fin;
	private double total_time=0.0;
	
	LogMap2Core logmap2;
	
	OAEIAlignmentOutput alignment_output;
	
	ConservativityRepairFacility consRepair;
	
	Set<MappingObjectStr> repairedMappings;

	
	public LogMapConservativityMatching(String iri_onto_str1, String iri_onto_str2, String output_folder) throws Exception {
		
		
		init_tot = Calendar.getInstance().getTimeInMillis();
		
		Parameters.readParameters();
		
		Oraculo.allowOracle(Parameters.allow_interactivity);
		
		logmap2 = new LogMap2Core(iri_onto_str1, iri_onto_str2);

		Set<MappingObjectStr> origMappings = logmap2.getLogMapMappings();
		logmap2.clearIndexStructures();
		
		try {
			consRepair = new ConservativityRepairFacility(iri_onto_str1, iri_onto_str2, false, origMappings);
			consRepair.repair(false);
			repairedMappings = consRepair.getRepairedMappings();
		}
		catch(Exception | Error e){
			FileUtil.writeErrorLogAndConsole("Repair failed, using the computed alignment: " + 
					e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
			repairedMappings = origMappings;
		}
		
		if(repairedMappings.isEmpty() && !origMappings.isEmpty()){
			FileUtil.writeErrorLogAndConsole("Empty repaired alignment, using the original one");
			repairedMappings = origMappings;
		}
			
		fin = Calendar.getInstance().getTimeInMillis();
		
		//System.out.println("Matching Time (s): " + (float)((double)fin-(double)init_tot)/1000.0);
		total_time = (float)((double)fin-(double)init_tot)/1000.0;
		//total_time = total_time - time_loading;
		//System.out.println("Time loading ontos (s): " + time_loading);
		//System.out.println("Is Oracle active? " + Oraculo.isActive() + "  " + Oraculo.getStatusOraculo());
		
		
		//We save anchors in all formats too
		saveLogMapMappings(repairedMappings, output_folder, "logmap_conservativity");
		
		
		FileUtil.writeLogAndConsole("LogMap Conservativity Total Matching Time (s): " + total_time);
		
	}
	
	private static String getHelpMessage(){
		return "LogMap 2 can operate as an ontology matching system (MATCHER/EVALUATION) or as a mapping debugging system (DEBUGGER). " +
				"Additionally it also converts mappings from RDF-OAEI format to OWL.\n\n" +
	
				"LogMap Conservativity MATCHER facility requires 4 parameters:\n" +
				"\t1. MATCHER. To use the matching functionality.\n" +
				"\t2. IRI ontology 1. e.g.: http://myonto1.owl  or  file:/C://myonto1.owl  or  file:/usr/local/myonto1.owl\n" +
				"\t3. IRI ontology 2. e.g.: http://myonto2.owl  or  file:/C://myonto2.owl  or  file:/usr/local/myonto2.owl\n" +
				"\t4. Full output path for mapping files e.g. /usr/local/output_path/ or C://output_path/\n or /C://output_path/\\n" +
				"\tFor example: java -jar logmap_conservativity.jar MATCHER file:/home/ontos/cmt.owl file:/home/ontos/ekaw.owl /home/mappings/output/ \n\n\n";
				
				/*"LogMap 2 EVALUATION facility requires 6 parameters:\n" +
				"\t1. EVALUATION. To use the matching + evaluation functionality against reference mappings.\n" +
				"\t2. IRI ontology 1. e.g.: http://myonto1.owl  or  file:/C://myonto1.owl  or  file:/usr/local/myonto1.owl\n" +
				"\t3. IRI ontology 2. e.g.: http://myonto2.owl  or  file:/C://myonto2.owl  or  file:/usr/local/myonto2.owl\n" +
				"\t4. Reference mappings (RDF alignment format). e.g.: /usr/local/reference_mappings.rdf\n" +
				"\t5. Full output path for mapping files and overlapping modules/fragments. e.g. /usr/local/output_path/ or C://output_path/\n or /C://output_path/\\n" +
				"\t6. Classify the input ontologies together with the mappings. e.g. true or false\n\n" +
				"\tFor example: java -jar logmap2_standalone.jar EVALUATION file:/home/ontos/cmt.owl file:/home/ontos/ekaw.owl /home/refs/ref-cmt-ekaw.rdf /home/mappings/output true\n\n\n" +
				
				"LogMap 2 DEBUGGER facility requires 8 parameters:\n" +
				"\t1. DEBUGGER. To use the debugging facility.\n" +
				"\t2. IRI ontology 1. e.g.: http://myonto1.owl  or  file:/C://myonto1.owl  or  file:/usr/local/myonto1.owl\n" +
				"\t3. IRI ontology 2. e.g.: http://myonto2.owl  or  file:/C://myonto2.owl  or  file:/usr/local/myonto2.owl\n" +
				"\t4. Format mappings e.g.: OWL  or  RDF  or  TXT\n" +
				"\t5. Full IRI or full Path:\n" +
				"\t\ta. Full IRI of input mappings if OWL format. e.g.: file:/C://mymappings.owl  or  file:/usr/local/mymappings.owl  or http://mymappings.owl\n" +
				"\t\tb. or Full path of input mappings if formats RDF or TXT. e.g.: C://mymappings.rdf  or  /usr/local/mymappings.txt\n" +
				"\t6. Full output path for the repaired mappings: e.g. /usr/local/output_path or C://output_path\n or /C://output_path/\\n" +
				"\t7. Extract modules for repair?: true or false\n" +
				"\t8. Check satisfiability after repair using HermiT? true or false\n\n" +
				"\tFor example: java -jar logmap2_standalone.jar DEBUGGER file:/home/ontos/cmt.owl file:/home/ontos/ekaw.owl " +
				"RDF /usr/local/mymappings.rdf /home/mappings/output false true\n\n\n" + 

				
				"The RDF2OWL converter facility requires 4 parameters:\n" +
				"\t1. RDF2OWL. To transform from RDF-OAEI format to OWL. Note that the input ontologies are required to check the type of entity of the mapped IRIs.\n" +
				"\t2. IRI ontology 1. e.g.: http://myonto1.owl  or  file:/C://myonto1.owl  or  file:/usr/local/myonto1.owl\n" +
				"\t3. IRI ontology 2. e.g.: http://myonto2.owl  or  file:/C://myonto2.owl  or  file:/usr/local/myonto2.owl\n" +
				"\t4. Full path RDF mappings to be converted: e.g. C://mymappings.rdf  or  /usr/local/mymappings.rdf\n\n" +
				"\tFor example: java -jar logmap2_standalone.jar RDF2OWL file:/home/ontos/cmt.owl file:/home/ontos/ekaw.owl /usr/local/mymappings.rdf\n\n";
				
				*/	
		
	}
	
	
	private void saveLogMapMappings(Set<MappingObjectStr> mappings, String output_path, String name) throws Exception {
		

		OutPutFilesManagerStatic.createOutFiles(output_path + name, OutPutFilesManagerStatic.AllFormats, "http://logmap-tests/oaei/source.owl", "http://logmap-tests/oaei/target.owl");
		
		for (MappingObjectStr mapping : mappings) {
			
			if (mapping.isClassMapping())
				OutPutFilesManagerStatic.addClassMapping2Files(
						mapping.getIRIStrEnt1(), mapping.getIRIStrEnt2(), mapping.getMappingDirection(), mapping.getConfidence());
			else if (mapping.isObjectPropertyMapping())
				OutPutFilesManagerStatic.addObjPropMapping2Files(
						mapping.getIRIStrEnt1(), mapping.getIRIStrEnt2(), mapping.getMappingDirection(), mapping.getConfidence());
			else if (mapping.isDataPropertyMapping())
				OutPutFilesManagerStatic.addDataPropMapping2Files(
						mapping.getIRIStrEnt1(), mapping.getIRIStrEnt2(), mapping.getMappingDirection(), mapping.getConfidence());
			else if (mapping.isInstanceMapping())
				OutPutFilesManagerStatic.addInstanceMapping2Files(
						mapping.getIRIStrEnt1(), mapping.getIRIStrEnt2(), mapping.getConfidence());
			
		}
		
		OutPutFilesManagerStatic.closeAndSaveFiles();
		
		
	}
	
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try{
			
			if (args.length<1){// || (!args[0].equals("MATCHER") && !args[0].equals("DEBUGGER"))){
				
				System.out.println(getHelpMessage());				
			}
			
			
						
			else if (args[0].equals("MATCHER")){
				
				if (args.length!=4){
					System.out.println(getHelpMessage());				
				}
				else {				
					new LogMapConservativityMatching(args[1], args[2], args[3]);
					
				}
				
				
			}
			else {
				System.out.println(getHelpMessage());
			}
			
			
			
		}
		catch(Exception e){
			System.out.println(getHelpMessage());
		}
		
		
	}

	
	

}
