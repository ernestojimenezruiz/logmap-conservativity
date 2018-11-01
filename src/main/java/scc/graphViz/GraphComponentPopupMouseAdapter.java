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
package scc.graphViz;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.mxgraph.model.mxCell;

import scc.gui.VisualDebugger;

public class GraphComponentPopupMouseAdapter extends MouseAdapter {
	private VisualDebugger vd;
	
	public GraphComponentPopupMouseAdapter(VisualDebugger vd){
		this.vd = vd;
	}
	
	public void mousePressed(MouseEvent e) {
		// Handles context menu on the Mac where the trigger is on mousepressed
		mouseReleased(e);
	}

	public void mouseReleased(MouseEvent e) {
		if (e.isPopupTrigger()){
			mxCell cell = 
					(mxCell) vd.graphComponent.getGraph().getSelectionCell();
			if(cell != null && cell.isEdge())
				vd.showGraphPopupMenu(e);
			else{
				if(vd.menu != null)
					vd.menu.hide();
			}
		}
	}
}
