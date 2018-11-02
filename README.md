# LogMap extension for the Conservativity Principle in Ontology Alignments

In order to enable interoperability between ontology-based systems, ontology matching techniques have been proposed. However, when the generated mappings lead to undesired logical consequences, their usefulness may be diminished. Our approach detects and minimizes the violations of the so-called conservativity principle where novel subsumption entailments between named concepts in one of the input ontologies are considered as unwanted. The practical applicability of the proposed approach is experimentally demonstrated on the datasets from the Ontology Alignment Evaluation Initiative.

This project contains a maven version of the original [LogMap-Conservativity](https://github.com/asolimando/logmap-conservativity) codes. LogMap's conservativity codes are currently being used by the [DARE](https://github.com/ernestojimenezruiz/dare-system) (Dialog-based Alignment Repair Engine) system.


## Use and Installation

* This project can be imported into Eclipse or other environments as a maven project it can be cloned using `git clone https://github.com/ernestojimenezruiz/logmap-conservativity.git`
* To generate a JAR file using Maven, run `mvn clean install` from the cloned project folder.
* Dependencies:	

	1. Together with the JAR file maven will also generate a folder with the "java-dependencies"
	2. The "lib" folder is also required. The "timeout" programs should be given execution permissions. 
	3. The "asp" folder contains the logic programs needed by the SCC repair algorithm
	4. The "resources" folder contains some example ontologies used in the tests

Check out the running example in the [v1.0 release](https://github.com/ernestojimenezruiz/logmap-conservativity/releases/download/v1.0/logmap-conservativity-kr2016-release.zip). `java -jar logmap-conservativity-1.0.0` runs the class _main.MainKR16_.


## References

- Alessandro Solimando, Ernesto Jiménez-Ruiz, Giovanna Guerrini:
**Minimizing conservativity violations in ontology alignments: algorithms and evaluation**. Knowl. Inf. Syst. 51(3): 775-819 (2017). ([PDF](https://www.cs.ox.ac.uk/files/8299/kais-conservativity.pdf))
- Alessandro Solimando, Ernesto Jiménez-Ruiz, Giovanna Guerrini:
**Detecting and Correcting Conservativity Principle Violations in Ontology-to-Ontology Mappings**. International Semantic Web Conference (2) 2014: 1-16. ([PDF](http://www.cs.ox.ac.uk/files/6647/conservativityLogMap.pdf))
- Ernesto Jiménez-Ruiz, Terry R. Payne, Alessandro Solimando, Valentina A. M. Tamma:
**Limiting Logical Violations in Ontology Alignnment Through Negotiation**. KR 2016: 217-226. ([PDF](http://www.cs.ox.ac.uk/files/8036/kr2016_jimenez-ruiz.pdf)) 
- [LogMap-matcher](https://github.com/ernestojimenezruiz/logmap-matcher) source codes.
- Original [LogMap-Conservativity](https://github.com/asolimando/logmap-conservativity) codes.
- [Dialog-based Alignment Repair Engine](https://github.com/ernestojimenezruiz/dare-system) source codes. 

