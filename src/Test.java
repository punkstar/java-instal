import uk.ac.bath.cs.agents.instal.CreationEvent;
import uk.ac.bath.cs.agents.instal.ExogeneousEvent;
import uk.ac.bath.cs.agents.instal.Fluent;
import uk.ac.bath.cs.agents.instal.Generates;
import uk.ac.bath.cs.agents.instal.Initiates;
import uk.ac.bath.cs.agents.instal.Institution;
import uk.ac.bath.cs.agents.instal.NormativeEvent;
import uk.ac.bath.cs.agents.instal.Terminates;
import uk.ac.bath.cs.agents.instal.Type;
import uk.ac.bath.cs.agents.instal.ViolationEvent;
import uk.ac.bath.cs.agents.instal.asp.AnsProlog;

public class Test {
	public static void main(String[] args) {
		Test t = new Test();
	}
	
	public Test() {		
		Institution inst = new Institution("grid", 10);
		
		/** TYPES **/
		Type handset = new Type("Handset");
		Type chunk = new Type("Chunk");
		Type player = new Type("Player");
		Type time = new Type("Time");
		Type channel = new Type("Channel");
		Type connectionPoint = new Type("ConnectionPoint");
		
		inst.type(handset)
			.type(chunk)
			.type(player)
			.type(time)
			.type(channel)
			.type(connectionPoint);
		
		/** EVENTS **/
		// Exogeneous
		ExogeneousEvent clock = new ExogeneousEvent("clock");
		ExogeneousEvent obtain = new ExogeneousEvent("obtain");
		ExogeneousEvent download = new ExogeneousEvent("download");
		
		obtain.addParameter(handset).addParameter(chunk).addParameter(channel);
		download.addParameter(handset).addParameter(handset).addParameter(chunk);
		
		// Creation
		CreationEvent create_grid = new CreationEvent("creategrid");
		
		// Normative
		NormativeEvent intObtain = new NormativeEvent("intObtain");
		NormativeEvent intShare = new NormativeEvent("intShare");
		NormativeEvent intDownload = new NormativeEvent("intDownload");
		NormativeEvent transition = new NormativeEvent("transition");
		
		intObtain.addParameter(handset).addParameter(chunk).addParameter(chunk);
		intShare.addParameter(handset);
		intDownload.addParameter(handset).addParameter(chunk);
		
		// Violations
		ViolationEvent misuse = new ViolationEvent("misuse");
		
		misuse.addParameter(handset);
		
		inst.event(clock)
			.event(obtain)
			.event(download)
			.event(create_grid)
			.event(intObtain)
			.event(intShare)
			.event(intDownload)
			.event(transition)
			.event(misuse);
		
		/** FLUENTS **/
		Fluent obtainChunk = new Fluent("obtainChunk");
		Fluent hasChunk = new Fluent("hasChunk");
		Fluent abusy = new Fluent("abusy");
		Fluent cbusy = new Fluent("cbusy");
		
		Fluent previous = new Fluent("previous");
		Fluent matchA = new Fluent("matchA");
		Fluent matchC = new Fluent("matchC");
		
		obtainChunk.addParameter(handset).addParameter(chunk);
		hasChunk.addParameter(handset).addParameter(chunk);
		abusy.addParameter(handset).addParameter(time);
		cbusy.addParameter(connectionPoint).addParameter(time);
		
		previous.addParameter(time).addParameter(time);
		matchA.addParameter(handset).addParameter(connectionPoint);
		matchC.addParameter(channel).addParameter(connectionPoint);
		
		inst.fluent(obtainChunk)
			.fluent(hasChunk)
			.fluent(abusy)
			.fluent(cbusy)
			.fluent(previous)
			.fluent(matchA)
			.fluent(matchC);
		
		/** RULES **/
		// For downloading..
		Generates g1 = new Generates(obtain, "A", "X", "C");
		Generates g2 = new Generates(obtain, "A", "X", "C");
		Generates g3 = new Generates(clock);
		
		g1.result(intObtain, "A", "X", "C")
		  .condition(false, cbusy, "C1", "T1")
		  .condition(false, cbusy, "A1", "T2")
		  .condition(false, matchA, "A", "A1")
		  .condition(true, matchC, "C", "C1");
		
		g2.result(transition);
		g3.result(transition);
		
		inst.generates(g1)
			.generates(g2)
			.generates(g3);
		
		Initiates i1 = new Initiates(intObtain, "A", "X", "C");
		Initiates i2 = new Initiates(intObtain, "A", "X", "C");
		
		i1.result(hasChunk, "A", "X");
		i2.result(cbusy, "A1", "2")
		  .result(cbusy, "C1", "2")
		  .condition(matchA, "A", "A1")
		  .condition(matchC, "C", "C1");
		
		Initiates i3 = new Initiates(transition);
		Initiates i4 = new Initiates(transition);
		Initiates i5 = new Initiates(transition);
		
		i3.result(cbusy, "A", "T2")
		  .condition(cbusy, "A", "T1")
		  .condition(previous, "T1", "T2");
		
		i4.result(intObtain, "A", "X", "C")
		  .condition(cbusy, "A1", "1")
		  .condition(matchA, "A", "A1");
		
		i5.result(intObtain, "A", "X", "C")
		  .condition(cbusy, "C1", "1")
		  .condition(matchA, "C", "C1");
		
		inst.initiates(i1)
			.initiates(i2)
			.initiates(i3)
			.initiates(i4)
			.initiates(i5);
		
		Terminates t1 = new Terminates(transition);
		
		t1.result(cbusy, "A", "Time");
		
		inst.terminates(t1);
		
		inst.initially(cbusy.initially("alice", "bob"));
		
		this.__log("InstAL:");
		System.out.println(inst.toString());
		
		this.__log("ASP:");
		AnsProlog asp = new AnsProlog(inst);
		asp.generate();
		System.out.println(asp.toString());
	}
	
	private void __log(String message) {
		System.out.println(String.format("*********** [log] %s", message));
	}
}
