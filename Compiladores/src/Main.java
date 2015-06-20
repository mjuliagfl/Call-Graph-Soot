import java.util.Iterator;
import java.util.Map;

import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.jimple.toolkits.callgraph.Edge;
import soot.PackManager;
import soot.SceneTransformer;
import soot.Transform;




public class Main extends SceneTransformer{
	
	public static void main(String[] args) {
		
		Main makeGraph = new Main();
		Transform grafoTransformado = new Transform( "wjtp.generateCallGraph", makeGraph);
		PackManager.v().getPack("wjtp").add(grafoTransformado);
		
		soot.Main.main(args);
		
		soot.Main.v().run(new String[] {		
				"Class", "-w", "-main-class", "Class"
		});
		
		
		
	}
	
	
	protected void internalTransform(String fase, Map opcoes) {
		CallGraph callGraph = Scene.v().getCallGraph();
		
		
		for (SootClass item : Scene.v().getApplicationClasses()) {
			
			for (SootMethod metodo : item.getMethods()) {
				
				if (metodo.hasActiveBody() == false) {
					continue;
				}
				
				Iterator<Edge> edges = callGraph.edgesOutOf(metodo);
				
				while (edges.hasNext()) {
					Edge edge = edges.next();
					SootMethod chamado = (SootMethod) edge.getTgt();
					System.out.println(metodo.getSignature()+"\t calls --->  "+ chamado.getSignature());
				}
			}
		}
	}

}
