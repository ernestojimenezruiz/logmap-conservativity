/*******************************************************************************
 * Copyright 2016 by the Department of Computer Science (University of Genova and University of Oxford)
 * 
 *    This file is part of LogMapC an extension of LogMap matcher for conservativity principle.
 * 
 *    LogMapC is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU Lesser General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 * 
 *    LogMapC is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU Lesser General Public License for more details.
 * 
 *    You should have received a copy of the GNU Lesser General Public License
 *    along with LogMapC.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package graphViz;

import graphViz.myConservativityGraph;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.mxGraphOutline;

public class GraphCache {
	private myConservativityGraph graph;
	private mxGraphComponent graphComponent;
	private mxGraphOutline graphOutline;
	
	public GraphCache(myConservativityGraph graph, mxGraphComponent graphComponent, 
			mxGraphOutline graphOutline){
		this.graph = graph;
		this.graphComponent = graphComponent;
		this.graphOutline = graphOutline;
	}
	
	
	public myConservativityGraph getGraph(){
		return graph;
	}
	
	public mxGraphComponent getGraphComponent(){
		return graphComponent;
	}
	
	public mxGraphOutline getGraphOutline(){
		return graphOutline;
	}
}
